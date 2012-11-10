package com.redcastlemedia.multitallented.spout.proxis.api.events;

import org.spout.api.event.Event;
import org.spout.api.event.HandlerList;

/**
 *
 * @author Multitallented
 */
public class UserGainExpEvent extends Event {
    private static final HandlerList hList = new HandlerList();
    private String username;
    private String type;
    private double exp;
    public UserGainExpEvent(String username, String type, double exp) {
        this.username = username;
        this.type = type;
        this.exp = exp;
    }
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public double getExp() {
        return exp;
    }
    public void setExp(double exp) {
        this.exp = exp;
    }
    
    public static HandlerList getHandlerList() {
        return hList;
    }
    
    @Override
    public HandlerList getHandlers() {
        return hList;
    }
}
