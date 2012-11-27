package com.redcastlemedia.multitallented.spout.proxis.models.users.states;

import com.redcastlemedia.multitallented.spout.proxis.models.users.User;
import java.util.HashSet;
import org.spout.api.Spout;
import org.spout.api.entity.Player;
import org.spout.vanilla.component.substance.Potion;
import org.spout.vanilla.protocol.msg.entity.effect.EntityEffectMessage;
import org.spout.vanilla.protocol.msg.entity.effect.EntityRemoveEffectMessage;

/**
 *
 * @author Multitallented
 */
public class InvisState extends UserState {
    private final long sDuration;
    public InvisState(long duration) {
        super("Invis", duration, -1);
        this.sDuration = duration;
    }
    
    @Override
    public void apply(User user) {
        super.apply(user);
        Player p = Spout.getEngine().getPlayer(user.NAME, true);
        if (p == null) {
            return;
        }
        byte aDuration = (byte) sDuration;
        if (aDuration < 0) {
            aDuration = (byte) 999999;
        }
        EntityEffectMessage eem = new EntityEffectMessage(p.getId(), (byte) 14, aDuration, (short) 3);
        p.getSession().send(true, eem);
        //TODO test invis effect
    }
    @Override
    public void remove(User user) {
        super.apply(user);
        Player p = Spout.getEngine().getPlayer(user.NAME, true);
        if (p == null) {
            return;
        }
        EntityRemoveEffectMessage erem = new EntityRemoveEffectMessage(p.getId(), (byte) 14);
        p.getSession().send(true, erem);
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