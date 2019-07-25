package com.practice.concurrent.threadLocal;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Higmin
 * @date 2019/7/25 15:42
 *
 * 启动15个线程，线程向map中写入20个整型值，然后输出map。运行该程序，观察结果，我们会发现，map中压根就不止20个元素，这说明程序产生了线程安全问题。
 * 我们都知道HashMap是非线程安全的，程序启动了15个线程，他们共享了同一个map，15个线程都往map写对象，这势必引起线程安全问题。
 *
 * 我们有三种方法解决这个问题：
 *
 * 1.将map的声明放到run方法中，这样map就成了方法内部变量，每个线程都有一份new HashMap()，无论多少个线程执行run方法，都不会有线程安全问题。这个方法也正如应用场景中提到的，如果有多处地方使用到map，传值是个烦人的地方。
 * 2.将HashMap换成Hashtable。用线程同步来解决问题，然而我们的程序只是想向一个map中写入20个整型的KEY-VALUE而已，并不需要线程同步，同步势必影响性能，得不偿失。
 * 3.ThreadLocal提供另外一种解决方案，即在解决方案a上边，将new HashMap()得到的实例变量，绑定到当前线程中。之后从任何地方，都可以通过ThreadLocal获取到该变量。
 * (ThreadLocal解决方法：将程序中的注释代码恢复，再将 private final static Map map = new HashMap();注释掉，运行程序，结果就是我们想要的。)
 **/
public class TreadLocalTest {

//    static ThreadLocal<HashMap> threadLocal = new ThreadLocal<HashMap>() {
//        @Override
//        protected HashMap initialValue() {
//            System.out.println(Thread.currentThread().getName() +" initialValue ");
//            return new HashMap();
//        }
//    };

    public static class T1 implements Runnable {
        private final static Map map = new HashMap();
        int id;

        public T1(int id) {
            this.id = id;
        }

        public void run() {
            // Map map = threadLocal.get();
            for (int i = 0; i < 20; i++) {
                map.put(i, i + id * 100);
                try {
                    Thread.sleep(100);
                } catch (Exception ex) {
                }
            }
            System.out.println(Thread.currentThread().getName()
                    + "  ==>  map.size()=" + map.size() + "  ==>  " + map);
        }
    }

    public static void main(String[] args) {
        Thread[] runs = new Thread[15];
        T1 t = new T1(1);
        for (int i = 0; i < runs.length; i++) {
            runs[i] = new Thread(t);
        }
        for (int i = 0; i < runs.length; i++) {
            runs[i].start();
        }

    }

}
