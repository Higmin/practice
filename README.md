## 欢迎大家来到 Higmin GitHub Project！ 

创建此工程是为了巩固基础知识 当然能对社区做点贡献也是我小小的心愿~

本工程包含了 SpringAOP，死锁，同步锁，读 - 写同步锁，数据结构（待完善），生成XML文件，mybatis逆向工程，接口并发测试，BIO，NIO， AIO，Netty的服务，客户端... 并且在不断更新中,有不足之处请留言指出

### 1. SpringAOP

AOP称为面向切面编程,也是面试当中经常会被问到的一环，其实Spring AOP的底层原理就是动态代理，使用动态代理实质上就是调用时拦截对象方法，对方法进行改造、增强。
本工程中的AOP方面是从手写动态代理的角度来深入理解AOP的。具体分为三个阶段：静态代理，动态代理，还有CGLIB代理.
详情移步：https://github.com/Higmin/practice/tree/master/src/main/java/com/practice/spring

以动态代理来说,测试动态代理加强类时，创建main方法直接测试即可：

```markdown
/**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        PersonProxy personProxy = new PersonProxy();
        IPerson person = (IPerson) personProxy.bind(new Person());
        person.doSomething("Swimming");
    }
```
### 2. IO
java Io流共涉及40多个类，这些类看上去很杂乱，但实际上很有规则，而且彼此之间存在非常紧密的联系， Java Io流的40多个类都是从如下4个抽象类基类中派生出来的。

InputStream/Reader: 所有的输入流的基类，前者是字节输入流，后者是字符输入流。
OutputStream/Writer: 所有输出流的基类，前者是字节输出流，后者是字符输出流。

本工程中的IO，主要是从BIO,NIO,AIO方面来梳理的，然后在这之后还加了一个基于NIO实现的Netty服务端，客户端示例以便理解。

BIO:同步非阻塞
NIO:同时支持阻塞与非阻塞模式，但这里我们以其同步非阻塞I/O模式来说明，并且是面向缓冲区的。
AIO:异步非阻塞I/O模型
详情移步：https://github.com/Higmin/practice/tree/master/src/main/java/com/practice/io

未完待续...


### Support or Contact

Having trouble with Pages? Check out our [documentation](https://help.github.com/categories/github-pages-basics/) or [contact support](https://github.com/contact) and we’ll help you sort it out.
