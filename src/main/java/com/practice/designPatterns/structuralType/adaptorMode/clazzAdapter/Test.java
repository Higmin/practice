package com.practice.designPatterns.structuralType.adaptorMode.clazzAdapter;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.HiddenFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * @Auther : guojianmin
 * @Date : 2019/6/27 15:40
 * @Description : 适配器模式：类适配器（默认适配器）。
 *
 * 需求:
 * 我们用 Appache commons-io 包中的 FileAlterationListener 做例子，此接口定义了很多的方法，用于对文件或文件夹进行监控，一旦发生了对应的操作，就会触发相应的方法。
 * 此接口的一大问题是抽象方法太多了，如果我们要用这个接口，意味着我们要实现每一个抽象方法，如果我们只是想要监控文件夹中的文件创建和文件删除事件，
 * 可是我们还是不得不实现所有的方法，很明显，这不是我们想要的。所以，我们需要下面的一个适配器，它用于实现上面的接口，但是所有的方法都是空方法，
 * 这样，我们就可以转而定义自己的类来继承下面这个类，然后重写我们需要的方法即可。
 *
 */
public class Test {

    public static void main(String[] args) {
        FileMonitor fileMonitor = new FileMonitor();
        File file = new File("D:\\FTPServerPath\\test");

        // 轮询间隔 5 秒
        long interval = TimeUnit.SECONDS.toMillis(1);
        // 创建过滤器
        IOFileFilter directories = FileFilterUtils.and(FileFilterUtils.directoryFileFilter(), HiddenFileFilter.VISIBLE);
        IOFileFilter files = FileFilterUtils.and(FileFilterUtils.fileFileFilter(), FileFilterUtils.suffixFileFilter(".txt"));
        IOFileFilter filter = FileFilterUtils.or(directories, files);
        // 使用过滤器
        FileAlterationObserver observer = new FileAlterationObserver(file, filter);
        //不使用过滤器
        //FileAlterationObserver observer = new FileAlterationObserver(new File(rootDir));
        observer.addListener(fileMonitor);//将自己实现的 监控文件夹中的文件创建和文件删除事件 的类，注册到监听器中
        //创建文件变化监听器
        FileAlterationMonitor monitor = new FileAlterationMonitor(interval, observer);
        // 开始监控
        try {
            monitor.start();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
