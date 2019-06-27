package com.practice.designPatterns.structuralType.adaptorMode.clazzAdapter;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;

/**
 * @Auther : guojianmin
 * @Date : 2019/6/27 15:41
 * @Description : 适配器，它用于实现 FileAlterationListener 接口，但是所有的方法都是空方法，这样，我们就可以转而定义自己的类来继承下面这个类即可。
 */
public class FileAlterationListenerAdaptor implements FileAlterationListener {
    @Override
    public void onStart(FileAlterationObserver fileAlterationObserver) {

    }

    @Override
    public void onDirectoryCreate(File file) {

    }

    @Override
    public void onDirectoryChange(File file) {

    }

    @Override
    public void onDirectoryDelete(File file) {

    }

    @Override
    public void onFileCreate(File file) {

    }

    @Override
    public void onFileChange(File file) {

    }

    @Override
    public void onFileDelete(File file) {

    }

    @Override
    public void onStop(FileAlterationObserver fileAlterationObserver) {

    }
}
