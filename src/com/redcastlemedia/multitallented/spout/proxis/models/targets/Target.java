package com.redcastlemedia.multitallented.spout.proxis.models.targets;

import com.redcastlemedia.multitallented.spout.proxis.Proxis;
import com.redcastlemedia.multitallented.spout.proxis.models.targets.TargetSource.TargetScheme;
import com.redcastlemedia.multitallented.spout.proxis.models.users.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import org.spout.api.geo.discrete.Point;

/**
 *
 * @author Multitallented
 */
public class Target {
    private final TargetSource ts;
    private final HashMap<String, Object> node;
    private final HashSet<String> targetTypes = new HashSet<>();
    public final String NAME;
    
    public Target(String name, TargetSource ts, HashMap<String, Object> node) {
        this.ts = ts;
        this.node = node;
        NAME = name;
        try {
            targetTypes.addAll((ArrayList<String>) node.get("target-types"));
        } catch (Exception e) {
            
        }
    }
    
    public Point getEndPoint(Proxis plugin, User user, Point originPoint) {
        return null;
    }
    
    public HashMap<String, Object> getNode() {
        return node;
    }
    
    public TargetScheme getTargets(Proxis plugin, User user, Point originPoint) {
        return ts.getTargets(plugin, targetTypes, node, user, originPoint);
    }
}
