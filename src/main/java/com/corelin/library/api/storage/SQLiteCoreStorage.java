package com.corelin.library.api.storage;

import com.corelin.library.plugin.CoreLinPlugin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @描述: TODO
 * @作者: 择忆霖心
 * @author: 择忆霖心
 * @时间: 2020/6/7 21:24
 * @版本: 1.0
 */
public class SQLiteCoreStorage implements CoreStorage {

    private Connection connection;
    private String url = "";
    private String dbName;
    private CoreLinPlugin plugin;
    private String user;
    private String password;

    public SQLiteCoreStorage(CoreLinPlugin plugin, String url , String user , String password , String dbName) {
        this.plugin = plugin;
        this.user = user;
        this.password = password;
        this.dbName = dbName;
        if (url != null || !url.isEmpty()){
            this.url = url;
        }
        try {
            Class.forName("org.sqlite.JDBC");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Connection getConnection() throws SQLException {
        if (this.connection == null || this.connection.isClosed()){
            if (!url.isEmpty()){
                this.connection =   DriverManager.getConnection("jdbc:sqlite:" + url);
            }else{
                this.connection =   DriverManager.getConnection("jdbc:sqlite:plugins/" + this.plugin.getPluginName() + "/" + this.dbName + ".db");
            }

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
            e.printStackTrace();
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
