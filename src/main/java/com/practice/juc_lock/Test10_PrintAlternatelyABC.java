package com.practice.juc_lock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Higmin
 * 三个线程交替打印ABC
 * @version 1.0
 * @date 2023/1/9 15:26:36
 */
public class Test10_PrintAlternatelyABC {

    public static int PRINT_ALTERNATELY_ABC_COUNT = 0;
    public static void main(String[] args) {
        // 1. 使用 Condition 实现 三个线程交替打印ABC
//        ReentrantLock lock = new ReentrantLock();
//        Condition conditionA = lock.newCondition();
//        Condition conditionB = lock.newCondition();
//        Condition conditionC = lock.newCondition();
//        new Thread(new PrintAlternatelyABCUseCondition("A", lock, conditionA, conditionB, 0)).start();
//        new Thread(new PrintAlternatelyABCUseCondition("B", lock, conditionB, conditionC, 1)).start();
//        new Thread(new PrintAlternatelyABCUseCondition("C", lock, conditionC, conditionA, 2)).start();


        // 2. 使用 Semaphore 实现 三个线程交替打印ABC
        Semaphore semaphorA = new Semaphore(1);
        Semaphore semaphorB = new Semaphore(0);
        Semaphore semaphorC = new Semaphore(0);
        new Thread(new PrintAlternatelyABCUseSemaphore("A", semaphorA, semaphorB)).start();
        new Thread(new PrintAlternatelyABCUseSemaphore("B", semaphorB, semaphorC)).start();
        new Thread(new PrintAlternatelyABCUseSemaphore("C", semaphorC, semaphorA)).start();

    }
}

/**
 * 实现方式1：使用 Condition 实现
 *
 * 使用 Condition 的等待唤醒实现，由于无法控制线程的执行顺序，所以需要定义一个共享变量，来控制线程开始执行的顺序
 * 示例中使用了一个共享的计数来控制是不是需要等待。
 */
class PrintAlternatelyABCUseCondition implements Runnable {

    private String msg;
    private ReentrantLock lock;
    private Condition curCondition;
    private Condition nextCondition;
    /**
     * 用来控制线程优先级
     */
    private int state;

    public PrintAlternatelyABCUseCondition(String msg, ReentrantLock lock, Condition curCondition, Condition nextCondition, int state) {
        this.msg = msg;
        this.lock = lock;
        this.curCondition = curCondition;
        this.nextCondition = nextCondition;
        this.state = state;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            lock.lock();
            try {
                //如果执行优先级还没到，则需要等待
                if (Test10_PrintAlternatelyABC.PRINT_ALTERNATELY_ABC_COUNT % 3 != state) {
                    curCondition.await();
                }
                System.out.print(msg);
                Test10_PrintAlternatelyABC.PRINT_ALTERNATELY_ABC_COUNT++;
                nextCondition.signal();
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
    }
}

/**
 * 实现方式2：使用 Semaphore 实现
 * 使用 Semaphore 实现时，不需要共享变量来控制顺序，因为Semaphore的特性是可以控制许可的消耗和增加，因此可以手动控制三个线程获取许可的时机。
 *
 * 思路：给优先执行的线程一个许可，其他线程0个许可，然后手动控制许可的获得可释放。
 */
class PrintAlternatelyABCUseSemaphore implements Runnable {

    private String msg;
    private Semaphore curSemaphore;
    private Semaphore nextSemaphore;

    public PrintAlternatelyABCUseSemaphore(String msg, Semaphore curSemaphore, Semaphore nextSemaphore) {
        this.msg = msg;
        this.curSemaphore = curSemaphore;
        this.nextSemaphore = nextSemaphore;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                curSemaphore.acquire(); // 当前线程信号量 -1，此时当前线程信号量为0，将无法再次获取信号量执行
                System.out.print(msg);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                nextSemaphore.release(); // 下一个线程的信号量 +1， 此时下一个线程就能获取到信号量，然后执行
            }
        }
    }
}
