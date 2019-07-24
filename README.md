## 欢迎大家来到 Higmin GitHub Project！ 

创建此工程是为了巩固基础知识 当然能对社区做点贡献也是我小小的心愿~

本工程包含了 SpringAOP，死锁，同步锁，读 - 写同步锁，数据结构（待完善），生成XML文件，mybatis逆向工程，接口并发测试，BIO，NIO， AIO，Netty的服务，客户端... 并且在不断更新中,有不足之处请留言指出。

### 1. SpringAOP

AOP称为面向切面编程,也是面试当中经常会被问到的一环，其实Spring AOP的底层原理就是动态代理，使用动态代理实质上就是调用时拦截对象方法，对方法进行改造、增强。
本工程中的AOP方面是从手写动态代理的角度来深入理解AOP的。具体分为三个阶段：静态代理，动态代理，还有CGLIB代理。
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

### 3. Lambda表达式

什么是Lambda表达式:
Java 8 的一个大亮点是引入Lambda表达式，使用它设计的代码会更加简洁。
当开发者在编写Lambda表达式时，也会随之被编译成一个函数式接口。
-> 是Java 8新增的Lambda表达式中，变量和临时代码块的分隔符，即：
(变量) -> {代码块}
如果代码块只有一个表达式，大括号可以省略。如果变量类型可以自动推断出来，可以不写变量类型。
::是类似于C++的域运算符，获取方法使用的。
stream()也是JDK8新增的流，你的表达式中将list转换为流，就可以惰性处理，这样只有变量要用的时候才会被调用。
本工程中着重Lambda表达式使用。
详情移步：https://github.com/Higmin/practice/tree/master/src/main/java/com/practice/Lambda
关于 java 8 更多新特性，可以在这里了解更多：http://ifeve.com/java-8-features-tutorial

### 4. 设计模式（未完待续...）

使用设计模式是为了重用代码、让代码更容易被他人理解、保证代码可靠性。大致可以分为三类：创建型，结构型，和功能型。

创建型：抽象工厂模式、工厂方法、建造者模式、原型模式、单态模式
结构型：适配器模式、桥接模式、组合模式、外观模式、装饰者模式、享元模式、代理模式
行为型：责任链模式、命令模式、解释器模式、迭代模式、中介者模式、备忘录模式、观察者模式、状态模式、策略模式、模板方法模式、访问者模式 

本工程中通过案例的模式展示设计模式通常的写法，加强理解。
详情移步：https://github.com/Higmin/practice/tree/master/src/main/java/com/practice/designPatterns

### 5. 防止XSS漏洞攻击解决办法

1.创建包装request的类 XssHttpServletRequestWrapper
2.自定义过滤器过滤器拦截请求（创建过滤器）
3.添加过滤器
这里需要注意一点的是@WebFilter这个注解是Servlet3.0的规范，并不是Spring boot提供的。除了这个注解以外，我们还需在配置类中加另外一个注解：@ServletComponetScan，指定扫描的包。

本工程中以 SpringBoot 项目 为示例，写了具体实现，以供参考。
详情移步：https://github.com/Higmin/practice/tree/master/src/main/java/com/practice/xssFilter

未完待续...


### Support or Contact

Having trouble with Pages? Check out our [documentation](https://help.github.com/categories/github-pages-basics/) or [contact support](https://github.com/contact) and we’ll help you sort it out.
