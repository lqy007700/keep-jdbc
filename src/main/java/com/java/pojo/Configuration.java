package com.java.pojo;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class Configuration {
    private DataSource dataSource;

    /**
     * key:statementId val:MappedStatement对象
     */
    Map<String, MappedStatement> mappedStatementMap = new HashMap<>();

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, MappedStatement> getMappedStatementMap() {
        return mappedStatementMap;
    }

    public void setMappedStatementMap(Map<String, MappedStatement> mappedStatementMap) {
        this.mappedStatementMap = mappedStatementMap;
    }
}