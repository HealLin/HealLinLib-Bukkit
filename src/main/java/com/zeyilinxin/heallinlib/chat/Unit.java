package com.zeyilinxin.heallinlib.chat;


public class Unit
{
    private String displayMessage;
    private HoverEvent hoverEvent;
    private ClickEvent clickEvent;

    Unit(final String message) {
        this.displayMessage = message;
        this.hoverEvent = null;
        this.clickEvent = null;
    }

    public ClickEvent getClickEvent() {
        return clickEvent;
    }

    public void setHoverEvent(HoverEvent hoverEvent) {
        this.hoverEvent = hoverEvent;
    }

    public void setClickEvent(ClickEvent clickEvent) {
        this.clickEvent = clickEvent;
    }

    public String toJson() {
        return "{" + ((this.hoverEvent == null) ? "" : (this.hoverEvent.toJson() + ",")) + ((this.clickEvent == null) ? "" : (this.clickEvent.toJson() + ",")) + "\"text\":\"" + this.displayMessage + "\"}";
    }
}
