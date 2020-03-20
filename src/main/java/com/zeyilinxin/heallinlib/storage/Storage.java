package com.zeyilinxin.heallinlib.storage;

import java.sql.Connection;
import java.sql.SQLException;

public interface Storage {

    Connection getConnection() throws SQLException;

    boolean createTable(String sql) throws SQLException ;

    <T> T getPlugin();
}
