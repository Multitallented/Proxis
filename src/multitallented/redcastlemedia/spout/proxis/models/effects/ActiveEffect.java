package multitallented.redcastlemedia.spout.proxis.models.effects;

import multitallented.redcastlemedia.spout.proxis.models.users.User;
import org.spout.api.entity.Player;
import org.spout.vanilla.component.living.VanillaEntity;

/**
 *
 * @author Multitallented
 */
public interface ActiveEffect {
    public abstract void execute(String skillName, User user, Player target);
    
    public abstract void execute(String skillName, User user, VanillaEntity target);
}
