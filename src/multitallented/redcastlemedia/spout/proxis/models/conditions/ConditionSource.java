package multitallented.redcastlemedia.spout.proxis.models.conditions;

import multitallented.redcastlemedia.spout.proxis.Proxis;
import multitallented.redcastlemedia.spout.proxis.models.SourceType;
import multitallented.redcastlemedia.spout.proxis.models.skills.Skill.CastSkill;
import multitallented.redcastlemedia.spout.proxis.models.skills.SkillResult;
import multitallented.redcastlemedia.spout.proxis.models.skills.SkillSource;
import multitallented.redcastlemedia.spout.proxis.models.users.User;
import org.spout.api.geo.cuboid.Block;
import org.spout.api.util.config.ConfigurationNode;
import org.spout.vanilla.component.living.VanillaEntity;

/**
 *
 * @author Multitallented
 */
public abstract class ConditionSource extends SkillSource {
    public final String name;
    
    public ConditionSource(String name) {
        super(SourceType.CONDITION);
        this.name = name;
    }
    
    public abstract SkillResult testCondition(Proxis plugin, CastSkill cs, User user, ConfigurationNode node);
    
    public abstract SkillResult testCondition(Proxis plugin, CastSkill cs, Block block, ConfigurationNode node);
    
    public abstract SkillResult testCondition(Proxis plugin, CastSkill cs, VanillaEntity ve, ConfigurationNode node);
}
