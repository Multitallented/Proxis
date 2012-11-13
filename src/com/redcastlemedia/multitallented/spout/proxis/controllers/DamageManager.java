package com.redcastlemedia.multitallented.spout.proxis.controllers;

import com.redcastlemedia.multitallented.spout.proxis.Proxis;
import com.redcastlemedia.multitallented.spout.proxis.models.users.states.BuiltInUserStates;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Multitallented
 */
public class DamageManager {
    private final Proxis plugin;
    private final HashMap<String, HashSet<BuiltInUserStates>> builtInUserStates = new HashMap<>();
    
    public DamageManager(Proxis plugin) {
        this.plugin = plugin;
        //TODO finish the damage manager
    }
    
    public void putBuiltInUserStates(String name, HashSet<BuiltInUserStates> states) {
        builtInUserStates.put(name, states);
    }
    
    public HashSet<BuiltInUserStates> getUserStates(String name) {
        return builtInUserStates.get(name);
    }
}
