package com.heallin.api.bukkit.manager;


import com.heallin.api.bukkit.entitiy.ic.ICorePlayer;
import com.heallin.api.bukkit.Core;
import com.heallin.api.bukkit.entitiy.CorePlayer;
import com.zeyilinxin.heallinlib.api.utils.ArrayListUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CorePlayerManager {

    private Core core;
    private Map<Player , CorePlayer> playerMap = new HashMap<>();

    public CorePlayerManager(Core core) {
        this.core = core;
    }

    public void onJoin(Player player){
        this.playerMap.put(player , new ICorePlayer(core , player));
    }

    public void onQuit(Player player){
        this.playerMap.remove(player);
    }

    public CorePlayer getCorePlayer(String name){
        for (Player p : playerMap.keySet()){
            if (p.getName().equals(name)){
                return this.playerMap.get(p);
            }
        }
        return null;
    }

    public CorePlayer getCorePlayer(UUID uuid){
        for (Player p : playerMap.keySet()){
            if (p.getUniqueId().equals(uuid)){
                return this.playerMap.get(p);
            }
        }
        return null;
    }

    public CorePlayer getCorePlayer(Player player){
        return this.playerMap.get(player);
    }

    public CorePlayer[] getOnlinePlayers(){
        return ArrayListUtils.listToArray(this.playerMap.values() , CorePlayer.class);
    }

    public CorePlayer[] getWorldPlayers(String worldName){
        ArrayList<CorePlayer> arrayList = new ArrayList<>();
        Bukkit.getWorld(worldName).getPlayers().forEach((i)->{
            arrayList.add(this.playerMap.get(i));
        });
        return ArrayListUtils.listToArray(arrayList , CorePlayer.class);
    }
}
