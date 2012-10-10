package multitallented.redcastlemedia.spout.proxis.models.conditions;

import multitallented.redcastlemedia.spout.proxis.models.SourceType;
import multitallented.redcastlemedia.spout.proxis.models.skill.SkillResult;
import multitallented.redcastlemedia.spout.proxis.models.skill.SkillSource;
import multitallented.redcastlemedia.spout.proxis.models.users.User;
import org.spout.api.entity.Entity;
import org.spout.api.geo.cuboid.Block;
import org.spout.vanilla.component.living.VanillaEntity;

/**
 *
 * @author Multitallented
 */
public abstract class ConditionSource extends SkillSource {
    public ConditionSource() {
        super(SourceType.CONDITION);
    }
    
    public abstract SkillResult testCondition(User user);
    
    public abstract SkillResult testCondition(Block block);
    
    public abstract SkillResult testCondition(VanillaEntity ve);
    
    public abstract void useCondition(User user);
}
