package com.redcastlemedia.multitallented.spout.proxis.api.events;

import org.spout.api.event.Cancellable;
import org.spout.api.event.Event;
import org.spout.api.event.HandlerList;

/**
 *
 * @author Multitallented
 */
public class UserManaChangeEvent extends Event implements Cancellable {
    private static final HandlerList hList = new HandlerList();
    private String username;
    private int mana;
    private boolean cancelled = false;
    private ManaChangeReason reason;
    public UserManaChangeEvent(String username, int mana) {
        this.username = username;
        this.mana = mana;
        this.reason = ManaChangeReason.SKILL;
    }
    public UserManaChangeEvent(String username, int mana, ManaChangeReason reason) {
        this.username = username;
        this.mana = mana;
        this.reason = reason;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public double getManaChange() {
        return mana;
    }
    public void setManaChange(int mana) {
        this.mana = mana;
    }
    public ManaChangeReason getReason() {
        return reason;
    }
    public void setManaChangeReason(ManaChangeReason reason) {
        this.reason = reason;
    }
    
    @Override
    public void setCancelled(boolean bln) {
        this.cancelled = bln;
    }
    @Override
    public boolean isCancelled() {
        return cancelled;
    }
    public static HandlerList getHandlerList() {
        return hList;
    }
    @Override
    public HandlerList getHandlers() {
        return hList;
    }
    
    public enum ManaChangeReason {
        NATURAL_REGEN,
        SKILL,
        COMMAND,
    }
}
