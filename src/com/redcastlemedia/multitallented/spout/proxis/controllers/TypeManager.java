package com.redcastlemedia.multitallented.spout.proxis.controllers;

import com.redcastlemedia.multitallented.spout.proxis.Proxis;
import java.io.File;
import java.util.HashMap;
import java.util.logging.Level;
import org.spout.api.util.config.yaml.YamlConfiguration;

/**
 *
 * @author Multitallented
 */
public class TypeManager {
    public final HashMap<String, HashMap<String, Integer>> types = new HashMap<>();
    private final Proxis plugin;
    
    public TypeManager(Proxis plugin) {
        this.plugin = plugin;
        loadConfig();
    }
    
    private void loadConfig() {
        YamlConfiguration typesConfig;
        try {
            File typeFile = new File(plugin.getDataFolder(), "types.yml");
            if (!typeFile.exists()) {
                typeFile.createNewFile();
            }
            typesConfig = new YamlConfiguration(typeFile);
            typesConfig.load();
        } catch (Exception ioe) {
            plugin.log(Level.SEVERE, Proxis.NAME + " failed to load types.yml");
            return;
        }
        for (String s : typesConfig.getKeys(false)) {
            HashMap<String, Integer> sources = new HashMap<>();
            for (String st : typesConfig.getChild(s).getKeys(false)) {
                sources.put(st, typesConfig.getChild(s + "." + st).getInt());
            }
            types.put(s, sources);
        }
    }
}
/*
 * fire:
 *   takedamage;fire: 1
 *   kill;fire: 20
 */