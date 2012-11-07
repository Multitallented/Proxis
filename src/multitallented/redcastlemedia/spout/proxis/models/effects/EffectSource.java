package multitallented.redcastlemedia.spout.proxis.models.effects;

import java.util.HashSet;
import multitallented.redcastlemedia.spout.proxis.models.SourceType;
import multitallented.redcastlemedia.spout.proxis.models.conditions.ConditionSource;
import multitallented.redcastlemedia.spout.proxis.models.skills.SkillSource;
import multitallented.redcastlemedia.spout.proxis.models.targets.TargetSource;
import multitallented.redcastlemedia.spout.proxis.models.users.User;
import org.spout.api.geo.cuboid.Block;
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
    
    public abstract void execute(User user, User target);
    
    public abstract void execute(User user, VanillaEntity target);
    
    public abstract void execute(User user, Block target);
}
