package multitallented.redcastlemedia.spout.proxis.models.effects;

import multitallented.redcastlemedia.spout.proxis.models.users.User;
import org.spout.api.entity.Entity;

/**
 *
 * @author Multitallented
 */
public interface ActiveEffect {
    public abstract void execute(String skillName, User user, Entity target);
}
