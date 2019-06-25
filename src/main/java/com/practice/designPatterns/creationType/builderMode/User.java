package com.practice.designPatterns.creationType.builderMode;

/**
 * @Auther : guojianmin
 * @Date : 2019/6/25 8:30
 * @Description : 建造者模式
 * 经常碰见的 XxxBuilder 的类，通常都是建造者模式的产物。建造者模式其实有很多的变种，但是对于客户端来说，我们的使用通常都是一个模式的：
 * Food food = new FoodBuilder().a().b().c().build();
 * Food food = Food.builder().a().b().c().build();
 * 套路就是：先 new 一个 Builder，然后可以链式地调用一堆方法，最后再调用一次 build() 方法，我们需要的对象就有了。
 *核心是：先把所有的属性都设置给 Builder，然后 build() 方法的时候，将这些属性复制给实际产生的对象。
 *
 * 另外：
 * 如果你只是想要链式写法，不想要建造者模式，有个很简单的办法，User 的 getter 方法不变，所有的 setter 方法都让其 return this 就可以了，然后就可以像下面这样调用：User user = new User().setName("").setPassword("").setAge(20);
 *User user = new User().setName("").setPassword("").setAge(20);
 */
public class User {
    // 下面是“一堆”的属性
    private String name;
    private String password;
    private String desc;
    private int age;

    // 构造方法私有化，不然客户端就会直接调用构造方法了
    private User(String name, String password, String desc, int age) {
        this.name = name;
        this.password = password;
        this.desc = desc;
        this.age = age;
    }

    // 静态方法，用于生成一个 Builder，这个不一定要有，不过写这个方法是一个很好的习惯，
    // 有些代码要求别人写 new User.UserBuilder().a()...build() 看上去就没那么好
    public static UserBuilder builder(){
        return new UserBuilder();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", desc='" + desc + '\'' +
                ", age=" + age +
                '}';
    }

    public static class UserBuilder {
        // 下面是和 User 一模一样的一堆属性
        private String name;
        private String password;
        private String desc;
        private int age;

        // 构造方法私有化
        private UserBuilder() {
        }

        // 链式调用设置各个属性值，返回 this，即 UserBuilder
        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder desc(String desc) {
            this.desc = desc;
            return this;
        }

        public UserBuilder age(int age) {
            this.age = age;
            return this;
        }

        // build() 方法负责将 UserBuilder 中设置好的属性“复制”到 User 中。
        // 当然，可以在 “复制” 之前做点检验
        public User build(){
            if (name == null || password == null){
                throw new RuntimeException("用户名和密码必填");
            }
            if (age <= 0 || age >= 150) {
                throw new RuntimeException("年龄不合法");
            }
            // 还可以做赋予”默认值“的功能
            if (desc == null) {
                desc = name;
            }
            return new User(name, password, desc, age);
        }
    }

}
