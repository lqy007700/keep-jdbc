package com.java.sqlSession.impl;

import com.java.pojo.Configuration;
import com.java.sqlSession.SqlSession;
import com.java.sqlSession.SqlSessionFactory;

public class SqlSessionFactoryImpl implements SqlSessionFactory {
    private Configuration configuration;

    public SqlSessionFactoryImpl(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new SqlSessionImpl(configuration);
    }
}
