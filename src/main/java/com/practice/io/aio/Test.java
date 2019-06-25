package com.practice.io.aio;

import com.practice.io.aio.client.Client;
import com.practice.io.aio.server.Server;

import java.util.Scanner;

/**
 * @Auther : guojianmin
 * @Date : 2019/6/25 15:06
 * @Description : 测试方法
 *
 * 1.启动Main方法
 * 2.在控制台输入 计算式 （ 例如：1+1  6-5  2*2   4/2  ）
 *
 * 顺序：BIO（同步阻塞）-->NIO（同步非阻塞）-->AIO（异步非阻塞）-->Netty
 */
public class Test {
    public static void main(String[] args) {
        try {
            //运行服务器
            Server.start();
            //避免客户端先于服务器启动前执行代码
            Thread.sleep(100);
            //运行客户端
            Client.start();
            System.out.println("请输入请求消息：");
            Scanner scanner = new Scanner(System.in);
            while(Client.sendMsg(scanner.nextLine()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
