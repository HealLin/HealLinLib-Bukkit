package com.zeyilinxin.heallinlib.chat;

import java.util.*;

public class HoverEvent
{
    private HoverAction action;
    private ArrayList<String> value;

    protected HoverEvent(final HoverAction action, final ArrayList<String> value) {
        this.action = action;
        this.value = value;
    }

    public void setAction(final HoverAction action) {
        this.action = action;
    }

    public void setValue(final ArrayList<String> value) {
        this.value = value;
    }

    public String toJson() {
        final StringBuilder a = new StringBuilder();
        for (int i = 0; i < this.value.size(); ++i) {
            a.append("{\"text\":\"").append(this.value.get(i)).append("\"}");
            if (i < this.value.size() - 1) {
                a.append(",");
            }
        }
        return "\"hoverEvent\":{\"action\":\"" + this.action.data + "\",\"value\":{\"extra\":[" + a.toString() + "],\"text\":\"\"}}";
    }

    public enum HoverAction
    {
        SHOW_TEXT("show_text"),
        SHOW_ACHIEVEMENT("show_achievement"),
        SHOW_ITEM("show_item"),
        SHOW_ENTITY("show_entity");

        private String data;

        private HoverAction(final String data) {
            this.data = data;
        }
    }
}
