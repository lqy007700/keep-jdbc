package com.java.sqlSession;

import com.java.pojo.Configuration;
import com.java.pojo.MappedStatement;

import java.sql.SQLException;
import java.util.List;

public interface Executor {
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws SQLException, Exception;
}
