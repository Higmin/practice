package com.practice.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Felix
 * @version 1.0
 * @date 2023/3/19 12:04:16
 */
@Configuration
@Slf4j
public class RestTemplateConfig {

    /**
     * 从连接池获取连接的timeout,不宜过大,ms
     */
    @Value("${rest-template.pool.connection-request-timeout:100}")
    private int connectionRequestTimeout;
    /**
     * 连接超时时间
     */
    @Value("${rest-template.http-pool.connection-timeout:30000}")
    private int connectionTimeout;
    /**
     * 数据读取超时时间
     */
    @Value("${rest-template.http-pool.socket-timeout:30000}")
    private int socketTimeout;
    /**
     * 同路由并发数
     */
    @Value("${rest-template.http-pool.max-per-route:100}")
    private int maxPerRoute;
    /**
     * 连接池最大数
     */
    @Value("${rest-template.http-pool.max-total:1000}")
    private int maxTotal;
    /**
     * 重试次数
     */
    @Value("${rest-template.http-pool.retry-times:3}")
    private int retryCount;

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        return new RestTemplate(factory);
    }

    @Bean
    public ClientHttpRequestFactory httpRequestFactory() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        CloseableHttpClient httpClient = getCloseableHttpClient();

        HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpComponentsClientHttpRequestFactory.setHttpClient(httpClient);
        httpComponentsClientHttpRequestFactory.setConnectionRequestTimeout(connectionRequestTimeout); // 不宜过长
        httpComponentsClientHttpRequestFactory.setConnectTimeout(connectionTimeout);
        httpComponentsClientHttpRequestFactory.setReadTimeout(socketTimeout);
        return httpComponentsClientHttpRequestFactory;
    }

    private CloseableHttpClient getCloseableHttpClient() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(SSLContexts.custom().loadTrustMaterial(null, (arg0, arg1) -> true).build(), NoopHostnameVerifier.INSTANCE);
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", sslConnectionSocketFactory)
                .build();
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);

        // 连接池最大连接数
        connectionManager.setMaxTotal(maxTotal);
        // 同路由并发数
        connectionManager.setDefaultMaxPerRoute(maxPerRoute);

        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Accept", "*/*"));
        headers.add(new BasicHeader("Content-Type", "application/json"));

        return HttpClients.custom()
                .setSSLSocketFactory(sslConnectionSocketFactory)
                .setConnectionManager(connectionManager)
                .setRetryHandler(new DefaultHttpRequestRetryHandler(retryCount, true))
                .setDefaultHeaders(headers)
                .build();
    }
}
