package multitallented.redcastlemedia.spout.proxis.models.effects;

import multitallented.redcastlemedia.spout.proxis.models.users.User;
import org.spout.api.entity.Player;
import org.spout.vanilla.component.living.VanillaEntity;

/**
 *
 * @author Multitallented
 */
public interface ExpirableEffect extends ActiveEffect {
    
    public abstract void setupTime(long duration, long period);
    
    public abstract void applyEffect(String skillName, User user, Player target);
    
    public abstract void applyEffect(String skillName, User user, VanillaEntity target);
    
    public abstract void removeEffect(String skillName, User user, Player target);
    
    public abstract void removeEffect(String skillName, User user, VanillaEntity target);
    
    public abstract void tick(VanillaEntity ve);
            
    public abstract void tick(User user);
}
