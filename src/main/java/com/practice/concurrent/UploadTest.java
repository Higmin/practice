package com.exercise.concurrent;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Auther : guojianmin
 * @Date : 2019/6/14 16:39
 * @Description : 目标：编写一段代码，模拟并发请求为20，且总的请求数为1000，当1000个请求完成后，打印“请求完成”
 */
public class UploadTest {
    // 总的请求个数
    public static final int requestTotal = 20;

    // 同一时刻最大的并发线程的个数
    public static final int concurrentThreadNum = 20;

    private static Integer count = 0;
    private static Integer countFail = 0;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(requestTotal);
        Semaphore semaphore = new Semaphore(concurrentThreadNum);
        for (int i = 0; i< requestTotal; i++) {
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    String result = testRequestUri();
                    System.out.println("result:{} "+ result);
                    semaphore.release();
                    count++;
                } catch (InterruptedException e) {
                    System.out.println("exception : "+e);
                    countFail++;
                } catch (IOException e) {
                    e.printStackTrace();
                    countFail++;
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                    countFail++;
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("请求完成");
        System.out.println("成功线程数：" + count);
        System.out.println("失败数为：" + countFail);
    }

    private static String testRequestUri() throws IOException, URISyntaxException {
        return HttpClientUtil.doGet("http://192.168.10.166:8443/transcription/getFtpTransferInfoList");
    }
}
