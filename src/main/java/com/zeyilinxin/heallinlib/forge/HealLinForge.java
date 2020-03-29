package com.zeyilinxin.heallinlib.forge;

import com.zeyilinxin.heallinlib.HealLinCatServer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import org.bukkit.entity.Player;

@Deprecated
public class HealLinForge {

    public static HealLinForge forge;
    private FMLCommonHandler fmlCommonHandler;
    private HealLinCatServer healLinCatServer;
    private MinecraftServer minecraftServer;

    public HealLinForge(HealLinCatServer healLinCatServer) {
        forge = this;
        this.healLinCatServer = healLinCatServer;
        this.fmlCommonHandler = FMLCommonHandler.instance();
        this.minecraftServer = this.fmlCommonHandler.getMinecraftServerInstance();
      //  RenderingRegistry.registerEntityRenderingHandler();
    }

    public MinecraftServer getMinecraftServer() {
        return minecraftServer;
    }

    public static EntityPlayerMP getEntityPlayerMP(Player player){
        return forge.minecraftServer.getPlayerList().getPlayerByUUID(player.getUniqueId());
    }




}
