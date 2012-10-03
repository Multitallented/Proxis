package multitallented.redcastlemedia.spout.proxis.models.effects;

import multitallented.redcastlemedia.spout.proxis.models.SourceType;
import multitallented.redcastlemedia.spout.proxis.models.skill.SkillSource;

/**
 *
 * @author Multitallented
 */
public abstract class EffectSource extends SkillSource {
    
    public EffectSource(String sourceName) {
        super(SourceType.EFFECT);
    }
}
