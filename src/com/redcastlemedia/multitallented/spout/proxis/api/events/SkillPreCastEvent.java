package com.redcastlemedia.multitallented.spout.proxis.api.events;

import com.redcastlemedia.multitallented.spout.proxis.models.skills.Skill.CastSkill;
import org.spout.api.event.Cancellable;
import org.spout.api.event.Event;
import org.spout.api.event.HandlerList;

/**
 *
 * @author Multitallented
 */
public class SkillPreCastEvent extends Event implements Cancellable {
    private static final HandlerList hList = new HandlerList();
    private String username;
    private boolean cancelled = false;
    private CastSkill skill;
    public SkillPreCastEvent(String username, CastSkill skill) {
        this.username = username;
        this.skill = skill;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public CastSkill getSkill() {
        return skill;
    }
    public void setManaChange(CastSkill skill) {
        this.skill = skill;
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
}
