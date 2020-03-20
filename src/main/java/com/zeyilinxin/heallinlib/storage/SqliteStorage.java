package com.zeyilinxin.heallinlib.storage;

import com.zeyilinxin.heallinlib.plugin.HealLinPlugin;

import java.sql.Connection;

public class SqliteStorage implements Storage{
    public SqliteStorage(HealLinPlugin healLinPlugin, String url , String[] args) {

    }

    @Override
    public Connection getConnection() {
        return null;
    }

    @Override
    public boolean createTable(String sql) {
        return false;
    }

    @Override
    public <T> T getPlugin() {
        return null;
    }
}
