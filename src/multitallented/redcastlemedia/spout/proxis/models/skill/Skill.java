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
    public final SkillConfiguration SKILL_CONFIG;
    
    public Skill(String name, SkillSource source, SkillConfiguration skillConfig) {
        SOURCE = source;
        NAME = name;
        SKILL_CONFIG = skillConfig;
    }
}
