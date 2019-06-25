package com.practice.spring.springAOP1;

import com.practice.spring.IPerson;
import com.practice.spring.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Auther : guojianmin
 * @Date : 2019/6/20 15:08
 * @Description : 静态代理。静态和动态是由代理产生的时间段来决定的。静态代理产生于代码编译阶段，即一旦代码运行就不可变了。
 * 通过代理类我们实现了将日志代码集成到了目标类，但从上面我们可以看出它具有很大的局限性：
 *     需要固定的类编写接口（或许还可以接受，毕竟有提倡面向接口编程），需要实现接口的每一个函数（不可接受），同样会造成代码的大量重复，将会使代码更加混乱。
 */
public class PersonProxy {
    private IPerson person;
    private final static Logger logger = LoggerFactory.getLogger(PersonProxy.class);

    public PersonProxy() {
    }

    public PersonProxy(IPerson person) {
        this.person = person;
    }

    public void doSomething(String work){
        logger.info("Before Proxy");
        person.doSomething(work);
        logger.info("After  Proxy");
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        PersonProxy personProxy = new PersonProxy(new Person());
        personProxy.doSomething("Swimming");
    }

}
