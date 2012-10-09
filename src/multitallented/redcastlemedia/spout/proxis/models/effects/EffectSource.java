package multitallented.redcastlemedia.spout.proxis.models.effects;

import java.util.HashSet;
import multitallented.redcastlemedia.spout.proxis.models.SourceType;
import multitallented.redcastlemedia.spout.proxis.models.conditions.ConditionSource;
import multitallented.redcastlemedia.spout.proxis.models.skill.SkillSource;
import multitallented.redcastlemedia.spout.proxis.models.users.User;
import org.spout.api.entity.Entity;

/**
 *
 * @author Multitallented
 */
public abstract class EffectSource extends SkillSource {
	public final HashSet<ConditionSource> conditions = new HashSet<>();
	
	public EffectSource() {
		super(SourceType.EFFECT);
	}
    
    public abstract void execute(User user, Entity target);
}
