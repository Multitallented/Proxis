package com.redcastlemedia.multitallented.spout.proxis.models.users.states;

import com.redcastlemedia.multitallented.spout.proxis.models.users.User;
import java.util.HashSet;
import org.spout.api.Spout;
import org.spout.api.entity.Player;
import org.spout.vanilla.component.substance.Potion;

/**
 *
 * @author Multitallented
 */
public class InvisState extends UserState {
    public InvisState(long duration) {
        super("Invis", duration, -1);
    }
    
    @Override
    public void apply(User user) {
        Player p = Spout.getEngine().getPlayer(user.NAME, true);
        if (p == null) {
            return;
        }
        //TODO add invis effect
    }
    @Override
    public void remove(User user) {
        Player p = Spout.getEngine().getPlayer(user.NAME, true);
        if (p == null) {
            return;
        }
        //TODO remove invis effect
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
