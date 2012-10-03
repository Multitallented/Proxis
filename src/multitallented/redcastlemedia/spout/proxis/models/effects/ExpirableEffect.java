package multitallented.redcastlemedia.spout.proxis.models.effects;

import multitallented.redcastlemedia.spout.proxis.models.users.User;
import org.spout.api.entity.Entity;

/**
 *
 * @author Multitallented
 */
public interface ExpirableEffect extends ActiveEffect {

	public abstract long setDuration();

	public abstract long setPeriod();

	public abstract void applyEffect(String skillName, User user, Entity target);

	public abstract void removeEffect(String skillName, User user, Entity target);

	public abstract void tick(Entity ve);
}
