package com.redcastlemedia.multitallented.spout.proxis.models.effects;

import com.redcastlemedia.multitallented.spout.proxis.Proxis;
import com.redcastlemedia.multitallented.spout.proxis.models.skills.Skill.CastSkill;
import java.util.HashMap;
import java.util.HashSet;
import org.spout.api.entity.Entity;
import org.spout.api.entity.Player;
import org.spout.api.geo.cuboid.Block;

/**
 *
 * @author Multitallented
 */
public class Effect {
    private final EffectSource es;
    private final HashMap<String, Object> node;
    private final Proxis plugin;
    public Effect(Proxis plugin, EffectSource es, HashMap<String, Object> node) {
        this.es = es;
        this.plugin = plugin;
        this.node = node;
    }
    
    public HashMap<String, Object> getNode() {
        return node;
    }
    
    public void execute(CastSkill cs, HashSet<Object> targetSet) {
        for (Object obj : targetSet) {
            if (obj.getClass().equals(Player.class)) {
                es.execute(plugin, cs, plugin.getUserManager().getUser(((Player) obj).getName()), node);
            } else if (obj.getClass().equals(Entity.class)) {
                es.execute(plugin, cs, (Entity) obj, node);
            } else if (obj.getClass().equals(Block.class)) {
                es.execute(plugin, cs, (Block) obj, node);
            }
        }
    }
}
