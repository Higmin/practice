package com.practice.concurrent.keyWord_volatile;

import java.util.concurrent.TimeUnit;

/**
 * @author Higmin
 * @date 2019/8/21 15:32
 *
 * 来一段 volatile 趣味代码
 *
 *  下面 Test 中这段代码的运行结果：不会输出结束，并且 JVM 也不会停止（原因是：其中主线程结束了，线程Thread-01并没有停，导致JVM没有停的原因，没有输出 "结束" ）
 **/
public class Test_01 {
    private static boolean condition = true;
    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            while (condition){}
            System.out.println("结束");
        },"Thread-01").start();

        TimeUnit.SECONDS.sleep(3);
        condition = false;
    }
}

/**
 * 针对Test_01的例子给 condition 加上volatile关键字修饰，猜猜看结果是什么？
 *
 * 下面 Test_02 运行结果：输出结束，并且 JVM 停止（主线程和线程Thread-01都停止了）
 *
 * 知道为什么吗？
 *
 * Test_01 中：主（main）线程修改标志，看是否能让线程Thread-01跳出循环。执行程序后发现程序并没有执行完，而是在一直等待线程Thread-01执行完毕。
 * 这就说明主线程修改condition变量并不对线程1可见，所以普通变量是不保证可见性的
 *
 * Test_02 中：由于加上volatile关键字修饰，使得condition对线程Thread-01可见，所以就会输出结束，并且 JVM 停止
 */
class Test_02{
    private static volatile boolean condition = true;
    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            while (condition){}
            System.out.println("结束");
        },"Thread-01").start();

        TimeUnit.SECONDS.sleep(3);
        condition = false;
    }
}


/**
 *  针对Test_01的例子，如果不允许加 volatile 关键字  是否有其他的办法？
 *
 *  暗示一下： 在 Thread-01 死循环中，加入System.out.println()输出语句 ，看看会发生什么？
 *
 *  结果：在主线程睡眠期间，Thread-01 不断循环，主线程睡眠结束，更改condition = false，然后输出 “结束”，并且JVM停止（主线程和Thread_01都结束了）
 *
 *  想想在这是为什么？
 *
 *  一起去看看 print 源码：发现源码中有同步synchronized，这就可以解释通了，synchronized是会刷新内存的，关于synchronized原理大家自行去了解。
 *  其实很好理解  synchronized不管是不是 阻塞 线程都会检查一下自己是否可以执行，并在检查过程中，刷新主存 并更新自己栈内存，导致Thread-01线程的数据是内存中最新的数据。
 *  public void println(String x) {
 *         synchronized (this) {
 *             print(x);
 *             newLine();
 *         }
 *     }
 */
class Test_03{
    private static  boolean condition = true;
    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            while (condition){
                // 在Test_01的基础上，在这里加一行输出 System.out.println();
                System.out.println("-Thread-01执行中-");
            }
            System.out.println("结束");
        },"Thread-01").start();

        TimeUnit.SECONDS.sleep(3);
        condition = false;
    }
}
