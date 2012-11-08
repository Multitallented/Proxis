package multitallented.redcastlemedia.spout.proxis.models.effects;

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
public class Effect {
    private final EffectSource es;
    private final ConfigurationNode node;
    private final Proxis plugin;
    public Effect(Proxis plugin, EffectSource es, ConfigurationNode node) {
        this.es = es;
        this.plugin = plugin;
        this.node = node;
    }
    
    public void execute(CastSkill cs, HashSet<Object> targetSet) {
        for (Object obj : targetSet) {
            if (obj.getClass().equals(Player.class)) {
                es.execute(plugin, cs, UserManager.getUser(((Player) obj).getName()), node);
            } else if (obj.getClass().equals(Block.class)) {
                es.execute(plugin, cs, (Block) obj, node);
            } else if (obj instanceof VanillaEntity) {
                es.execute(plugin, cs, (VanillaEntity) obj, node);
            }
        }
    }
}
