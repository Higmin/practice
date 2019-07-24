## 欢迎大家来到 Higmin GitHub Project！   

创建此工程是为了巩固基础知识 当然能对社区做点贡献也是我小小的心愿~  

本工程包含了 SpringAOP，死锁，同步锁，读 - 写同步锁，数据结构（待完善），生成XML文件，mybatis逆向工程，接口并发测试，BIO，NIO， AIO，Netty的服务，客户端... 并且在不断更新中,有不足之处请留言指出。  

### 1. SpringAOP  

AOP称为面向切面编程,也是面试当中经常会被问到的一环，其实Spring AOP的底层原理就是动态代理，使用动态代理实质上就是调用时拦截对象方法，对方法进行改造、增强。  
本工程中的AOP方面是从手写动态代理的角度来深入理解AOP的。具体分为三个阶段：静态代理，动态代理，还有CGLIB代理。 
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

详情移步：https://github.com/Higmin/practice/tree/master/src/main/java/com/practice/spring  

### 2. IO

java Io流共涉及40多个类，这些类看上去很杂乱，但实际上很有规则，而且彼此之间存在非常紧密的联系， Java Io流的40多个类都是从如下4个抽象类基类中派生出来的。   
InputStream/Reader: 所有的输入流的基类，前者是字节输入流，后者是字符输入流。  
OutputStream/Writer: 所有输出流的基类，前者是字节输出流，后者是字符输出流。   
本工程中的IO，主要是从BIO,NIO,AIO方面来梳理的，然后在这之后还加了一个基于NIO实现的Netty服务端，客户端示例以便理解。  
BIO:同步非阻塞  
NIO:同时支持阻塞与非阻塞模式，但这里我们以其同步非阻塞I/O模式来说明，并且是面向缓冲区的  
AIO:异步非阻塞I/O模型   

详情移步：https://github.com/Higmin/practice/tree/master/src/main/java/com/practice/io

### 3. Java 锁机制之Lock
Lock接口比同步方法和同步块（这里的同步就是考察Synchronized关键字）提供了更具扩展性的锁操作。Lock不是Java语言内置的，synchronized是Java语言的关键字，因此是内置特性，Lock是一个类，通过这个类可以实现同步访问；他们允许更灵活的结构，可以具有完全不同的性质，并且可以支持多个相关类的条件对象。它的优势有：可以使锁更公平；可以使线程在等待锁的时候响应中断；可以让线程尝试获取锁，并在无法获取锁的时候立即返回或者等待一段时间；可以在不同的范围，以不同的顺序获取和释放锁  
本工程中是从死锁、如何避免死锁、同步锁、读-写同步锁方面来介绍。  

详情移步：https://github.com/Higmin/practice/tree/master/src/main/java/com/practice/lock
这里只是基础部分的示例，关于更全面的锁机制介绍，包括对公平锁，非公平锁，乐观锁，悲观锁，和分布式锁等的介绍或者导读，推荐一篇文章写的挺全面的，感兴趣的小伙伴可以去观摩一番：https://www.cnblogs.com/tison/p/8283233.html

### 4. Lambda表达式

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

### 5. 设计模式（未完待续...）

使用设计模式是为了重用代码、让代码更容易被他人理解、保证代码可靠性。大致可以分为三类：创建型，结构型，和功能型。  

创建型：抽象工厂模式、工厂方法、建造者模式、原型模式、单态模式  
结构型：适配器模式、桥接模式、组合模式、外观模式、装饰者模式、享元模式、代理模式  
行为型：责任链模式、命令模式、解释器模式、迭代模式、中介者模式、备忘录模式、观察者模式、状态模式、策略模式、模板方法模式、访问者模式   

本工程中通过案例的模式展示设计模式通常的写法，加强理解。  

详情移步：https://github.com/Higmin/practice/tree/master/src/main/java/com/practice/designPatterns  

### 6. 防止XSS漏洞攻击解决办法

1.创建包装request的类 XssHttpServletRequestWrapper  
2.自定义过滤器过滤器拦截请求（创建过滤器）  
3.添加过滤器  
这里需要注意一点的是@WebFilter这个注解是Servlet3.0的规范，并不是Spring boot提供的。除了这个注解以外，我们还需在配置类中加另外一个注解：@ServletComponetScan，指定扫描的包。  

本工程中以 SpringBoot 项目 为示例，写了具体实现，以供参考。

详情移步：https://github.com/Higmin/practice/tree/master/src/main/java/com/practice/xssFilter  

### 7. MyBatis Generator逆向工程（根据数据表生成实体，mapper,xxxxmapper.xml）
什么是MyBatis Generator ?  
MyBatis Generator是一个可以用来生成Mybatis dao,entity,Mapper文件的一个工具,在项目的过程中可以省去很多重复的工作,我们只要在MyBatis Generator的配置文件中配置好要生成的表名与包名，然后运行一条命令就会生成一堆文件。 

使用方法是：  
1.导入依赖包（推荐在maven仓库查询使用最新依赖包）  
2.编写配置文件（可以直接使用此工程里的generatorConfig.xml 和 jdbc.properties，注意修改jdbc.properties中的数据库连接信息和生成mapper、实体的位置）  
3.编写运行方法，如下  

```
public class MGB {

    public static void main(String[] args) throws Exception {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        //指向逆向工程配置文件，只需要把下面这个文件改为你自己写的配置文件即可
        File configFile = new File("C:\\CMS\\CMS\\generator\\src\\main\\" +
                "resources\\generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
}

```
详情移步：https://github.com/Higmin/practice/tree/master/src/main/java/com/practice/reverseEngineering  

未完待续...  


### Support or Contact

Having trouble with Pages? Check out our [documentation](https://help.github.com/categories/github-pages-basics/) or [contact support](https://github.com/contact) and we’ll help you sort it out.
