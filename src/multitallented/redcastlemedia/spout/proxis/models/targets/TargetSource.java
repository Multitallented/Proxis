package multitallented.redcastlemedia.spout.proxis.models.targets;

import java.util.ArrayList;
import multitallented.redcastlemedia.spout.proxis.models.SourceType;
import multitallented.redcastlemedia.spout.proxis.models.skills.Skill;
import multitallented.redcastlemedia.spout.proxis.models.skills.SkillSource;
import multitallented.redcastlemedia.spout.proxis.models.users.User;

/**
 *
 * @author Multitallented
 */
public abstract class TargetSource extends SkillSource {
    public TargetSource() {
        super(SourceType.TARGET);
    }
    
    public abstract ArrayList<ArrayList<Object>> getTargets(Skill skill, User user);
    
    public abstract ArrayList<Class> getTargetTypes();
}