package com.practice.xssFilter;

/**
 * SpringBoot 项目防止XSS漏洞攻击解决办法
 * 1.创建包装request的类 XssHttpServletRequestWrapper
 * 2.自定义过滤器过滤器拦截请求（创建过滤器）
 * 3.添加过滤器
 * 这里需要注意一点的是@WebFilter这个注解是Servlet3.0的规范，并不是Spring boot提供的。除了这个注解以外，我们还需在配置类中加另外一个注解：@ServletComponetScan，指定扫描的包。
 */
public class Test {
}
