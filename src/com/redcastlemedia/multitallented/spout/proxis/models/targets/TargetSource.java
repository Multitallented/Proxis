package com.redcastlemedia.multitallented.spout.proxis.models.targets;

import com.redcastlemedia.multitallented.spout.proxis.Proxis;
import com.redcastlemedia.multitallented.spout.proxis.models.SourceType;
import com.redcastlemedia.multitallented.spout.proxis.models.skills.SkillSource;
import com.redcastlemedia.multitallented.spout.proxis.models.users.User;
import java.util.HashMap;
import java.util.HashSet;
import org.spout.api.geo.discrete.Point;

/**
 *
 * @author Multitallented
 */
public abstract class TargetSource extends SkillSource {
    public TargetSource() {
        super(SourceType.TARGET);
    }
    
    public abstract TargetScheme getTargets(Proxis plugin, HashSet<String> types,
            HashMap<String, Object> node, User caster, Point originPoint);
    
    public class TargetScheme {
        public final HashSet<Object> targets;
        public final Point originPoint;
        public TargetScheme(HashSet<Object> targets, Point originPoint) {
            this.targets = targets;
            this.originPoint = originPoint;
        }
    }
}