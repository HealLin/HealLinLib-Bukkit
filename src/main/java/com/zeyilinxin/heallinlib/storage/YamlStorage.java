package com.zeyilinxin.heallinlib.storage;

import com.zeyilinxin.heallinlib.player.PlayerConfigManager;
import com.zeyilinxin.heallinlib.plugin.HealLinPlugin;

import java.sql.Connection;

public class YamlStorage implements Storage {

    private PlayerConfigManager playerConfigManager;

    public YamlStorage(HealLinPlugin healLinPlugin, String url , String[] args) {
        this.playerConfigManager = playerConfigManager;
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
        return (T) this.playerConfigManager;
    }
}
