package com.heallin.api.bukkit.manager.event;

import lombok.Setter;
import org.bukkit.event.Event;

public abstract class CoreEvent {

    private final boolean async;
    @Setter

    private String name;

    public CoreEvent() {
        this(false);
    }


    public CoreEvent(boolean isAsync) {
        this.async = isAsync;
    }

    public String getEventName() {
        if (this.name == null) {
            this.name = this.getClass().getSimpleName();
        }

        return this.name;
    }





}
