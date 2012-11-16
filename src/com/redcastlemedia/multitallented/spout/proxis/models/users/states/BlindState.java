package com.redcastlemedia.multitallented.spout.proxis.models.users.states;

import com.redcastlemedia.multitallented.spout.proxis.models.users.User;
import java.util.HashSet;
import org.spout.api.Spout;
import org.spout.api.entity.Player;
import org.spout.vanilla.protocol.msg.entity.effect.EntityEffectMessage;
import org.spout.vanilla.protocol.msg.entity.effect.EntityRemoveEffectMessage;

/**
 *
 * @author Multitallented
 */
public class BlindState extends UserState {
    private final long sDuration;
    public BlindState(long duration) {
        super("Blind", duration, -1);
        this.sDuration = duration;
    }

    @Override
    public void apply(User user) {
        super.apply(user);
        Player player = Spout.getEngine().getPlayer(user.NAME, true);
        if (player == null) {
            return;
        }
        byte aDuration = (byte) sDuration;
        if (aDuration < 0) {
            aDuration = (byte) 999999;
        }
        EntityEffectMessage eem = new EntityEffectMessage(player.getId(), (byte) 15, aDuration, (short) 3);
        player.getSession().send(true, eem);
        //TODO test this
    }
    
    @Override
    public void remove(User user) {
        super.remove(user);
        Player player = Spout.getEngine().getPlayer(user.NAME, true);
        if (player == null) {
            return;
        }
        EntityRemoveEffectMessage erem = new EntityRemoveEffectMessage(player.getId(), (byte) 15);
        player.getSession().send(true, erem);
        //TODO test this
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
