package com.redcastlemedia.multitallented.spout.proxis.models.users.states;

import com.redcastlemedia.multitallented.spout.proxis.models.users.User;
import java.util.HashSet;
import org.spout.api.entity.Entity;
import org.spout.api.geo.cuboid.Block;

/**
 *
 * @author Multitallented
 */
public interface UserState {
    public abstract HashSet<BuiltInUserStates> getDefaultStates();
    
    public abstract void apply(User user);
    
    public abstract void apply(Block block);
    
    public abstract void apply(Entity e);
    
    public abstract void remove(User user);
    
    public abstract void remove(Block block);
    
    public abstract void remove(Entity e);
}