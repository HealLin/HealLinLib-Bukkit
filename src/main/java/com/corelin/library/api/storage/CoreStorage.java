package com.corelin.library.api.storage;

import java.sql.Connection;
import java.sql.SQLException;

public interface CoreStorage {

    Connection getConnection() throws SQLException;

    /**
     * 创建表
     * @param sql
     * @return
     * @throws SQLException
     */
    boolean createTable(String sql) ;


    /**
     * 关闭数据库连接
     */
    void closeConnection();
}
