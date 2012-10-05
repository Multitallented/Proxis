package multitallented.redcastlemedia.spout.proxis.models.targets;

import java.util.ArrayList;
import multitallented.redcastlemedia.spout.proxis.models.SourceType;
import multitallented.redcastlemedia.spout.proxis.models.skill.Skill;
import multitallented.redcastlemedia.spout.proxis.models.skill.SkillSource;
import multitallented.redcastlemedia.spout.proxis.models.users.User;

/**
 *
 * @author Multitallented
 */
public abstract class TargetSource extends SkillSource {
    public TargetSource() {
        super(SourceType.SELECTOR);
    }
    
    public abstract ArrayList<Object> getTarget(Skill skillName, User user);
    
    public abstract Class getTargetType();
}
