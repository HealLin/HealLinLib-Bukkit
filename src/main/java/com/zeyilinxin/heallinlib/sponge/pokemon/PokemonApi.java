package com.zeyilinxin.heallinlib.sponge.pokemon;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.entities.pixelmon.EntityPixelmon;
import com.pixelmonmod.pixelmon.storage.PlayerPartyStorage;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.FMLCommonHandler;
import org.spongepowered.api.entity.living.player.Player;

import java.util.UUID;

public class PokemonApi {

    public static PlayerPartyStorage getPlayerPartyStorage(Player player){
        return Pixelmon.storageManager.getParty(player.getUniqueId());
    }

    public static EntityPixelmon getEntityPixelmon(UUID uuid){
        Entity entity = FMLCommonHandler.instance().getMinecraftServerInstance().func_175576_a(uuid);
        if (entity instanceof EntityPixelmon){
            return (EntityPixelmon) entity;
        }
        return null;
    }
}
