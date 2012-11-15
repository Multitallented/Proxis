package com.redcastlemedia.multitallented.spout.proxis.models.users.states;

import com.redcastlemedia.multitallented.spout.proxis.models.users.User;
import java.util.HashSet;
import org.spout.api.entity.Entity;
import org.spout.api.geo.cuboid.Block;

/**
 *
 * @author Multitallented
 */
public class LoadingState extends UserState {
    public LoadingState(long duration, long period) {
        super("Loading", duration, period);
    }
    
    @Override
    public HashSet<BuiltInUserStates> getDefaultStates() {
        HashSet<BuiltInUserStates> tempSet = new HashSet<>();
        tempSet.add(BuiltInUserStates.ROOT);
        tempSet.add(BuiltInUserStates.NO_COMMANDS);
        tempSet.add(BuiltInUserStates.NO_DAMAGE);
        tempSet.add(BuiltInUserStates.NO_SKILLS);
        return tempSet;
    }

    @Override
    public void apply(User user) {
        //TODO teleport? blind?
    }

    @Override
    public void apply(Block block) {
        
    }

    @Override
    public void apply(Entity e) {
        
    }

    @Override
    public void remove(User user) {
        
    }

    @Override
    public void remove(Block block) {
        
    }

    @Override
    public void remove(Entity e) {
        
    }

    @Override
    public void tick(User user) {
        
    }

    @Override
    public void tick(Block block) {
        
    }

    @Override
    public void tick(Entity e) {
        
    }
}
