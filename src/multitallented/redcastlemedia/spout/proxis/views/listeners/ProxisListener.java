package multitallented.redcastlemedia.spout.proxis.views.listeners;

import multitallented.redcastlemedia.spout.proxis.Proxis;
import multitallented.redcastlemedia.spout.proxis.api.events.SkillConditionEvent;
import org.spout.api.event.EventHandler;
import org.spout.api.event.Listener;
import org.spout.api.event.Order;
import org.spout.api.event.player.PlayerJoinEvent;

/**
 *
 * @author Multitallented
 */
public class ProxisListener implements Listener {
    private final Proxis proxis;
    /**
     * Initializes the listener by storing the parent class.
     * @param proxis
     */
    public ProxisListener(Proxis proxis) {
        this.proxis = proxis;
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        //TODO
    }
    
    ////////////PROXIS EVENTS/////////////////
    @EventHandler(order = Order.LATEST_IGNORE_CANCELLED)
    public void onSkillCondition(SkillConditionEvent event) {
        event.CAST_SKILL.checkInCondition(event.INDEX, event.getResult());
    }
}
