package multitallented.redcastlemedia.spout.proxis.models.conditions;

import java.util.HashSet;
import multitallented.redcastlemedia.spout.proxis.Proxis;
import multitallented.redcastlemedia.spout.proxis.controllers.UserManager;
import multitallented.redcastlemedia.spout.proxis.models.skills.Skill.CastSkill;
import org.spout.api.entity.Player;
import org.spout.api.geo.cuboid.Block;
import org.spout.api.util.config.ConfigurationNode;
import org.spout.vanilla.component.living.VanillaEntity;

/**
 *
 * @author Multitallented
 */
public class Condition {
    private final ConditionSource cs;
    private final ConfigurationNode node;
    private final Proxis plugin;
    public Condition(Proxis plugin, ConditionSource cs, ConfigurationNode node) {
        this.plugin = plugin;
        this.cs = cs;
        this.node = node;
    }
    
    public void testCondition(CastSkill cts, HashSet<Object> targets) {
        for (Object obj : targets) {
            if (obj.getClass().equals(Player.class)) {
                cs.testCondition(plugin, cts, UserManager.getUser(((Player) obj).getName()), node);
            } else if (obj.getClass().equals(Block.class)) {
                cs.testCondition(plugin, cts, (Block) obj, node);
            } else if (obj instanceof VanillaEntity) {
                cs.testCondition(plugin, cts, (VanillaEntity) obj, node);
            }
        }
    }
}
