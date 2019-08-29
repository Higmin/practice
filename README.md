## 欢迎大家来到 Higmin GitHub Project！   

创建此工程是为了巩固基础知识 当然能对社区做点贡献也是我小小的心愿~  

本工程包含了 SpringAOP，死锁，同步锁，读 - 写同步锁，ThreadLocal使用,JUC线程池和Spring提供的线程池,jdk 1.8 中的日期时间API,数据结构（待完善），生成XML文件工具类，防止XSS漏洞攻击解决办法,mybatis逆向工程，接口并发测试，BIO，NIO， AIO，Netty的服务，客户端... 并且在不断更新中,有不足之处请留言指出。  

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



### 3. Java 并发编程之 volatile 关键字

volatile关键字包含两层语义:  
3.1 保证了不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量的值，这新值对其他线程来说是立即可见的。（保证可见性）  
3.2 禁止进行指令重排序。（保证有序性）  
划个重点：volatile 关键字能保证可见性和有序性，但是不能保证操作的原子性。  
可见性只能保证每次读取的是最新的值，但是volatile没办法保证对变量的操作的原子性。  
本工程中的代码示例有：
a.volatile 不保证原子性  
b.使用synchronized关键字,Lock,AtomicInteger来解决原子性的操作的问题  
具体的详解可以在这里查看：https://blog.csdn.net/weixin_38497019/article/details/99430092  

代码源码移步至：https://github.com/Higmin/practice/tree/master/src/main/java/com/practice/concurrent/keyWord_volatile  

### 4. Java 锁机制之Lock
Lock接口比同步方法和同步块（这里的同步就是考察Synchronized关键字）提供了更具扩展性的锁操作。Lock不是Java语言内置的，synchronized是Java语言的关键字，因此是内置特性，Lock是一个类，通过这个类可以实现同步访问；他们允许更灵活的结构，可以具有完全不同的性质，并且可以支持多个相关类的条件对象。它的优势有：可以使锁更公平；可以使线程在等待锁的时候响应中断；可以让线程尝试获取锁，并在无法获取锁的时候立即返回或者等待一段时间；可以在不同的范围，以不同的顺序获取和释放锁  
本工程中是从死锁、如何避免死锁、同步锁、读-写同步锁方面来介绍。

新增了 趣味练习：synchronized + 线程的挂起与唤醒，猜猜看输出什么？ https://github.com/Higmin/practice/tree/master/src/main/java/com/practice/lock/Test09_practice.java 

详情移步：https://github.com/Higmin/practice/tree/master/src/main/java/com/practice/lock
这里只是基础部分的示例，关于更全面的锁机制介绍，包括对公平锁，非公平锁，乐观锁，悲观锁，和分布式锁等的介绍或者导读，推荐一篇文章写的挺全面的，感兴趣的小伙伴可以去观摩一番：https://www.cnblogs.com/tison/p/8283233.html

### 5. ThreadLocal使用

ThreadLocal的官方API解释为:  
“该类提供了线程局部 (thread-local) 变量。这些变量不同于它们的普通对应物，因为访问某个变量（通过其 get 或 set 方法）的每个线程都有自己的局部变量，它独立于变量的初始化副本。ThreadLocal 实例通常是类中的 private static 字段，它们希望将状态与某一个线程（例如，用户 ID 或事务 ID）相关联。”  

大概的意思有两点：  
a. ThreadLocal提供了一种访问某个变量的特殊方式：访问到的变量属于当前线程，即保证每个线程的变量不一样，而同一个线程在任何地方拿到的变量都是一致的，这就是所谓的线程隔离。  
b. 如果要使用ThreadLocal，通常定义为private static类型，在我看来最好是定义为private static final类型。  

应用场景:  
ThreadLocal通常用来共享数据，当你想在多个方法中使用某个变量，这个变量是当前线程的状态，其它线程不依赖这个变量，你第一时间想到的就是把变量定义在方法内部，然后再方法之间传递参数来使用，这个方法能解决问题，但是有个烦人的地方就是，每个方法都需要声明形参，多处声明，多处调用。影响代码的美观和维护。有没有一种方法能将变量像private static形式来访问呢？这样在类的任何一处地方就都能使用。这个时候ThreadLocal大显身手了。  

详情移步：
https://github.com/Higmin/practice/tree/master/src/main/java/com/practice/concurrent/threadLocal  

### 6. 线程池
在Java中，我们一般通过集成Thread类和实现Runnnable接口，调用线程的start()方法实现线程的启动。但如果并发的数量很多，而且每个线程都是执行很短的时间便结束了，那样频繁的创建线程和销毁进程会大大的降低系统运行的效率。线程池正是为了解决多线程效率低的问题而产生的，他使得线程可以被复用，就是线程执行结束后不被销毁，而是可以继续执行其他任务。（这里可以用tomcat做例子进行思考）  
很多人想问，线程池听起来高大上，但在实际工作中却很少使用。其实不然，在各种流行框架或者高性能的架构中，池化技术是无处不在的。  

本工程中从JUC中ThreadPoolExecutor类的四个应用和Spring的线程池开始介绍：  
通常我们创建线程池都是通过Executors 工厂方法 Executors.newCachedThreadPool()（无界线程池，可以进行自动线程回收）、Executors.newFixedThreadPool(int)（固定大小线程池）和Executors.newSingleThreadExecutor()（单个后台线程），Executors.newScheduledThreadPool（定时，延时线程池）它们均为大多数使用场景预定义了设置。本工程中对这四种用法进行了简单的示例：  
详情移步至：https://github.com/Higmin/practice/tree/master/src/main/java/com/practice/concurrent/threadPool  
但是这样简单的应用在生产中难免不出问题，所以，我们需要介绍一下Spring为我们提供的线程池技术ThreadPoolTaskExecutor  
其实，它的实现方式完全是使用ThreadPoolExecutor进行实现（有点类似于装饰者模式。当然Spring提供的功能更加强大些，因为还有定时调度功能）。  
具体的介绍和使用配置在工程示例中已经写的非常非常详细了，详情移步： https://github.com/Higmin/practice/tree/master/src/main/java/com/practice/concurrent/threadPool/ThreadPoolTaskExecutorTest.java  

### 7. Lambda表达式

#### 7.1 什么是Lambda表达式:  
Java 8 的一个大亮点是引入Lambda表达式，使用它设计的代码会更加简洁。  
当开发者在编写Lambda表达式时，也会随之被编译成一个函数式接口。  
-> 是Java 8新增的Lambda表达式中，变量和临时代码块的分隔符，即：  
(变量) -> {代码块}  
如果代码块只有一个表达式，大括号可以省略。如果变量类型可以自动推断出来，可以不写变量类型。  
::是类似于C++的域运算符，获取方法使用的。  
stream()也是JDK8新增的流，你的表达式中将list转换为流，就可以惰性处理，这样只有变量要用的时候才会被调用。  
本工程中着重Lambda表达式使用。  

了解了Lambda表达式的使用，我们再来看看函数式接口：ava 8增加了两个新的概念在接口声明的时候： 默认方法和静态方法。  
#### 7.2 默认方法允许我们在接口里添加新的方法，而不会破坏实现这个接口的已有类的兼容性，也就是说不会强迫实现接口的类实现默认方法。接口可以提供一个默认的方法实现，所有这个接口的实现类都会通过继承得倒这个方法（如果有需要也可以重写这个方法）  
#### 7.3 接口里可以声明静态方法，并且可以在接口中实现。  
#### 7.4 方法引用：方法引用提供了一个很有用的语义来直接访问类或者实例的已经存在的方法或者构造方法， 结合Lambda表达式，方法引用使语法结构紧凑简明。不需要复杂的引用。  

详情移步：https://github.com/Higmin/practice/tree/master/src/main/java/com/practice/java8/Lambda  

#### 7.5 在这里顺便整理一下 jdk 1.8 中的日期时间API：  
介绍一下两个概念：绝对时间和时区  
7.5.1.绝对时间： 是指从1970年01月01日00时00分00秒 到此刻的时间，全世界都一样。  
注意：1970年01月01日00时00分00秒(北京时间1970年01月01日08时00分00秒)  
7.5.2.时区 是符合人们习惯的一种辅助计时方法，按照经线从东到西将绝对时间做了重新划分以方便全球不同经度的地区计时，现今全球共分为24个时区，并且规定相邻区域的时间相差1小时  
本工程中举例了 Clock 、Instant、LocalDateTime、和 DateTimeFormatter 的用法。以及一些常用的工具类（Date转LocalDateTime，LocalDateTime转Date，LocalDateTime转指定格式（例如：yyyy-MM-dd HH:mm:ss ）的String类型）  
详情移步：https://github.com/Higmin/practice/tree/master/src/main/java/com/practice/java8/date

关于 jdk 1.8 更多新特性，可以在这里了解更多：http://ifeve.com/java-8-features-tutorial  

### 8. 设计模式（未完待续...）

使用设计模式是为了重用代码、让代码更容易被他人理解、保证代码可靠性。大致可以分为三类：创建型，结构型，和功能型。  
工程中的具体代码示例情况如下：  
创建型：抽象工厂模式（完成）、工厂方法（完成）、建造者模式（完成）、原型模式（完成）、单例模式（完成）  
结构型：适配器模式（完成）、桥接模式、组合模式、外观模式、装饰者模式、享元模式、代理模式（完成）  
行为型：责任链模式、命令模式、解释器模式、迭代模式、中介者模式、备忘录模式、观察者模式（完成）、状态模式、策略模式（完成）、模板方法模式、访问者模式   

本工程中通过案例的模式展示设计模式通常的写法，加强理解。  

详情移步：https://github.com/Higmin/practice/tree/master/src/main/java/com/practice/designPatterns  

### 9. 防止XSS漏洞攻击解决办法

第1步：创建包装request的类 XssHttpServletRequestWrapper  
第2步：自定义过滤器过滤器拦截请求（创建过滤器）  
第3步：添加过滤器  
这里需要注意一点的是@WebFilter这个注解是Servlet3.0的规范，并不是Spring boot提供的。除了这个注解以外，我们还需在配置类中加另外一个注解：@ServletComponetScan，指定扫描的包。  

本工程中以 SpringBoot 项目 为示例，写了具体实现，以供参考。

详情移步：https://github.com/Higmin/practice/tree/master/src/main/java/com/practice/xssFilter  

### 10. MyBatis Generator逆向工程（根据数据表生成实体，mapper,xxxxmapper.xml）
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
