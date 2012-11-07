package multitallented.redcastlemedia.spout.proxis.models.conditions;

import multitallented.redcastlemedia.spout.proxis.models.SourceType;
import multitallented.redcastlemedia.spout.proxis.models.skills.SkillResult;
import multitallented.redcastlemedia.spout.proxis.models.skills.SkillSource;
import multitallented.redcastlemedia.spout.proxis.models.users.User;
import org.spout.api.geo.cuboid.Block;
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
    
    public abstract SkillResult testCondition(User user);
    
    public abstract SkillResult testCondition(Block block);
    
    public abstract SkillResult testCondition(VanillaEntity ve);
    
    public abstract void useCondition(User user);
    
    public abstract void useCondition(Block block);
    
    public abstract void useCondition(VanillaEntity ve);
}
