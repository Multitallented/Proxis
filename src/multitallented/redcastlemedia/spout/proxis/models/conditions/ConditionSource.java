package multitallented.redcastlemedia.spout.proxis.models.conditions;

import multitallented.redcastlemedia.spout.proxis.models.SourceType;
import multitallented.redcastlemedia.spout.proxis.models.skill.SkillSource;
import multitallented.redcastlemedia.spout.proxis.models.users.User;
import org.spout.api.entity.Entity;

/**
 *
 * @author Multitallented
 */
public abstract class ConditionSource extends SkillSource {
    public ConditionSource() {
        super(SourceType.CONDITION);
    }
    
    public abstract boolean testCondition(String skillName, User user, Entity target);
}
