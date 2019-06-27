package com.practice.designPatterns.structuralType.adaptorMode.clazzAdapter;

import java.io.File;

/**
 * @Auther : guojianmin
 * @Date : 2019/6/27 15:42
 * @Description : 我们需要的几个方法；我们只是想要监控文件夹中的文件创建和文件删除事件
 */
public class FileMonitor extends FileAlterationListenerAdaptor {

    @Override
    public void onFileCreate(File file) {
        System.out.println("在这里，监控文件的创建事件");
    }

    @Override
    public void onFileDelete(File file) {
        System.out.println("在这里，监控文件的删除事件");
    }
}
