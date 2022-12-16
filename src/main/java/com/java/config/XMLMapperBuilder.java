package com.java.config;

import com.java.pojo.Configuration;
import com.java.pojo.MappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

public class XMLMapperBuilder {
    private final Configuration configuration;

    public XMLMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * 解析sql
     */
    public void parse(InputStream in) throws DocumentException {
        Document read = new SAXReader().read(in);

        Element rootElement = read.getRootElement();

        String namespace = rootElement.attributeValue("namespace");
        List<Node> elements = rootElement.selectNodes("//select");

        for (Node node : elements) {
            Element element = (Element) node;
            String id = element.attributeValue("id");
            String resultType = element.attributeValue("resultType");
            String paramterType = element.attributeValue("paramterType");
            String sql = element.getTextTrim();

            MappedStatement mappedStatement = new MappedStatement();
            mappedStatement.setId(id);
            mappedStatement.setParamterType(paramterType);
            mappedStatement.setResourceType(resultType);
            mappedStatement.setSql(sql);

            String key = namespace + "." + id;
            configuration.getMappedStatementMap().put(key, mappedStatement);
        }
    }
}
