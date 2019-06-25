package com.practice.designPatterns.creationType.builderMode;

/**
 * @Auther : guojianmin
 * @Date : 2019/6/25 9:18
 * @Description : TODO用一句话描述此类的作用
 */
public class Test {
    public static void main(String[] args) {
        User user = User.builder()
                .name("Tom")
                .password("123456")
                .desc("描述内容")
                .age(18)
                .build();
        System.out.println(user.toString());

        User user2 = User.builder()
                .age(16)
                .build();
        System.out.println(user2.toString());
    }
}
