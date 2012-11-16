package com.redcastlemedia.multitallented.spout.proxis.models.targets;

import com.redcastlemedia.multitallented.spout.proxis.Proxis;
import com.redcastlemedia.multitallented.spout.proxis.models.users.User;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Multitallented
 */
public class Target {
    private final TargetSource ts;
    private final HashMap<String, Object> config;
    private final HashSet<String> targetTypes = new HashSet<>();
    public final String NAME;
    
    public Target(TargetSource ts, HashMap<String, Object> config) {
        this.ts = ts;
        this.config = config;
        String name = "Invalid";
        try {
            for (String s : ((HashMap<String, Object>) config.get("pattern")).keySet()) {
                targetTypes.add(s);
            }
            name = (String) config.get("name");
        } catch (ClassCastException cce) {
            
        }
        NAME = name;
    }
    
    public HashSet<Object> getTargets(Proxis plugin, User user) {
        HashSet<Object> tempSet = ts.getTargets(plugin, targetTypes, config);
        return tempSet;
    }
}
