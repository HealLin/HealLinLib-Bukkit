package com.zeyilinxin.heallinlib.mod.pokemon;

import com.heallin.api.bukkit.CoreBukkit;
import com.heallin.api.bukkit.entitiy.CorePlayer;
import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.battles.rules.BattleRules;
import com.pixelmonmod.pixelmon.comm.packetHandlers.OpenReplaceMoveScreen;
import com.pixelmonmod.pixelmon.entities.pixelmon.EntityPixelmon;
import com.pixelmonmod.pixelmon.items.ItemPixelmonSprite;
import com.pixelmonmod.pixelmon.storage.PlayerPartyStorage;
import com.zeyilinxin.heallinlib.forge.HealLinForge;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PokemonApi {

    public static EntityPixelmon getEntityPixelmon(UUID uuid){
        Entity entity = FMLCommonHandler.instance().getMinecraftServerInstance().getEntityFromUuid(uuid);
        if (entity instanceof EntityPixelmon){
            return (EntityPixelmon) entity;
        }
        return null;
    }

    @Deprecated
    public static PlayerPartyStorage getPlayerPartyStorage(Player player){
        return Pixelmon.storageManager.getParty(player.getUniqueId());
    }

    public static PlayerPartyStorage getPlayerPartyStorage(CorePlayer corePlayer){
        return getPlayerPartyStorage(corePlayer.getPlayer());
    }

    public static void replacePokemonSkill(UUID pokemonUUID , int attackId , CorePlayer player){
        Pixelmon.network.sendTo(new OpenReplaceMoveScreen(pokemonUUID , attackId) , player.getEntityPlayerMP());
    }

    @Deprecated
    public static void replacePokemonSkill(UUID pokemonUUID , int attackId , Player player){
        replacePokemonSkill(pokemonUUID , attackId , CoreBukkit.getCorePlayer(player));
    }

    public static ItemStack getPhoto(Pokemon pokemon){
        return ItemPixelmonSprite.getPhoto(pokemon);
    }

    public static ItemStack getPhoto(EntityPixelmon entityPixelmon){
        return ItemPixelmonSprite.getPhoto(entityPixelmon);
    }

}
