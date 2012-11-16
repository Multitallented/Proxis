package com.redcastlemedia.multitallented.spout.proxis.models.users.states;

import com.redcastlemedia.multitallented.spout.proxis.Proxis;
import com.redcastlemedia.multitallented.spout.proxis.models.LanguageConfiguration;
import com.redcastlemedia.multitallented.spout.proxis.models.users.User;
import java.util.HashSet;
import org.spout.api.chat.style.ChatStyle;
import org.spout.api.chat.style.ColorChatStyle;
import org.spout.api.entity.Entity;
import org.spout.api.entity.Player;
import org.spout.api.geo.cuboid.Block;

/**
 *
 * @author Multitallented
 */
public class LoadingState extends UserState {
    public LoadingState() {
        super("Loading", -1, 100);
    }
    
    @Override
    public HashSet<BuiltInUserStates> getDefaultStates() {
        HashSet<BuiltInUserStates> tempSet = new HashSet<>();
        tempSet.add(BuiltInUserStates.NO_COMMANDS);
        tempSet.add(BuiltInUserStates.NO_DAMAGE);
        tempSet.add(BuiltInUserStates.NO_SKILLS);
        return tempSet;
    }
    
    @Override
    public void sendCancelledMessage(String username, CancelledMessageTypes type) {
        User user = getPlugin().getUserManager().getUser(username);
        Player p = getPlugin().getEngine().getPlayer(username, true);
        if (p == null) {
            return;
        }
        p.sendMessage(ColorChatStyle.RED + Proxis.NAME + Proxis.lconf.getTranslation(user.getLocale(), "deny-loading"));
    }

    @Override
    public void apply(User user) {
        //TODO teleport? blind?
    }

    @Override
    public void tick(User user) {
        
    }

    @Override
    public HashSet<UserState> getInternalStates() {
        HashSet<UserState> tempSet = new HashSet<>();
        //TODO add root
        //TODO add blind
        //TODO add invis
        return tempSet;
    }
}