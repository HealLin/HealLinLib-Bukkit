package com.zeyilinxin.heallinlib.chat;

public class ClickEvent {

    private ClickAction action;
    private String value;

    public ClickEvent(){

    }

    public ClickEvent(ClickAction action, final String value) {
        this.action = action;
        this.value = value;
    }

    public void setAction(ClickAction action) {
        this.action = action;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isClick(){
        return true;
    }

    protected String toJson() {
        return "\"clickEvent\":{\"action\":\"" + this.action.data + "\",\"value\":\"" + this.value + "\"}";
    }

    public enum ClickAction
    {
        OPEN_URL("open_url"),
        OPEN_FILE("open_file"),
        RUN_COMMAND("run_command"),
        SUGGEST_COMMAND("suggest_command"),
        CHANGE_PAGE("change_page");

        private String data;

        private ClickAction(final String data) {
            this.data = data;
        }
    }
}
