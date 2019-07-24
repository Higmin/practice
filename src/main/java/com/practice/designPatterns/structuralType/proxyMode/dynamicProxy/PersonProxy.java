package com.practice.designPatterns.structuralType.proxyMode.dynamicProxy;

import com.practice.designPatterns.structuralType.proxyMode.IPerson;
import com.practice.designPatterns.structuralType.proxyMode.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Auther : guojianmin
 * @Date : 2019/6/20 15:14
 * @Description : 动态代理。静态和动态是由代理产生的时间段来决定的。静态代理产生于代码编译阶段，即一旦代码运行就不可变了。
 * 它的好处理时可以为我们生成任何一个接口的代理类，并将需要增强的方法织入到任意目标函数。但它仍然具有一个局限性，就是只有实现了接口的类，才能为其实现代理。
 */
public class PersonProxy implements InvocationHandler {
    private Object delegate;
    private final Logger logger = LoggerFactory.getLogger(PersonProxy.class);

    /**
     * 代理类绑定代理对象,实现动态代理
     * @param delegate
     * @return 代理对象
     */
    public Object bind(Object delegate){
        this.delegate = delegate;
        return Proxy.newProxyInstance(delegate.getClass().getClassLoader(),delegate.getClass().getInterfaces(),this);
    }

    /**
     * 被代理类  在执行其方法的时候 会进入此方法，执行代理过程（Before 和 After）。 根据反射 method.invoke(delegate,args) 来确定执行的是哪一个方法
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        try {
            logger.info("Before Proxy");
            //method.invoke(owner, args)：执行该Method.invoke方法的参数是执行这个方法的对象owner，和参数数组args，可以这么理解：owner对象中带有参数args的method方法。返回值是Object，也既是该方法的返回值。
            result = method.invoke(delegate,args);
            logger.info("After Proxy");
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        PersonProxy personProxy = new PersonProxy();
        IPerson person = (IPerson) personProxy.bind(new Person());
        person.doSomething("Swimming");
    }
}
