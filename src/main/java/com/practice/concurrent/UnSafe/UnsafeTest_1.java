package com.practice.concurrent.UnSafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.nio.*;

/**
 * @author Higmin
 * @date 2019/8/19 15:19
 *
 * 通过staticFieldOffset和objectFieldOffset可以获取静态和非静态成员属性的偏移地址，然后直接进行存取值操作。
 * 重要的事情说n遍：Unsafe申请的内存的使用将直接脱离jvm，gc将无法管理Unsafe申请的内存，所以使用之后一定要手动释放内存，避免内存溢出！！！
 **/
public class UnsafeTest_1 {

    private Integer STATIC_INT = 1024;

    private static Unsafe getUnsafe() throws Exception {
        Field f = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        return (Unsafe) f.get(null);
    }
    public static void main(String[] args) throws Exception {
        Unsafe unsafe = getUnsafe();
        Field staticInt = UnsafeTest_1.class.getDeclaredField("STATIC_INT");
        staticInt.setAccessible(true);
        Object staticFieldBase = unsafe.staticFieldBase(staticInt);
        long staticFieldOffset = unsafe.staticFieldOffset(staticInt);
    }
}
