package com.heallin.api.bukkit.version.R12.packet;

import net.minecraft.server.v1_12_R1.EntityLiving;
import net.minecraft.server.v1_12_R1.PacketPlayOutSpawnEntityLiving;
import org.bukkit.entity.EntityType;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class NewPacketPlayOutSpawnEntityLiving {

    private PacketPlayOutSpawnEntityLiving packetPlayOutSpawnEntityLiving;
    private Map<EntityType , Integer> entityTypeIntegerMap = new HashMap<>();

    public NewPacketPlayOutSpawnEntityLiving(){
        this.packetPlayOutSpawnEntityLiving = new PacketPlayOutSpawnEntityLiving();
    }

    public NewPacketPlayOutSpawnEntityLiving(EntityLiving entityLiving){
        this.packetPlayOutSpawnEntityLiving = new PacketPlayOutSpawnEntityLiving(entityLiving);
    }

    public void setEntityId(int entityId){
        try {
            Field field = this.packetPlayOutSpawnEntityLiving.getClass().getField("a");
            field.setAccessible(true);
            field.set(this.packetPlayOutSpawnEntityLiving , entityId);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public int getEntityId(){
        int id = 0;
        try {
            Field field = this.packetPlayOutSpawnEntityLiving.getClass().getField("a");
            field.setAccessible(true);
            id =  field.getInt(this.packetPlayOutSpawnEntityLiving);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return id;
    }

    public void setEntityType(EntityType type){
        try {
            Field field = this.packetPlayOutSpawnEntityLiving.getClass().getField("c");
            field.setAccessible(true);
            field.set(this.packetPlayOutSpawnEntityLiving , this.entityTypeIntegerMap.getOrDefault(type , 1));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    //public void


    public PacketPlayOutSpawnEntityLiving build(){
        return this.packetPlayOutSpawnEntityLiving;
    }
}
