package com.redcastlemedia.multitallented.spout.proxis.models.users.states;

import com.redcastlemedia.multitallented.spout.proxis.models.users.User;
import java.util.HashSet;
import org.spout.api.Spout;
import org.spout.api.entity.Player;
import org.spout.api.geo.discrete.Point;

/**
 *
 * @author Multitallented
 */
public class RootState extends UserState {
    private Point p;
    public RootState(long duration, long period) {
        super("Root", duration, period);
    }

    @Override
    public void apply(User user) {
        super.apply(user);
        Player player = Spout.getEngine().getPlayer(user.NAME, true);
        if (player == null) {
            p = null;
        }
        p = player.getTransform().getPosition();
    }
    @Override
    public void tick(User user) {
        super.tick(user);
        Player player = Spout.getEngine().getPlayer(user.NAME, true);
        if (player == null) {
            return;
        }
        if (!player.getTransform().getPosition().equals(p)) {
            player.teleport(p);
        }
    }
    
    @Override
    public HashSet<BuiltInUserStates> getDefaultStates() {
        return new HashSet<>();
    }

    @Override
    public HashSet<UserState> getInternalStates() {
        return new HashSet<>();
    }

    @Override
    public void sendCancelledMessage(String username, CancelledMessageTypes type) {
        
    }
}
