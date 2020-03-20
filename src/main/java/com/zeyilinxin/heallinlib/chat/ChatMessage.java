//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zeyilinxin.heallinlib.chat;

import net.minecraft.server.v1_12_R1.IChatBaseComponent;

import java.util.ArrayList;

public class ChatMessage {
    private int point;
    private ArrayList<Unit> units = new ArrayList();
    private String json;

    public ChatMessage(String message) {
        this.units.add(new Unit(message));
        this.point = 0;
        this.json = null;
    }

    public ChatMessage next(String message) {
        this.units.add(new Unit(message));
        ++this.point;
        return this;
    }

    public ChatMessage tooltip(ArrayList<String> list) {
        (this.units.get(this.point)).setHoverEvent(new HoverEvent(HoverEvent.HoverAction.SHOW_TEXT, list));
        return this;
    }

    public ChatMessage click(ClickEvent.ClickAction action, String value) {
        (this.units.get(this.point)).setClickEvent(new ClickEvent(action, value));
        return this;
    }

    public ChatMessage build() {
        StringBuilder a = new StringBuilder();
        for(int i = 0; i < this.units.size(); ++i) {
            a.append((this.units.get(i)).toJson());
            if (i < this.units.size() - 1) {
                a.append(",");
            }
        }
        this.json = "{\"extra\":[" + a.toString() + "],\"text\":\"\"}";
        return this;
    }


    public IChatBaseComponent getText(){
        return IChatBaseComponent.ChatSerializer.a(this.json);
    }
}
