package com.redcastlemedia.multitallented.spout.proxis.models.targets;

import com.redcastlemedia.multitallented.spout.proxis.Proxis;
import com.redcastlemedia.multitallented.spout.proxis.models.SourceType;
import com.redcastlemedia.multitallented.spout.proxis.models.skills.SkillSource;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Multitallented
 */
public abstract class TargetSource extends SkillSource {
    public TargetSource() {
        super(SourceType.TARGET);
    }
    
    public abstract HashSet<Object> getTargets(Proxis plugin, HashSet<String> types, HashMap<String, Object> node);
}