package multitallented.redcastlemedia.spout.proxis.models.effects;

import multitallented.redcastlemedia.spout.proxis.models.skill.SkillComponentType;
import java.util.ArrayList;

/**
 *
 * @author Multitallented
 */
public abstract class Effect {
    public Effect() {
        
    }
    
    public abstract ArrayList<SkillComponentType> getEffectTypes();
}
