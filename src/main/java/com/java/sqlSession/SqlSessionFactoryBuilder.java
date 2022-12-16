package com.java.sqlSession;

import com.java.config.XmlConfigBuilder;
import com.java.pojo.Configuration;
import com.java.sqlSession.impl.SqlSessionFactoryImpl;

import java.io.InputStream;

public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(InputStream in) throws Exception {
        // 使用dom4j解析配置文件
        XmlConfigBuilder xmlConfigBuilder = new XmlConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parseConfig(in);

        // 创建sqlSessionFactory对象
        SqlSessionFactoryImpl sqlSessionFactory = new SqlSessionFactoryImpl(configuration);

        return sqlSessionFactory;
    }
}
