package com.java.pojo;

public class MappedStatement {
    // id
    private String id;

    // 返回值类型
    private String resourceType;

    // 参数类型
    private String parameterType;

    // sql
    private String sql;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setParamterType(String paramterType) {
        this.parameterType = paramterType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
