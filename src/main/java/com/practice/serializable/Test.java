package com.practice.serializable;

import org.apache.commons.lang3.SerializationUtils;

/**
 * @author Higmin
 * @date 2019/7/15 13:48
 *
 * 什么是序列化
 * 我们的对象并不只是存在内存中，还需要传输网络，或者保存起来下次再加载出来用，所以需要Java序列化技术。
 *
 * Java序列化技术正是将对象转变成一串由二进制字节组成的数组，可以通过将二进制数据保存到磁盘或者传输网络，磁盘或者网络接收者可以在对象的属类的模板上来反序列化类的对象，达到对象持久化的目的。
 *
 * 怎么序列化一个对象？
 * 要序列化一个对象，这个对象所在类就必须实现Java序列化的接口：java.io.Serializable。
 *
 *
 * 步骤：
 * 1、类实现序列化接口
 * 2、序列化/反序列化
 * 可以借助commons-lang3工具包里面的类实现对象的序列化及反序列化，你没有必要自己写。
 *
 *
 * 这里需要注意的是：
 * 如果父类序列化了，子类会继承父类的序列化，子类无需添加序列化接口。
 * 如果父类没有序列化，子类序列化了，子类中的属性能正常序列化，但父类的属性会丢失，不能序列化。
 *
 **/
public class Test {

    public static void main(String[] args) {
        User user = new User();
        user.setName("Tom");
        user.setAddress("北京市朝阳区");

        //序列化
        byte[] bytes = SerializationUtils.serialize(user);

        //反序列化
        User user1 = SerializationUtils.deserialize(bytes);
        System.out.println(user1);
    }
}
