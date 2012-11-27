package com.redcastlemedia.multitallented.spout.proxis.views;

import com.redcastlemedia.multitallented.spout.proxis.Proxis;
import com.redcastlemedia.multitallented.spout.proxis.api.events.SkillConditionEvent;
import com.redcastlemedia.multitallented.spout.proxis.api.events.SkillPreCastEvent;
import com.redcastlemedia.multitallented.spout.proxis.api.events.UserGainExpEvent;
import com.redcastlemedia.multitallented.spout.proxis.api.events.UserManaChangeEvent;
import com.redcastlemedia.multitallented.spout.proxis.api.events.UserManaChangeEvent.ManaChangeReason;
import com.redcastlemedia.multitallented.spout.proxis.models.ProxisConfiguration;
import com.redcastlemedia.multitallented.spout.proxis.models.users.User;
import com.redcastlemedia.multitallented.spout.proxis.models.users.states.BuiltInUserStates;
import com.redcastlemedia.multitallented.spout.proxis.models.users.states.UserState;
import com.redcastlemedia.multitallented.spout.proxis.models.users.states.UserState.CancelledMessageTypes;
import org.spout.api.entity.Entity;
import org.spout.api.entity.Player;
import org.spout.api.event.EventHandler;
import org.spout.api.event.Listener;
import org.spout.api.event.Order;
import org.spout.api.event.entity.EntitySpawnEvent;
import org.spout.api.event.player.PlayerChatEvent;
import org.spout.api.event.player.PlayerJoinEvent;
import org.spout.api.event.player.PlayerLeaveEvent;
import org.spout.api.event.server.PreCommandEvent;
import org.spout.vanilla.component.living.Living;
import org.spout.vanilla.component.misc.HealthComponent;
import org.spout.vanilla.event.entity.EntityDamageEvent;
import org.spout.vanilla.event.player.PlayerDeathEvent;
import org.spout.vanilla.material.VanillaMaterial;
import org.spout.vanilla.protocol.entity.creature.CreatureType;
import org.spout.vanilla.protocol.handler.player.EntityHealthChangeEvent;
import org.spout.vanilla.source.HealthChangeCause;

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
        //Loads the user and sets his hp and loading state
        proxis.getUserManager().getUser(event.getPlayer().getName());
    }
    @EventHandler
    public void onPlayerQuit(PlayerLeaveEvent event) {
        proxis.getUserManager().saveUser(event.getPlayer().getName());
    }
    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        Entity e = event.getEntity();
        if (!e.has(Living.class) || !e.has(HealthComponent.class) && !(e instanceof Player)) {
            return;
        }
        Living liv = e.get(Living.class);
        String creatureTypeName = CreatureType.byClass(liv.getClass()).name().toLowerCase();
        int health = ProxisConfiguration.MOB_HEALTH.getChild(creatureTypeName).getInt(0);
        if (health != 0) {
            e.get(HealthComponent.class).setSpawnHealth(health);
            e.get(HealthComponent.class).setMaxHealth(health);
            e.get(HealthComponent.class).setHealth(health, HealthChangeCause.COMMAND);
        }
        //TODO damage? probably going to have to check the damage events instead
    }
    @EventHandler(order = Order.DEFAULT_IGNORE_CANCELLED)
    public void onPlayerCommand(PreCommandEvent event) {
        if (!event.getCommandSource().getClass().equals(Player.class)) {
            return;
        }
        User user = proxis.getUserManager().getUser(event.getCommandSource().getName());
        for (UserState us : user.getStates().values()) {
            if (us.getDefaultStates().contains(BuiltInUserStates.NO_COMMANDS)) {
                us.sendCancelledMessage(user.NAME, CancelledMessageTypes.COMMAND);
                event.setCancelled(true);
                return;
            }
        }
    }
    
    @EventHandler(order = Order.DEFAULT_IGNORE_CANCELLED)
    public void onPlayerChat(PlayerChatEvent event) {
        User user = proxis.getUserManager().getUser(event.getPlayer().getName());
        for (UserState us : user.getStates().values()) {
            if (us.getDefaultStates().contains(BuiltInUserStates.MUTE)) {
                us.sendCancelledMessage(user.NAME, CancelledMessageTypes.CHAT);
                event.setCancelled(true);
                return;
            }
        }
    }
    
    ////////////VANILLA EVENTS////////////////
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        proxis.getUserManager().handlePlayerDeath(event.getPlayer().getName());
    }
    
    @EventHandler(order = Order.DEFAULT_IGNORE_CANCELLED)
    public void onDamage(EntityDamageEvent event) {
        if (event.getDamage() < 1) {
            return;
        }
        User user = null;
        User dUser = null;
        if (event.getDamager() != null && event.getDamager().getClass().equals(Player.class)) {
            dUser = proxis.getUserManager().getUser(((Player) event.getDamager()).getName());
        }
        if (!event.getEntity().getClass().equals(Player.class)) {
            user = proxis.getUserManager().getUser(((Player) event.getEntity()).getName());
        }
        if (user != null) {
            for (UserState us : user.getStates().values()) {
                if (us.getDefaultStates().contains(BuiltInUserStates.NO_DAMAGE) ||
                        us.getDefaultStates().contains(BuiltInUserStates.NO_INCOMING_DAMAGE) ||
                        (dUser != null && (us.getDefaultStates().contains(BuiltInUserStates.NO_PVP) ||
                        us.getDefaultStates().contains(BuiltInUserStates.NO_INCOMING_PVP))) ||
                        (dUser == null && (us.getDefaultStates().contains(BuiltInUserStates.NO_INCOMING_PVE) ||
                        us.getDefaultStates().contains(BuiltInUserStates.NO_PVE)))) {
                    if (dUser != null) {
                        us.sendCancelledMessage(dUser.NAME, CancelledMessageTypes.DAMAGE);
                    }
                    event.setCancelled(true);
                    return;
                }
            }
        }
        if (dUser != null) {
            for (UserState us : dUser.getStates().values()) {
                if (us.getDefaultStates().contains(BuiltInUserStates.NO_OUTGOING_DAMAGE) ||
                        us.getDefaultStates().contains(BuiltInUserStates.NO_DAMAGE) ||
                        (user != null && (us.getDefaultStates().contains(BuiltInUserStates.NO_OUTGOING_PVP) ||
                        us.getDefaultStates().contains(BuiltInUserStates.NO_PVP))) ||
                        (user == null && (us.getDefaultStates().contains(BuiltInUserStates.NO_PVE) ||
                        us.getDefaultStates().contains(BuiltInUserStates.NO_OUTGOING_PVE)))) {
                    us.sendCancelledMessage(dUser.NAME, CancelledMessageTypes.DAMAGE);
                    event.setCancelled(true);
                    return;
                }
            }
        }
        proxis.getUserManager().putDamage(user, event.getDamager(), event.getDamageCause());
    }
    
    @EventHandler(order = Order.DEFAULT_IGNORE_CANCELLED)
    public void onHealthChange(EntityHealthChangeEvent event) {
        //TODO test this to see if this works
        if (event.getEntity().getClass().equals(Player.class)) {
            User user = proxis.getUserManager().getUser(((Player) event.getEntity()).getName());
            if (event.getChange() > 1) {
                for (UserState us : user.getStates().values()) {
                    if (us.getDefaultStates().contains(BuiltInUserStates.NO_HEAL)) {
                        us.sendCancelledMessage(user.NAME, CancelledMessageTypes.HEAL);
                        event.setCancelled(true);
                        return;
                    }
                }
            }
            /*int newHP = user.getHP() + event.getChange();
            Player player = Spout.getEngine().getPlayer(user.NAME, true);
            if (player == null) {
                return;
            }
            HealthComponent hc = player.get(HealthComponent.class);
            if (newHP > 0 && newHP <= user.getSkillClass().MAX_HP) {
                int oldHealth = user.getHP() / user.getSkillClass().MAX_HP;
                user.setHP(user.getHP() + event.getChange());
                int newHealth = user.getHP() / user.getSkillClass().MAX_HP;
                int newChange = (int) (((double) newHealth - oldHealth) / ((double) user.getSkillClass().MAX_HP) * hc.getMaxHealth());
                newChange = hc.getHealth() + newChange < 0 ? (hc.getHealth() - 1) * -1 : newChange;
                event.setChange(newChange);
                //TODO use hc.setMaxHealth() instead
            } else if (newHP < 0) {
                user.setHP(0);
                event.setChange(hc.getHealth() * -1);
            } else {
                user.setHP(user.getSkillClass().MAX_HP);
                event.setChange(hc.getMaxHealth() - hc.getHealth());
            }*/
        }
        
        //TODO damage manager
    }
    
    ////////////PROXIS EVENTS/////////////////
    @EventHandler(order = Order.DEFAULT_IGNORE_CANCELLED)
    public void onUserManaChangeEvent(UserManaChangeEvent event) {
        User user = proxis.getUserManager().getUser(event.getUsername());
        boolean natural = event.getReason() == ManaChangeReason.NATURAL_REGEN;
        boolean increase = event.getManaChange() > 0;
        boolean decrease = !increase;
        for (UserState us : user.getStates().values()) {
            if (us.getDefaultStates().contains(BuiltInUserStates.MANA_FREEZE) ||
                    (natural && us.getDefaultStates().contains(BuiltInUserStates.MANA_FREEZE_NATURAL)) ||
                    (increase && us.getDefaultStates().contains(BuiltInUserStates.MANA_FREEZE_GAIN)) ||
                    (decrease && us.getDefaultStates().contains(BuiltInUserStates.MANA_FREEZE))) {
                us.sendCancelledMessage(user.NAME, CancelledMessageTypes.MANA);
                event.setCancelled(true);
                return;
            }
        }
    }
    @EventHandler
    public void onSkillPreCastEvent(SkillPreCastEvent event) {
        User user = proxis.getUserManager().getUser(event.getUsername());
        for (UserState us : user.getStates().values()) {
            if (us.getDefaultStates().contains(BuiltInUserStates.NO_SKILLS) ||
                    us.getDefaultStates().contains(BuiltInUserStates.NO_OUTGOING_SKILLS)) {
                us.sendCancelledMessage(user.NAME, CancelledMessageTypes.SKILL);
                event.setCancelled(true);
                return;
            }
        }
    }
    @EventHandler(order = Order.LATEST_IGNORE_CANCELLED)
    public void onSkillCondition(SkillConditionEvent event) {
        event.CAST_SKILL.checkInCondition(event.INDEX, event.getResult());
    }
    @EventHandler(order = Order.LATEST)
    public void onUserGainExp(UserGainExpEvent event) {
        proxis.getTypeManager().gainExp(event.getUsername(), event.getType(), event.getExp());
    }
    //TODO addFavoriteSkill from listener
}
