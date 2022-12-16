package com.java.sqlSession;

import java.util.List;

public interface SqlSession {

    public <E> List<E> findAll(String statementId, Object... params) throws Exception;

    public <T> T findOne(String statementId, Object... params) throws Exception;

    // 为dao接口生成代理实现类
    public <T> T getMapper(Class<?> mapperClass);
}
