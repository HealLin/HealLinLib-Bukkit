package com.zeyilinxin.heallinlib.sponge;

import com.zeyilinxin.heallinlib.sponge.plugin.SpongePlugin;

@Deprecated
public class HealLinSponge {

    private static HealLinSponge healLinSponge;
    private SpongePlugin spongePlugin;

    HealLinSponge(SpongePlugin spongePlugin){
        this.spongePlugin = spongePlugin;
    }


    public static HealLinSponge ini(SpongePlugin spongePlugin){
        if (healLinSponge == null){
            healLinSponge = new HealLinSponge(spongePlugin);
        }
        return healLinSponge;
    }


}
