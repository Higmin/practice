package com.practice.designPatterns.creationType.abstractFactoryMode;

import com.practice.designPatterns.creationType.abstractFactoryMode.factory.AmdFactory;
import com.practice.designPatterns.creationType.abstractFactoryMode.factory.IntelFactory;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang3.SerializationUtils;

import java.io.UnsupportedEncodingException;

/**
 * @Auther : guojianmin
 * @Date : 2019/6/21 16:47
 * @Description : TODO用一句话描述此类的作用
 */
public class Test {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "AC:DC:F4:D5:75:5B";
//        byte[] serialize = SerializationUtils.serialize(str);
        byte[] bytes = str.getBytes();
        for (byte b :bytes) {
            System.out.println(Integer.toHexString(b));
        }
    }
}
