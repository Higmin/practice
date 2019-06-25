package com.practice.io.nio;

import com.practice.io.nio.client.Client;
import com.practice.io.nio.server.Server;

import java.util.Scanner;

/**
 * @Auther : guojianmin
 * @Date : 2019/6/25 14:19
 * @Description : 测试方法
 *
 * 顺序：BIO（同步阻塞）-->NIO（同步非阻塞）-->AIO（异步非阻塞）-->Netty
 *
 * 1.启动Main方法
 * 2.在控制台输入 计算式 （ 例如：1+1  6-5  2*2   4/2  ）
 */
public class Test {

    //测试主方法
    public static void main(String[] args) {

        try {
            //运行服务器
            Server.start();
            //避免客户端先于服务器启动前执行代码
            Thread.sleep(1000);
            //运行客户端
            Client.start();

            //new Scanner(System.in) 从控制台读入数据
            //.nextInt();读入的是int型的
            while(Client.sendMsg(new Scanner(System.in).nextLine()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
