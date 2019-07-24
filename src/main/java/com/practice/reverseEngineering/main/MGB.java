package com.practice.reverseEngineering.main;

//根据数据表生成实体，mapper,xxxxmapper.xml
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import sun.security.krb5.Config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Higmin
 * @date 2019/7/24 10:33
 **/
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
