package com.redcastlemedia.multitallented.spout.proxis.controllers;

import com.redcastlemedia.multitallented.spout.proxis.Proxis;
import com.redcastlemedia.multitallented.spout.proxis.models.SkillClass;
import java.util.HashMap;

/**
 *
 * @author Multitallented
 */
public class ClassManager {
    private final Proxis plugin;
    private final HashMap<String, SkillClass> classes = new HashMap<>();
    public ClassManager(Proxis plugin) {
        this.plugin = plugin;
        loadClasses();
    }
    
    private void loadClasses() {
        //TODO finish this
    }
    
    public SkillClass getSkillClass(String name) {
        return classes.get(name);
    }
}
