package com.redcastlemedia.multitallented.spout.proxis.models.conditions;

import com.redcastlemedia.multitallented.spout.proxis.Proxis;
import com.redcastlemedia.multitallented.spout.proxis.models.SourceType;
import com.redcastlemedia.multitallented.spout.proxis.models.skills.Skill.CastSkill;
import com.redcastlemedia.multitallented.spout.proxis.models.skills.SkillResult;
import com.redcastlemedia.multitallented.spout.proxis.models.skills.SkillSource;
import com.redcastlemedia.multitallented.spout.proxis.models.users.User;
import java.util.HashMap;
import org.spout.api.entity.Entity;
import org.spout.api.geo.cuboid.Block;
import org.spout.api.util.config.ConfigurationNode;

/**
 *
 * @author Multitallented
 */
public abstract class ConditionSource extends SkillSource {
    public final String name;
    
    public ConditionSource(String name) {
        super(SourceType.CONDITION);
        this.name = name;
    }
    
    public abstract SkillResult testCondition(Proxis plugin, CastSkill cs, User user, HashMap<String, Object> node);
    
    public abstract SkillResult testCondition(Proxis plugin, CastSkill cs, Block block, HashMap<String, Object> node);
    
    public abstract SkillResult testCondition(Proxis plugin, CastSkill cs, Entity ve, HashMap<String, Object> node);
}
