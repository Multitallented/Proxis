package com.redcastlemedia.multitallented.spout.proxis.models.skills;

import com.redcastlemedia.multitallented.spout.proxis.Proxis;
import com.redcastlemedia.multitallented.spout.proxis.models.SourceType;
import org.spout.api.entity.Entity;
import org.spout.api.entity.Player;
import org.spout.vanilla.event.entity.EntityDamageEvent;
import org.spout.vanilla.source.DamageCause;

/**
 *
 * @author Multitallented
 */
public abstract class SkillSource {
    public final SourceType TYPE;
    private String name;
    private Proxis plugin;

    public SkillSource(SourceType type) {
        TYPE = type;
    }

    public void init(Proxis plugin, String sourceName) {
        this.plugin = plugin;
        this.name = sourceName;
    }

    public Proxis getPlugin() {
        return plugin;
    }
    public String getName() {
        return name;
    }

    
    public boolean damageCheck(Player damager, Entity damagee) {
        return plugin.getEngine().getEventManager().callEvent(new EntityDamageEvent(damagee, 0, DamageCause.UNKNOWN, false, (Entity) damager)).isCancelled();
    }
}
