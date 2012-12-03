package com.redcastlemedia.multitallented.spout.proxis.models.skills;

import com.redcastlemedia.multitallented.spout.proxis.Proxis;
import com.redcastlemedia.multitallented.spout.proxis.api.events.SkillDamageEvent;
import com.redcastlemedia.multitallented.spout.proxis.models.SourceType;
import com.redcastlemedia.multitallented.spout.proxis.models.skills.Skill.CastSkill;
import com.redcastlemedia.multitallented.spout.proxis.models.users.User;
import java.util.HashMap;
import org.spout.api.Spout;
import org.spout.api.entity.Entity;
import org.spout.api.entity.Player;
import org.spout.vanilla.component.misc.HealthComponent;
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

    public HashMap<String, Object> getDefaultNode() {
        HashMap<String, Object> tempMap = new HashMap<>();
        return tempMap;
    }
    
    public boolean damageCheck(Player damager, Entity damagee) {
        return plugin.getEngine().getEventManager().callEvent(new EntityDamageEvent(damagee, 0, DamageCause.UNKNOWN, false, (Entity) damager)).isCancelled();
    }
    
    public void damage(CastSkill cs, Player caster, Entity e, int amount, DamageCause cause, boolean knockback) {
        SkillDamageEvent event = Spout.getEventManager().callEvent(new SkillDamageEvent(cs, e, amount));
        if (event.isCancelled() || event.getAmount() < 1 || !e.has(HealthComponent.class)) {
            return;
        }
        e.get(HealthComponent.class).damage(amount, cause, caster, knockback);
    }
    public void damage(CastSkill cs, Player caster, User u, int amount, DamageCause cause, boolean knockback) {
        Entity e = Spout.getEngine().getPlayer(u.NAME, true);
        if (e == null) {
            return;
        }
        SkillDamageEvent event = Spout.getEventManager().callEvent(new SkillDamageEvent(cs, e, amount));
        if (event.isCancelled() || event.getAmount() < 1 || !e.has(HealthComponent.class)) {
            return;
        }
        e.get(HealthComponent.class).damage(amount, cause, caster, knockback);
    }
}
