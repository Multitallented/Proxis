package multitallented.redcastlemedia.spout.proxis.models.skill;

import java.util.HashSet;
import multitallented.redcastlemedia.spout.proxis.models.effects.EffectType;

/**
 *
 * @author Multitallented
 */
public abstract class Skill {
    public final String NAME;
    public final HashSet<EffectType> TYPES = new HashSet<>();
    public final SkillSource SOURCE;
    
    public Skill(String name, SkillSource source) {
        SOURCE = source;
        NAME = name;
    }
}
