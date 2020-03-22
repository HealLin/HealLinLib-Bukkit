package com.zeyilinxin.heallinlib.sponge.plugin;

import com.zeyilinxin.heallinlib.sponge.config.SpongeConfig;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;

@Deprecated
public abstract class SpongePlugin {

    private SpongeConfig spongeConfig;

    public SpongePlugin(){

    }

    public abstract  CommentedConfigurationNode getConfigurationNode();

    public  abstract ConfigurationLoader<CommentedConfigurationNode> getConfigurationLoader();


    public SpongeConfig getConfig(){
        if (this.spongeConfig == null){
            this.spongeConfig = new SpongeConfig(this.getConfigurationLoader() , this.getConfigurationNode());
        }
        return this.spongeConfig;
    }


}
