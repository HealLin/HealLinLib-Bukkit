package com.heallin.api.bukkit.entitiy.ic;

import com.heallin.api.bukkit.entitiy.CoreEntity;
import lombok.Getter;
import net.minecraft.server.v1_12_R1.Entity;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;

import java.util.UUID;

public class ICoreEntity implements CoreEntity {

    @Getter
    private CraftEntity craftEntity;
    @Getter
    private Entity entity;

    public ICoreEntity(CraftEntity craftEntity){
        this.craftEntity = craftEntity;
        this.entity = craftEntity.getHandle();
    }

    @Override
    public String getName() {
        return this.entity.getName();
    }

    @Override
    public UUID getUUID() {
        return this.entity.getUniqueID();
    }


}
