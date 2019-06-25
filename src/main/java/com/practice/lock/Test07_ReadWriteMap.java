package com.exercise.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Auther : guojianmin
 * @Date : 2019/6/13 16:33
 * @Description : 公平锁
 *
 * ReentrantReadWriteLock为两个锁提供了可重入的加锁语义，它是继承了ReadWriteLock，扩展了ReadWriteLock。它与ReadWriteLock相同，ReentrantReadWriteLock能够被构造
 * 为非公平锁(构造方法不设置参数，默认是非公平)，或者公平。在公平锁中，选择权交给等待时间最长的线程；如果锁由读者获得，而一个线程请求写入锁，那么不再允许读者获得读取锁，直到写者被受理，平且已经释放了写锁。
 * 在非公平的锁中，线程允许访问的顺序是不定的。由写者降级为读者是允许的；从读者升级为写者是不允许的(尝试这样的行为会导致死锁)
 * 　　当锁被持有的时间相对较长，并且大部分操作都不会改变锁守护的资源，那么读写锁能够改进并发性。ReadWriteMap使用了ReentrantReadWriteLock来包装Map,使得它能够在多线程间
 * 被安全的共享，并仍然能够避免 "读-写" 或者 ”写-写“冲突。显示中ConcurrentHashMap并发容器的性能已经足够好了，所以你可以是使用他，而不必使用这个新的解决方案，如果你需要并发的部分
 * 只有哈希Map，但是如果你需要为LinkedHashMap这种可替换元素Map提供更好的并发访问，那么这项技术是非常有用的。
 *
 *
 * 预期结果：使用 hashMap  测试 ，会抛出异常（因为线程不安全导致的） 第 101  106  121  127行
 *          使用自定义的ReadWriteMap 测试， 不会抛异常，程序正常运行  第 100  105  120  126行
 */
public class Test07_ReadWriteMap<K,V> {

    private final Map<K,V> map;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock r = lock.readLock();
    private final Lock w = lock.writeLock();

    public Test07_ReadWriteMap(Map<K, V> map) {
        this.map = map;
    }

    public V put(K key, V value){
        w.lock();
        V put = null;
        try {
            put = map.put(key, value);

        } finally {
            w.unlock();
        }
        return put;
    }

    public void putAll(Map<? extends K, ? extends V> m){
        w.lock();
        try {
            map.putAll(m);
        } finally {
            w.unlock();
        }
    }

    public void clear(){
        w.lock();
        try {
            map.clear();
        } finally {
            w.unlock();
        }
    }

    public V remove (Object key){
        w.lock();
        try {
            return map.remove(key);
        } finally {
            w.unlock();
        }
    }

    public V get(Object key){
        r.lock();
        try {
            return map.get(key);
        } finally {
            r.unlock();
        }
    }

    public Map<K, V> getMap() {
        return map;
    }

    public static void main(String[] args) {
        Test07_ReadWriteMap<Integer, String> map = new Test07_ReadWriteMap<Integer, String>(new HashMap<>());
        HashMap<Object, Object> hashMap = new HashMap<>();

        new Thread(){
                @Override
                public void run() {
                    for (int i = 0; i < 30; i++) {
                        int finalI = i;
                        new Thread(){
                            @Override
                            public void run() {
                                map.put(finalI,"我是value"+ finalI);
//                                hashMap.put(finalI,"我是value"+finalI);
                                System.out.println(Thread.currentThread() + " 正在写操作 -----1");
                            }
                        }.start();
                        System.out.println("map: "+map.toString());
//                        System.out.println("map: "+hashMap.toString());
                    }
                }

            }.start();

            new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < 30; i++) {
                        int finalI = i;
                        new Thread(){
                            @Override
                            public void run() {
                                map.put(finalI,"我是又一个value"+ finalI);
//                                hashMap.put(finalI,"我是又一个value"+ finalI);
                                System.out.println(Thread.currentThread() + " 正在写操作 -----2");
                            }
                        }.start();
                    }
                    System.out.println("map: "+map.toString());
//                    System.out.println("map: "+hashMap.toString());
                }
            }.start();

        }

}
