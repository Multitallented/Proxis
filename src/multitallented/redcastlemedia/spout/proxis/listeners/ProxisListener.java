package multitallented.redcastlemedia.spout.proxis.listeners;

import multitallented.redcastlemedia.spout.proxis.Proxis;
import org.spout.api.event.EventHandler;
import org.spout.api.event.Listener;
import org.spout.api.event.player.PlayerJoinEvent;

/**
 *
 * @author Multitallented
 */
public class ProxisListener implements Listener {
    /**
     * Initializes the listener by storing the parent class.
     * @param proxis
     */
    public ProxisListener(Proxis proxis) {
        
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        //TODO
    }
}
