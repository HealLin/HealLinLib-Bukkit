package com.zeyilinxin.heallinlib.sponge.config;

import com.google.common.reflect.TypeToken;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpongeConfig {

    private ConfigurationLoader<CommentedConfigurationNode> configurationLoader;
    private CommentedConfigurationNode configurationNode;

    public SpongeConfig(ConfigurationLoader<CommentedConfigurationNode> configurationLoader, CommentedConfigurationNode configurationNode){
        this.configurationLoader = configurationLoader;
        this.configurationNode = configurationNode;
    }

    public void reloadFile() throws IOException {
        this.configurationNode = this.configurationLoader.load();
    }


    public List<String> getList( Object... objects){
        List<String> arrayList = new ArrayList<>();
        try {
            arrayList = configurationNode.getNode(objects).getList(TypeToken.of(String.class) , new ArrayList<>());
        } catch (ObjectMappingException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public void saveFile(){
        try {
            this.configurationLoader.save(this.configurationNode);
        } catch (IOException var4) {
            var4.printStackTrace();
        }
    }
}
