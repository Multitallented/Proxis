package multitallented.redcastlemedia.spout.proxis.models.skill;

import multitallented.redcastlemedia.spout.proxis.models.users.User;
import org.spout.api.entity.Player;
import org.spout.vanilla.component.living.VanillaEntity;

/**
 *
 * @author Multitallented
 */
public interface UsableSkill {
    
    public abstract SkillResult canUse(String skillName, User user, VanillaEntity target);
    
    public abstract SkillResult canUse(String skillName, User user, Player player);
    
    public abstract void execute(String skillName, User user, VanillaEntity target);
    
    public abstract void execute(String skillName, User user, Player player);
}
