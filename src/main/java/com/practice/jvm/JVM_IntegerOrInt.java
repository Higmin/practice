package com.practice.jvm;

/**
 * @author Higmin
 * @date 2019/9/2 9:03
 *
 *  警惕jvm自动拆装箱带来的性能损耗   能用值类型解决问题的情况下 坚决不适用  引用类型
 **/
public class JVM_IntegerOrInt {
    public static void main(String[] args) {
        int count = 0;
        long st = System.currentTimeMillis();
        for (int i = 0; i < Integer.MAX_VALUE; i ++){
            count += i;
        }
        System.out.println(System.currentTimeMillis() - st); // for循环中，基本数据类型  count += i 所用时长

        //-----------------------------------------------------

        Integer countObject = 0;
        st = System.currentTimeMillis();
        for (int i = 0; i < Integer.MAX_VALUE; i ++){
            countObject += i;
        }
        System.out.println(System.currentTimeMillis() - st); // for循环中，基本数据类型  countObject += i 所用时长
    }
}
