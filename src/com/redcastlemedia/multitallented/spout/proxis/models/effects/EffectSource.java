package com.redcastlemedia.multitallented.spout.proxis.models.effects;

import com.redcastlemedia.multitallented.spout.proxis.Proxis;
import com.redcastlemedia.multitallented.spout.proxis.models.SourceType;
import com.redcastlemedia.multitallented.spout.proxis.models.conditions.ConditionSource;
import com.redcastlemedia.multitallented.spout.proxis.models.skills.Skill.CastSkill;
import com.redcastlemedia.multitallented.spout.proxis.models.skills.SkillSource;
import com.redcastlemedia.multitallented.spout.proxis.models.targets.TargetSource;
import com.redcastlemedia.multitallented.spout.proxis.models.users.User;
import java.util.HashSet;
import org.spout.api.geo.cuboid.Block;
import org.spout.api.util.config.ConfigurationNode;
import org.spout.vanilla.component.living.VanillaEntity;

/**
 *
 * @author Multitallented
 */
public abstract class EffectSource extends SkillSource {
    public final HashSet<ConditionSource> conditions = new HashSet<>();
    public final String NAME;
    public TargetSource target = null;

    public EffectSource(String name) {
        super(SourceType.EFFECT);
        this.NAME = name;
    }
    
    public abstract void execute(Proxis plugin, CastSkill cs, User target, ConfigurationNode node);
    
    public abstract void execute(Proxis plugin, CastSkill cs, VanillaEntity target, ConfigurationNode node);
    
    public abstract void execute(Proxis plugin, CastSkill cs, Block target, ConfigurationNode node);
}
