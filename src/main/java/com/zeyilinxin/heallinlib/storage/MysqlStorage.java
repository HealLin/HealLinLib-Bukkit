package com.zeyilinxin.heallinlib.storage;

import com.zeyilinxin.heallinlib.plugin.HealLinPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MysqlStorage implements Storage{

    private HealLinPlugin plugin;
    private String url;
    private String user;
    private String password;
    private Connection connection;

    public MysqlStorage(HealLinPlugin healLinPlugin, String url , String[] args) {
        this.plugin = healLinPlugin;
        this.url = url;
        this.user = args[0];
        this.password = args[1];
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
    public <T> T getPlugin() {
        return (T) this.plugin;
    }
}
