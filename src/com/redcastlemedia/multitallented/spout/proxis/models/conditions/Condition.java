package com.redcastlemedia.multitallented.spout.proxis.models.conditions;

import com.redcastlemedia.multitallented.spout.proxis.Proxis;
import com.redcastlemedia.multitallented.spout.proxis.models.skills.Skill.CastSkill;
import java.util.HashMap;
import java.util.HashSet;
import org.spout.api.entity.Entity;
import org.spout.api.entity.Player;
import org.spout.api.geo.cuboid.Block;
import org.spout.api.util.config.ConfigurationNode;

/**
 *
 * @author Multitallented
 */
public class Condition {
    private final ConditionSource cs;
    private final HashMap<String, Object> node;
    private final Proxis plugin;
    public Condition(Proxis plugin, ConditionSource cs, HashMap<String, Object> node) {
        this.plugin = plugin;
        this.cs = cs;
        this.node = node;
    }
    
    public void testCondition(CastSkill cts, HashSet<Object> targets) {
        for (Object obj : targets) {
            if (obj.getClass().equals(Player.class)) {
                cs.testCondition(plugin, cts, plugin.getUserManager().getUser(((Player) obj).getName()), node);
            } else if (obj.getClass().equals(Entity.class)) {
                cs.testCondition(plugin, cts, (Entity) obj, node);
            } else if (obj.getClass().equals(Block.class)) {
                cs.testCondition(plugin, cts, (Block) obj, node);
            }
        }
    }
}
