package com.java.config;

import com.java.io.Resources;
import com.java.pojo.Configuration;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class XmlConfigBuilder {

    private Configuration configuration;

    public XmlConfigBuilder() {
        this.configuration = new Configuration();
    }

    /**
     * 将配置文件进行解析，封装configuration
     */
    public Configuration parseConfig(InputStream in) throws DocumentException, PropertyVetoException {
        Document read = new SAXReader().read(in);
        Element rootElement = read.getRootElement();

        // sqlMapConfig.xml 解析
        List<?> elements = rootElement.selectNodes("//property");
        Properties properties = new Properties();

        for (Object o : elements) {
            Element element = (Element) o;
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            properties.setProperty(name, value);
        }

        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(properties.getProperty("driverClass"));
        comboPooledDataSource.setJdbcUrl(properties.getProperty("jdbcUrl"));
        comboPooledDataSource.setUser(properties.getProperty("username"));
        comboPooledDataSource.setPassword(properties.getProperty("password"));
        configuration.setDataSource(comboPooledDataSource);

        // xxxMapper.xml 解析
        XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(configuration);
        List<Node> mappers = rootElement.selectNodes("//mapper");
        for (Node mapper : mappers) {
            Element element = (Element) mapper;
            String mapperPath = element.attributeValue("resource");
            InputStream resourceAsStream = Resources.getResourceAsStream(mapperPath);
            xmlMapperBuilder.parse(resourceAsStream);
        }
        return configuration;
    }
}
