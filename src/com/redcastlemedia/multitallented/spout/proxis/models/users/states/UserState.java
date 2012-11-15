package com.redcastlemedia.multitallented.spout.proxis.models.users.states;

import com.redcastlemedia.multitallented.spout.proxis.Proxis;
import com.redcastlemedia.multitallented.spout.proxis.models.effects.EffectSource;
import com.redcastlemedia.multitallented.spout.proxis.models.skills.Skill;
import com.redcastlemedia.multitallented.spout.proxis.models.users.User;
import java.util.HashSet;
import org.spout.api.entity.Entity;
import org.spout.api.geo.cuboid.Block;
import org.spout.api.util.config.ConfigurationNode;

/**
 *
 * @author Multitallented
 */
public abstract class UserState extends EffectSource {
    private final long duration;
    private final long period;
    public UserState(String name, long duration, long period) {
        super(name);
        this.duration = duration;
        this.period = period;
    }
    
    public abstract HashSet<BuiltInUserStates> getDefaultStates();
    
    public abstract void apply(User user);
    public abstract void apply(Block block);
    public abstract void apply(Entity e);
    
    public abstract void tick(User user);
    public abstract void tick(Block block);
    public abstract void tick(Entity e);
    
    public abstract void remove(User user);
    public abstract void remove(Block block);
    public abstract void remove(Entity e);
    
    @Override
    public void execute(Proxis plugin, Skill.CastSkill cs, User target, ConfigurationNode node) {
        apply(target);
        //TODO add to scheduler
    }
    
    
    @Override
    public void execute(Proxis plugin, Skill.CastSkill cs, Entity target, ConfigurationNode node) {
        apply(target);
        //TODO add to scheduler
    }

    @Override
    public void execute(Proxis plugin, Skill.CastSkill cs, Block target, ConfigurationNode node) {
        apply(target);
        //TODO add to scheduler
    }
    
    public abstract void sendCancelledMessage(String username, CancelledMessageTypes type);
    
    public enum CancelledMessageTypes {
        CHAT,
        COMMAND,
        HEAL,
        DAMAGE,
        SKILL,
        MANA
    }
}