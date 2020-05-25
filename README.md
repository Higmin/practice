## 欢迎大家来到 Higmin GitHub Project！   

创建此工程是为了巩固基础知识 当然能对社区做点贡献也是我小小的心愿~  

本工程包含了 SpringAOP，死锁，JUC同步锁，读-写同步锁，ThreadLocal使用,JUC线程池和Spring提供的线程池,jdk 1.8 中的日期时间API,数据结构中 图的实现及操作和广度优先遍历/深度优先遍历（其他待完善），生成XML文件工具类，防止XSS漏洞攻击解决办法,mybatis逆向工程，接口并发测试，BIO，NIO， AIO，Netty的服务，客户端... 并且在不断更新中,有不足之处请留言指出。  

### 一、 Spring  
Spring 是一个开源框架，是为了解决企业应用程序开发复杂性而创建的。框架的主要优势之一就是其分层架构，分层架构允许您选择使用哪一个组件，同时为 J2EE 应用程序开发提供集成的框架。  
#### 1.1 SpringAOP  
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

详情移步：https://github.com/higminteam/practice/blob/master/src/main/java/com/practice/spring  
````
另外在配置 AOP 切面之前，我们需要了解下 aspectj 相关注解的作用：  
 
 @Aspect：声明该类为一个注解类；  
 @Pointcut：定义一个切点，后面跟随一个表达式，表达式可以定义为切某个注解，也可以切某个 package 下的方法；
 切点定义好后，就是围绕这个切点做文章了：  
 
 @Before: 在切点之前，织入相关代码；  
 @After: 在切点之后，织入相关代码;  
 @AfterReturning: 在切点返回内容后，织入相关代码，一般用于对返回值做些加工处理的场景；  
 @AfterThrowing: 用来处理当织入的代码抛出异常后的逻辑处理;  
 @Around: 环绕，可以在切入点前后织入代码，并且可以自由的控制何时执行切点；
````
   
 - [应用：使用自定义注解 和 AOP 切面统一打印出入参日志](https://github.com/higminteam/practice/blob/master/src/main/java/com/practice/spring/springAOP_ApplicationScenario/aop_web_log_request_aspect)  
 
#### 1.2 自定义 Spring 工具类 SpringUtils.java
写的一个工具类，对于非Spring管理的类 可以通过此类 来获取 Spring 容器中的 ApplicationContext 和 Spring 容器中的 Bean。  
获取 Spring 容器中的 Bean 时提供了三种获取方式：
 * 通过name获取 Bean.
 * 通过class获取Bean.
 * 通过name,以及Clazz返回指定的Bean

详情移步：https://github.com/higminteam/practice/blob/master/src/main/java/com/practice/spring/springUtils  

#### 1.3 Springboot 整合 Redis
##### 1. 加入Redis相关依赖:  
``
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
``
##### 2. application.properties中加入redis相关配置  
> spring.redis.database=0  # Redis数据库索引（默认为0）  
> spring.redis.host=192.168.0.24  # Redis服务器地址  
> spring.redis.port=6379  # Redis服务器连接端口  
> spring.redis.password=  # Redis服务器连接密码（默认为空）  
> spring.redis.pool.max-active=200  # 连接池最大连接数（使用负值表示没有限制））  
> spring.redis.pool.max-wait=-1  # 连接池最大阻塞等待时间（使用负值表示没有限制）  
> spring.redis.pool.max-idle=10 # 连接池中的最大空闲连接   
> spring.redis.pool.min-idle=0  # 连接池中的最小空闲连接   
> spring.redis.timeout=1000  # 连接超时时间（毫秒）  
##### 3. 写一个redis配置类  
 详情请见：https://github.com/higminteam/practice/blob/master/src/main/java/com/practice/spring/springbootAndRedis  

##### 4. 利用redis 实现分布式锁
思路：这个主要利用redis的setnx命令进行，setnx："set if not exists"就是如果不存在则成功设置缓存同时返回1，否则返回0 。  
 详情请见：https://github.com/higminteam/practice/blob/master/src/main/java/com/practice/concurrent/distributedLock/DistributedLockByRedis.java  
#### 1.4 Springboot 自定义注解
Spring自定义注解实现参数校验。  
本例中以校验MAC地址（格式：080027004C44）为例，实现参数校验自定义注解及检验规则。  

详情请见：https://github.com/higminteam/practice/blob/master/src/main/java/com/practice/spring/customizeValid  

#### 1.5 Springboot 全局异常处理
利用SpringAOP 面向切面编写全局异常处理。 
本例是基于SpringBoot的全局异常处理，提供返回的结果集。

详情请见：https://github.com/higminteam/practice/blob/master/src/main/java/com/practice/spring/exception  

### 二、 IO

java Io流共涉及40多个类，这些类看上去很杂乱，但实际上很有规则，而且彼此之间存在非常紧密的联系， Java Io流的40多个类都是从如下4个抽象类基类中派生出来的。   
InputStream/Reader: 所有的输入流的基类，前者是字节输入流，后者是字符输入流。  
OutputStream/Writer: 所有输出流的基类，前者是字节输出流，后者是字符输出流。   
本工程中的IO，主要是从BIO,NIO,AIO方面来梳理的，然后在这之后还加了一个基于NIO实现的Netty服务端，客户端示例以便理解。  
BIO:同步非阻塞  
NIO:同时支持阻塞与非阻塞模式，但这里我们以其同步非阻塞I/O模式来说明，并且是面向缓冲区的  
AIO:异步非阻塞I/O模型   

详情移步：https://github.com/higminteam/practice/blob/master/src/main/java/com/practice/io

### 三、 Java 并发编程之 volatile 关键字

volatile关键字包含两层语义:  
3.1 保证了不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量的值，这新值对其他线程来说是立即可见的。（保证可见性）  
3.2 禁止进行指令重排序。（保证有序性）  
划个重点：volatile 关键字能保证可见性和有序性，但是不能保证操作的原子性。  
可见性只能保证每次读取的是最新的值，但是volatile没办法保证对变量的操作的原子性。  
本工程中的代码示例有：
a.volatile 不保证原子性  
b.使用synchronized关键字,Lock,AtomicInteger来解决原子性的操作的问题  
具体的详解可以在这里查看：https://blog.csdn.net/weixin_38497019/article/details/99430092  

代码源码移步至：https://github.com/higminteam/practice/blob/master/src/main/java/com/practice/concurrent/keyWord_volatile  

### 四、 Java 锁机制之Lock
Lock接口比同步方法和同步块（这里的同步就是考察Synchronized关键字）提供了更具扩展性的锁操作。Lock不是Java语言内置的，synchronized是Java语言的关键字，因此是内置特性，Lock是一个类，通过这个类可以实现同步访问；他们允许更灵活的结构，可以具有完全不同的性质，并且可以支持多个相关类的条件对象。它的优势有：可以使锁更公平；可以使线程在等待锁的时候响应中断；可以让线程尝试获取锁，并在无法获取锁的时候立即返回或者等待一段时间；可以在不同的范围，以不同的顺序获取和释放锁  
本工程中是从死锁、如何避免死锁、同步锁、读-写同步锁方面来介绍。

新增了 趣味练习：synchronized + 线程的挂起与唤醒，猜猜看输出什么？ https://github.com/Higmin/practice/tree/master/src/main/java/com/practice/lock/Test09_practice.java 

详情移步：https://github.com/higminteam/practice/blob/master/src/main/java/com/practice/lock
这里只是基础部分的示例，关于更全面的锁机制介绍，包括对公平锁，非公平锁，乐观锁，悲观锁，和分布式锁等的介绍或者导读，推荐一篇文章写的挺全面的，感兴趣的小伙伴可以去观摩一番：https://www.cnblogs.com/tison/p/8283233.html

### 五、 ThreadLocal使用

ThreadLocal的官方API解释为:  
“该类提供了线程局部 (thread-local) 变量。这些变量不同于它们的普通对应物，因为访问某个变量（通过其 get 或 set 方法）的每个线程都有自己的局部变量，它独立于变量的初始化副本。ThreadLocal 实例通常是类中的 private static 字段，它们希望将状态与某一个线程（例如，用户 ID 或事务 ID）相关联。”  

大概的意思有两点：  
a. ThreadLocal提供了一种访问某个变量的特殊方式：访问到的变量属于当前线程，即保证每个线程的变量不一样，而同一个线程在任何地方拿到的变量都是一致的，这就是所谓的线程隔离。  
b. 如果要使用ThreadLocal，通常定义为private static类型，在我看来最好是定义为private static final类型。  

应用场景:  
ThreadLocal通常用来共享数据，当你想在多个方法中使用某个变量，这个变量是当前线程的状态，其它线程不依赖这个变量，你第一时间想到的就是把变量定义在方法内部，然后再方法之间传递参数来使用，这个方法能解决问题，但是有个烦人的地方就是，每个方法都需要声明形参，多处声明，多处调用。影响代码的美观和维护。有没有一种方法能将变量像private static形式来访问呢？这样在类的任何一处地方就都能使用。这个时候ThreadLocal大显身手了。  

详情移步：https://github.com/higminteam/practice/blob/master/src/main/java/com/practice/concurrent/threadLocal  

### 六、 线程池
在Java中，我们一般通过集成Thread类和实现Runnnable接口，调用线程的start()方法实现线程的启动。但如果并发的数量很多，而且每个线程都是执行很短的时间便结束了，那样频繁的创建线程和销毁进程会大大的降低系统运行的效率。线程池正是为了解决多线程效率低的问题而产生的，他使得线程可以被复用，就是线程执行结束后不被销毁，而是可以继续执行其他任务。（这里可以用tomcat做例子进行思考）  
很多人想问，线程池听起来高大上，但在实际工作中却很少使用。其实不然，在各种流行框架或者高性能的架构中，池化技术是无处不在的。  

本工程中从JUC中ThreadPoolExecutor类的四个应用和Spring的线程池开始介绍：  
通常我们创建线程池都是通过ThreadPoolExecutor 创建 CachedThreadPool（无界线程池，可以进行自动线程回收）、FixedThreadPool（固定大小线程池）和SingleThreadExecutor()（单个后台线程），ScheduledThreadPool（定时，延时线程池）它们均为大多数使用场景预定义了设置。本工程中对这四种用法进行了简单的示例：  
注意：线程池的创建不允许使用 Executors 去创建，而是通过ThreadPoolExecutor的方式去创建，这样的处理方式可以规避资源耗尽的风险。  
详情移步至：https://github.com/higminteam/practice/blob/master/src/main/java/com/practice/concurrent/threadPool  

但是这样简单的应用在生产中难免不出问题，所以，我们需要介绍一下Spring为我们提供的线程池技术ThreadPoolTaskExecutor  
其实，它的实现方式完全是使用ThreadPoolExecutor进行实现（有点类似于装饰者模式。当然Spring提供的功能更加强大些，因为还有定时调度功能）。  
具体的介绍和使用配置在工程示例中已经写的非常非常详细了，详情移步： https://github.com/Higmin/practice/tree/master/src/main/java/com/practice/concurrent/threadPool/ThreadPoolTaskExecutorTest.java  

### 七、 Lambda表达式

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

详情移步：https://github.com/higminteam/practice/blob/master/src/main/java/com/practice/java8/Lambda  

#### 7.5 在这里顺便整理一下 jdk 1.8 中的日期时间API：  
介绍一下两个概念：绝对时间和时区  
7.5.1.绝对时间： 是指从1970年01月01日00时00分00秒 到此刻的时间，全世界都一样。  
注意：1970年01月01日00时00分00秒(北京时间1970年01月01日08时00分00秒)  
7.5.2.时区 是符合人们习惯的一种辅助计时方法，按照经线从东到西将绝对时间做了重新划分以方便全球不同经度的地区计时，现今全球共分为24个时区，并且规定相邻区域的时间相差1小时  
本工程中举例了 Clock 、Instant、LocalDateTime、和 DateTimeFormatter 的用法。以及一些常用的工具类（Date转LocalDateTime，LocalDateTime转Date，LocalDateTime转指定格式（例如：yyyy-MM-dd HH:mm:ss ）的String类型）  
详情移步：https://github.com/higminteam/practice/blob/master/src/main/java/com/practice/java8/date

关于 jdk 1.8 更多新特性，可以在这里了解更多：http://ifeve.com/java-8-features-tutorial  

### 八、 设计模式（未完待续...）

#### 8.1 23种设计模式整理
使用设计模式是为了重用代码、让代码更容易被他人理解、保证代码可靠性。大致可以分为三类：创建型，结构型，和功能型。  
工程中的具体代码示例情况如下：  
创建型：抽象工厂模式（完成）、工厂方法（完成）、建造者模式（完成）、原型模式（完成）、单例模式（完成）  
详情移步：https://github.com/higminteam/practice/blob/master/src/main/java/com/practice/designPatterns/creationType  
结构型：适配器模式（完成）、桥接模式、组合模式、外观模式、装饰者模式、享元模式、代理模式（完成）  
详情移步：https://github.com/higminteam/practice/blob/master/src/main/java/com/practice/designPatterns/structuralType  
行为型：责任链模式、命令模式、解释器模式、迭代模式、中介者模式、备忘录模式、观察者模式（完成）、状态模式、策略模式（完成）、模板方法模式、访问者模式   
详情移步：https://github.com/higminteam/practice/blob/master/src/main/java/com/practice/designPatterns/behaviorType  

本工程中通过案例的模式展示设计模式通常的写法，加强理解。  

#### 8.2 生产者消费者模型（Producer-Consumer）
手写生产者消费者模型  
##### 8.2.1 利用阻塞队列实现  
详情移步：https://github.com/higminteam/practice/blob/master/src/main/java/com/practice/designPatterns/ProducerAndConsumer/blockingQueue  
##### 8.2.2 利用  wait/notify 机制 实现  
详情移步：https://github.com/higminteam/practice/blob/master/src/main/java/com/practice/designPatterns/ProducerAndConsumer/waitAndNotify  

### 九、 防止XSS漏洞攻击解决办法

第1步：创建包装request的类 XssHttpServletRequestWrapper  
第2步：自定义过滤器过滤器拦截请求（创建过滤器）  
第3步：添加过滤器  
这里需要注意一点的是@WebFilter这个注解是Servlet3.0的规范，并不是Spring boot提供的。除了这个注解以外，我们还需在配置类中加另外一个注解：@ServletComponetScan，指定扫描的包。  

本工程中以 SpringBoot 项目 为示例，写了具体实现，以供参考。

详情移步：https://github.com/higminteam/practice/blob/master/src/main/java/com/practice/xssFilter  

### 十、 MyBatis Generator逆向工程（根据数据表生成实体，mapper,xxxxmapper.xml）
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
详情移步：https://github.com/higminteam/practice/blob/master/src/main/java/com/practice/reverseEngineering  

### 十一、 警惕jvm自动拆装箱带来的性能损耗
警惕jvm自动拆装箱带来的性能损耗   能用值类型解决问题的情况下 坚决不适用  引用类型
本例中通过一个 count += i; 操作来输出int和Integer所用时长。  
示例请见： https://github.com/higminteam/practice/blob/master/src/main/java/com/practice/jvm/JVM_IntegerOrInt.java

### 十二、 数据结构和算法

#### 12.1 leetcode 算法解题记录
本小节记录自己在leetcode上的一些解题记录，项目创建比较晚，没有来得及都收集进来，但是小编也会尽量去补录，不断更新~~  
代码移步：https://github.com/higminteam/practice/blob/master/src/main/java/com/practice/algorithm/leetcode_passing  

#### 12.2 数据结构
##### 12.2.1 图的实现及操作和广度优先遍历/深度优先遍历
参考一篇优质博文：https://www.jianshu.com/p/62dbce1e6178  
再次感谢博主的分享。Test类中的测试图的原型，博主在文章中有详细的介绍，大家可参考理解。  
代码移步：https://github.com/higminteam/practice/blob/master/src/main/java/com/practice/dataStructure/graph  

#### 12.3 十大经典排序算法
本项目作为学习积累分享使用。 感谢博主分享，推荐大家去阅读。 
参考博客：  
https://www.cnblogs.com/onepixel/articles/7674659.html  
https://blog.csdn.net/morewindows/article/details/6684558  
 
>算法分类
十种常见排序算法可以分为两大类：
比较类排序：通过比较来决定元素间的相对次序，由于其时间复杂度不能突破O(nlogn)，因此也称为非线性时间比较类排序。
非比较类排序：不通过比较来决定元素间的相对次序，它可以突破基于比较排序的时间下界，以线性时间运行，因此也称为线性时间非比较类排序。

##### 12.3.1 冒泡排序（Bubble Sort）
冒泡排序是一种简单的排序算法。它重复地走访过要排序的数列，一次比较两个元素，如果它们的顺序错误就把它们交换过来。走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。这个算法的名字由来是因为越小的元素会经由交换慢慢“浮”到数列的顶端。 

>算法描述如下：  
①比较相邻的元素。如果第一个比第二个大，就交换它们两个；  
②对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；  
③针对所有的元素重复以上的步骤，除了最后一个；  
④重复步骤1~3，直到排序完成。  

代码移步：https://github.com/higminteam/practice/blob/master/src/main/java/com/practice/algorithm/sort/BubbleSort.java  

##### 12.3.2 选择排序（Selection Sort）
选择排序(Selection-sort)是一种简单直观的排序算法。它的工作原理：首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。n个记录的直接选择排序可经过n-1趟直接选择排序得到有序结果。 

>算法描述如下：  
①初始状态：无序区为R[1..n]，有序区为空；  
②第i趟排序(i=1,2,3…n-1)开始时，当前有序区和无序区分别为R[1..i-1]和R(i..n）。该趟排序从当前无序区中-选出关键字最小的记录 R[k]，将它与无序区的第1个记录R交换，使R[1..i]和R[i+1..n)分别变为记录个数增加1个的新有序区和记录个数减少1个的新无序区；  
③n-1趟结束，数组有序化了。  

代码移步：https://github.com/higminteam/practice/blob/master/src/main/java/com/practice/algorithm/sort/SelectionSort.java  
  
##### 12.3.3 插入排序（Insertion Sort）
插入排序（Insertion-Sort）的算法描述是一种简单直观的排序算法。它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。一般来说，插入排序都采用in-place在数组上实现。
>算法描述如下：  
①从第一个元素开始，该元素可以认为已经被排序；  
②取出下一个元素，在已经排序的元素序列中从后向前扫描；  
③如果该元素（已排序）大于新元素，将该元素移到下一位置；  
④重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；  
⑤将新元素插入到该位置后；  
⑥重复步骤2~5。  

代码待完善。。。  

##### 12.3.4 希尔排序（Shell Sort）
第一个突破O(n2)的排序算法，是简单插入排序的改进版。它与插入排序的不同之处在于，它会优先比较距离较远的元素。希尔排序又叫缩小增量排序。先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序。
>算法描述如下：  
①选择一个增量序列t1，t2，…，tk，其中ti>tj，tk=1；  
②按增量序列个数k，对序列进行k 趟排序；  
③每趟排序，根据对应的增量ti，将待排序列分割成若干长度为m 的子序列，分别对各子表进行直接插入排序。仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。  

代码待完善。。。  

##### 12.3.5 归并排序（Merge Sort）
归并排序是建立在归并操作上的一种有效的排序算法。该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。若将两个有序表合并成一个有序表，称为2-路归并。 

>算法描述如下：  
①把长度为n的输入序列分成两个长度为n/2的子序列；  
②对这两个子序列分别采用归并排序；  
③将两个排序好的子序列合并成一个最终的排序序列。  

代码待完善。。。  

##### 12.3.6 快速排序（Quick Sort） **重点**
快速排序的基本思想：通过一趟排序将待排记录分隔成独立的两部分，其中一部分记录的关键字均比另一部分的关键字小，则可分别对这两部分记录继续进行排序，以达到整个序列有序。  
概括来说为：挖坑填数+分治法。

>算法描述如下：   
①先从数列中取出一个数作为基准数；  
②分区过程，将比这个数大的数全放到它的右边，小于或等于它的数全放到它的左边；  
③再对左右区间重复第二步，直到各区间只有一个数。  

代码移步：https://github.com/higminteam/practice/blob/master/src/main/java/com/practice/algorithm/sort/QuickSort.java  

##### 12.3.7 堆排序（Heap Sort）
堆排序（Heapsort）是指利用堆这种数据结构所设计的一种排序算法。堆积是一个近似完全二叉树的结构，并同时满足堆积的性质：即子结点的键值或索引总是小于（或者大于）它的父节点。

>算法描述如下：  
①将初始待排序关键字序列(R1,R2….Rn)构建成大顶堆，此堆为初始的无序区；  
②将堆顶元素R[1]与最后一个元素R[n]交换，此时得到新的无序区(R1,R2,……Rn-1)和新的有序区(Rn),且满足R[1,2…n-1]<=R[n]；  
③由于交换后新的堆顶R[1]可能违反堆的性质，因此需要对当前无序区(R1,R2,……Rn-1)调整为新堆，然后再次将R[1]与无序区最后一个元素交换，得到新的无序区(R1,R2….Rn-2)和新的有序区(Rn-1,Rn)。不断重复此过程直到有序区的元素个数为n-1，则整个排序过程完成。  

代码待完善。。。  

##### 12.3.8 计数排序（Counting Sort）
计数排序不是基于比较的排序算法，其核心在于将输入的数据值转化为键存储在额外开辟的数组空间中。 作为一种线性时间复杂度的排序，计数排序要求输入的数据必须是有确定范围的整数。

>算法描述如下：  
①找出待排序的数组中最大和最小的元素；  
②统计数组中每个值为i的元素出现的次数，存入数组C的第i项；  
③对所有的计数累加（从C中的第一个元素开始，每一项和前一项相加）；  
④反向填充目标数组：将每个元素i放在新数组的第C(i)项，每放一个元素就将C(i)减去1。  

代码待完善。。。  

##### 12.3.9 桶排序（Bucket Sort）
桶排序是计数排序的升级版。它利用了函数的映射关系，高效与否的关键就在于这个映射函数的确定。桶排序 (Bucket sort)的工作的原理：假设输入数据服从均匀分布，将数据分到有限数量的桶里，每个桶再分别排序（有可能再使用别的排序算法或是以递归方式继续使用桶排序进行排）。
>算法描述如下：  
①设置一个定量的数组当作空桶；  
②遍历输入数据，并且把数据一个一个放到对应的桶里去；  
③对每个不是空的桶进行排序；  
④从不是空的桶里把排好序的数据拼接起来。   

代码待完善。。。  

##### 12.3.10 基数排序（Radix Sort）
基数排序是按照低位先排序，然后收集；再按照高位排序，然后再收集；依次类推，直到最高位。有时候有些属性是有优先级顺序的，先按低优先级排序，再按高优先级排序。最后的次序就是高优先级高的在前，高优先级相同的低优先级高的在前。
>算法描述如下：  
①取得数组中的最大数，并取得位数；  
②arr为原始数组，从最低位开始取每个位组成radix数组；  
③对radix进行计数排序（利用计数排序适用于小范围数的特点）；  

代码待完善。。。  

#### 12.4 最近最久未使用（LRU）算法
LRU是Least Recently Used的缩写，即最近最少使用，是一种常用的淘汰机制算法。  
常见的使用该算法的有：Redis缓存淘汰机制，这里我们用 java 手写一个 LRU 算法 ：我们采用 哈希表 + 双向链表 的数据结构实现。 
由于java 中已经有这样的数据结构了，比如 LinkedHashMap ，那么我们实现起来就简单多了。  
代码移步：https://github.com/higminteam/practice/blob/master/src/main/java/com/practice/algorithm/lru/LruSort.java  
#### 12.5 一致性哈希实现  
代码：https://github.com/higminteam/practice/blob/master/md/algorithm/consistentHash/ConsistentHash.md  

 * 进阶：  
 1. 考虑到路由（RouteHandle）算法有很多种：一致性哈希、随机路由、轮询、LRU   ==> 我们可以采用策略模式来实现不同的机制  
 2. 如果每种算法的实现方式有多种，我们可以通过选择提供配置来决定哪一种实现。
#### 12.6 其他
##### 12.6.1 要求不用数学库，求 sqrt (2)精确到小数点后10位(Java版)
本例使用二分法实现  
代码：https://github.com/higminteam/practice/blob/master/md/algorithm/other/Sqrt.java
##### 12.6.2 求 最大公约数  
本例使用辗转相除法（欧几里得算法）  
代码：https://github.com/higminteam/practice/blob/master/md/algorithm/other/GreatestCommonDivisor.java

### 十三、 Kafka 生产者消费者示例
本小节主要是用Java实现了Kafka 生产者和消费者，采用的是kafka_2.12,版本号在pom.xml中可以找到。  
#### 13.1 Kafka消费者消费位移确认有自动提交与手动提交两种策略。在创建KafkaConsumer对象时，通过参数enable.auto.commit设定，true表示自动提交（默认）。自动提交策略由消费者协调器（ConsumerCoordinator）每隔${auto.commit.interval.ms}毫秒执行一次偏移量的提交。手动提交需要由客户端自己控制偏移量的提交。  
(1)自动提交。在创建一个消费者时，默认是自动提交偏移量，当然我们也可以显示设置为自动。例如，我们创建一个消费者，该消费者自动提交偏移量。  
(2)手动提交。在有些场景我们可能对消费偏移量有更精确的管理，以保证消息不被重复消费以及消息不被丢失。假设我们对拉取到的消息需要进行写入数据库处理，或者用于其他网络访问请求等等复杂的业务处理，在这种场景下，所有的业务处理完成后才认为消息被成功消费，这种场景下，我们必须手动控制偏移量的提交。  
   Kafka 提供了异步提交（commitAsync）及同步提交（commitSync）两种手动提交的方式。两者的主要区别在于同步模式下提交失败时一直尝试提交，直到遇到无法重试的情况下才会结束，同时，同步方式下消费者线程在拉取消息时会被阻塞，直到偏移量提交操作成功或者在提交过程中发生错误。而异步方式下消费者线程不会被阻塞，可能在提交偏移量操作的结果还未返回时就开始进行下一次的拉取操作，在提交失败时也不会尝试提交。  
   实现手动提交前需要在创建消费者时关闭自动提交，即设置enable.auto.commit=false。然后在业务处理成功后调用commitAsync()或commitSync()方法手动提交偏移量。由于同步提交会阻塞线程直到提交消费偏移量执行结果返回，而异步提交并不会等消费偏移量提交成功后再继续下一次拉取消息的操作，因此异步提交还提供了一个偏移量提交回调的方法commitAsync(OffsetCommitCallback callback)。当提交偏移量完成后会回调OffsetCommitCallback 接口的onComplete()方法，这样客户端根据回调结果执行不同的逻辑处理。  
可以在安装Kafka之后，通过生产者和消费者main方法启动测试。  
官方文档：http://kafka.apache.org/21/javadoc/index.html?org/apache/kafka/clients/producer/KafkaProducer.html  
GitHub示例代码移步：https://github.com/higminteam/practice/blob/master/src/main/java/com/practice/kafka  
#### 13.2 以时间戳查询消息
   Kafka 在0.10.1.1 版本增加了时间戳索引文件，因此我们除了直接根据偏移量索引文件查询消息之外，还可以根据时间戳来访问消息。consumer-API 提供了一个offsetsForTimes(Map<TopicPartition, Long> timestampsToSearch)方法，该方法入参为一个Map 对象，Key 为待查询的分区，Value 为待查询的时间戳，该方法会返回时间戳大于等于待查询时间的第一条消息对应的偏移量和时间戳。需要注意的是，若待查询的分区不存在，则该方法会被一直阻塞。  

### 十四、 一些使用的工具类
Excel相关处理  
反射工具类. 提供调用getter/setter方法, 访问私有变量, 调用私有方法, 获取泛型类型Class, 被AOP过的真实类等工具函数。    
sql操作工具类  
字符集工具类  
类型转换器  
字符串格式化  
时间工具类  
全局配置类  
Servlet客户端工具类  
字符串工具类  
yml配置处理工具类   
代码移步：https://github.com/higminteam/practice/blob/master/src/main/java/com/practice/utils  


未完待续...  


### Support or Contact

Having trouble with Pages? Check out our [documentation](https://help.github.com/categories/github-pages-basics/) or [contact support](https://github.com/contact) and we’ll help you sort it out.
