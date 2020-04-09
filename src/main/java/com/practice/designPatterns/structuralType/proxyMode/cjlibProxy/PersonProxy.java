package com.practice.designPatterns.structuralType.proxyMode.cjlibProxy;

import com.practice.designPatterns.structuralType.proxyMode.Person;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @Auther : guojianmin
 * @Date : 2019/6/20 15:37
 * @Description : CGLIB代理  ：CGLIB是一个强大的，高性能，高质量的代码生成库，它可以在运行期扩展Java类与实现Java接口。
 *                CGLIB解决了动态代理的难题，它通过生成目标类子类的方式来实现来实现代理，而不是接口，规避了接口的局限性。
 *                CGLIB是一个强大的高性能代码生成包（生成原理还没研究过），其在运行时期（非编译时期）生成被 代理对象的子类，并重写了被代理对象的所有方法，从而作为代理对象。
 *               当然CGLIB也具有局限性，对于无法生成子类的类（final类），肯定是没有办法生成代理子类的。
 *
 * 同样的，CGLIB代理也有一个类和一个接口非常核心:
 *
 * import net.sf.cglib.proxy.Enhancer;
 * import net.sf.cglib.proxy.MethodInterceptor;
 * Enhancer 类用来设置被代理类和加强类（代理类）并生成代理对象，而 MethodInterceptor 接口主要是来处理执行逻辑。
 */
public class PersonProxy implements MethodInterceptor {

//    private Object delegate;
    private final Logger logger = LoggerFactory.getLogger(PersonProxy.class);

    /**
     * 被代理类  在执行其方法的时候 会进入此方法，执行代理过程（Before 和 After）。 根据反射 methodProxy.invokeSuper(method, args) 来确定执行的是哪一个方法
     * @param proxy
     * @param method
     * @param args
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

        Object result = null;
        try {
            logger.info("Before Proxy");
            result = methodProxy.invokeSuper(proxy, args);
            logger.info("After Proxy");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 创建 CGLIB 代理类对象
     * @return
     */
    public static Person getProxyInstance(){
        Enhancer enhancer = new Enhancer();
        //设置 被代理类
        enhancer.setSuperclass(Person.class);
        //设置代理类（即加强类）
        enhancer.setCallback(new PersonProxy());
        return (Person)enhancer.create();
    }

    public static void main(String[] args) {
        Person person = PersonProxy.getProxyInstance();
        person.doSomething("Swimming");
    }
}
