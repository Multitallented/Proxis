package multitallented.redcastlemedia.spout.proxis.models.effects;

import java.util.HashSet;
import multitallented.redcastlemedia.spout.proxis.models.SourceType;
import multitallented.redcastlemedia.spout.proxis.models.conditions.ConditionSource;
import multitallented.redcastlemedia.spout.proxis.models.skill.SkillSource;
import multitallented.redcastlemedia.spout.proxis.models.users.User;
import org.spout.api.geo.cuboid.Block;
import org.spout.api.util.config.ConfigurationHolder;
import org.spout.vanilla.component.living.VanillaEntity;

/**
 *
 * @author Multitallented
 */
public abstract class EffectSource extends SkillSource {
    public final HashSet<ConditionSource> conditions = new HashSet<>();
    public final ConfigurationHolder TARGET = new ConfigurationHolder("self", "target");

	public EffectSource() {
		super(SourceType.EFFECT);
	}

	public abstract void execute(User user, User target);

	public abstract void execute(User user, VanillaEntity target);

	public abstract void execute(User user, Block target);
}
