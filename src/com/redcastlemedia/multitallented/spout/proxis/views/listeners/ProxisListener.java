package com.redcastlemedia.multitallented.spout.proxis.views.listeners;

import com.redcastlemedia.multitallented.spout.proxis.Proxis;
import com.redcastlemedia.multitallented.spout.proxis.api.events.SkillConditionEvent;
import com.redcastlemedia.multitallented.spout.proxis.api.events.UserGainExpEvent;
import com.redcastlemedia.multitallented.spout.proxis.models.users.User;
import org.spout.api.entity.Player;
import org.spout.api.event.EventHandler;
import org.spout.api.event.Listener;
import org.spout.api.event.Order;
import org.spout.api.event.player.PlayerJoinEvent;
import org.spout.vanilla.event.entity.EntityDamageEvent;
import org.spout.vanilla.event.player.PlayerDeathEvent;

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
    
    ////////////VANILLA EVENTS////////////////
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        proxis.getUserManager().handlePlayerDeath(event.getPlayer().getName());
    }
    
    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (!event.getEntity().getClass().equals(Player.class) || event.getDamage() < 0) {
            return;
        }
        User user = proxis.getUserManager().getUser(((Player) event.getEntity()).getName());
        proxis.getUserManager().putDamage(user, event.getDamager(), event.getDamageCause());
    }
    
    ////////////PROXIS EVENTS/////////////////
    @EventHandler(order = Order.LATEST_IGNORE_CANCELLED)
    public void onSkillCondition(SkillConditionEvent event) {
        event.CAST_SKILL.checkInCondition(event.INDEX, event.getResult());
    }
    @EventHandler(order = Order.LATEST)
    public void onUserGainExp(UserGainExpEvent event) {
        proxis.getTypeManager().gainExp(event.getUsername(), event.getType(), event.getExp());
    }
}
