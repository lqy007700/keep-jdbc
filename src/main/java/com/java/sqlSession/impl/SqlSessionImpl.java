package com.java.sqlSession.impl;

import com.java.pojo.Configuration;
import com.java.pojo.MappedStatement;
import com.java.sqlSession.SqlSession;

import java.lang.reflect.*;
import java.util.List;

public class SqlSessionImpl implements SqlSession {

    private final Configuration configuration;

    public SqlSessionImpl(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> findAll(String statementId, Object... params) throws Exception {
        ExecutorImpl executor = new ExecutorImpl();
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementId);
        List<Object> list = executor.query(configuration, mappedStatement, params);
        return (List<E>) list;
    }

    @Override
    public <T> T findOne(String statementId, Object... params) throws Exception {
        List<Object> all = findAll(statementId, params);
        if (all.size() == 1) {
            return (T) all.get(0);
        } else {
            throw new RuntimeException("查询结果为空或返回结果过多");
        }
    }

    //使用JDK动态代理，为dao接口生成代理对象并返回
    @Override
    public <T> T getMapper(Class<?> mapperClass) {
        Object o = Proxy.newProxyInstance(SqlSession.class.getClassLoader(), new Class[]{mapperClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String name = method.getName();
                String declaringClass = method.getDeclaringClass().getName();
                String statementId = declaringClass + "." + name;

                // 获取被调用方法的返回值类型
                Type genericReturnType = method.getGenericReturnType();
                if (genericReturnType instanceof ParameterizedType) {
                    return findAll(statementId, args);
                }
                return findOne(statementId, args);
            }
        });
        return (T) o;
    }
}
