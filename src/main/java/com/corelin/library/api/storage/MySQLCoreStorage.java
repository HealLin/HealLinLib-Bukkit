package com.corelin.library.api.storage;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLCoreStorage implements CoreStorage {

    private String url;
    private String user;
    private String password;
    private Connection connection;

    public MySQLCoreStorage( String url , String user , String  password) {
        this.url = url;
        this.user = user;
        this.password = password;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        if (this.connection == null || this.connection.isClosed()){
            this.connection =  DriverManager.getConnection(url , user , password);
        }
        return this.connection;
    }

    @Override
    public boolean createTable(String sql) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.execute();
            statement.close();
            return true;
        } catch (SQLException e) {
            return false;
        }finally {
            try {
                if (statement != null){
                    statement.close();
                    statement = null;
                }
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
