package com.redcastlemedia.multitallented.spout.proxis.api.events;

import com.redcastlemedia.multitallented.spout.proxis.models.skills.Skill.CastSkill;
import org.spout.api.entity.Entity;
import org.spout.api.event.Cancellable;
import org.spout.api.event.Event;
import org.spout.api.event.HandlerList;

/**
 *
 * @author Multitallented
 */
public class SkillHealEvent extends Event implements Cancellable {
    private final CastSkill cs;
    private Entity e;
    private int amount;
    private static final HandlerList hList = new HandlerList();
    public SkillHealEvent(CastSkill cs, Entity e, int amount) {
        this.cs = cs;
        this.e = e;
        this.amount = amount;
    }
    
    public CastSkill getCastSkill() {
        return cs;
    }
    public Entity getEntity() {
        return e;
    }
    public void setEntity(Entity e) {
        this.e = e;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
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
