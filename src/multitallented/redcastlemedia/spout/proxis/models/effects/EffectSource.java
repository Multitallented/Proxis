package multitallented.redcastlemedia.spout.proxis.models.effects;

import java.util.HashSet;
import multitallented.redcastlemedia.spout.proxis.Proxis;
import multitallented.redcastlemedia.spout.proxis.models.SourceType;
import multitallented.redcastlemedia.spout.proxis.models.conditions.ConditionSource;
import multitallented.redcastlemedia.spout.proxis.models.skills.Skill.CastSkill;
import multitallented.redcastlemedia.spout.proxis.models.skills.SkillSource;
import multitallented.redcastlemedia.spout.proxis.models.targets.TargetSource;
import multitallented.redcastlemedia.spout.proxis.models.users.User;
import org.spout.api.geo.cuboid.Block;
import org.spout.api.util.config.ConfigurationNode;
import org.spout.vanilla.component.living.VanillaEntity;

/**
 *
 * @author Multitallented
 */
public abstract class EffectSource extends SkillSource {
    public final HashSet<ConditionSource> conditions = new HashSet<>();
    public final String NAME;
    public TargetSource target = null;

    public EffectSource(String name) {
        super(SourceType.EFFECT);
        this.NAME = name;
    }
    
    public abstract void execute(Proxis plugin, CastSkill cs, User target, ConfigurationNode node);
    
    public abstract void execute(Proxis plugin, CastSkill cs, VanillaEntity target, ConfigurationNode node);
    
    public abstract void execute(Proxis plugin, CastSkill cs, Block target, ConfigurationNode node);
}
