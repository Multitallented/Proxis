package com.redcastlemedia.multitallented.spout.proxis.models;

import com.redcastlemedia.multitallented.spout.proxis.Proxis;
import com.redcastlemedia.multitallented.spout.proxis.models.skills.Skill;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import org.spout.api.util.config.FileConfiguration;
import org.spout.api.util.config.yaml.YamlConfiguration;

/**
 *
 * @author Multitallented
 */
public class SkillConfigManager {
    private final HashMap<String, Skill> skills = new HashMap<>();
    
    public SkillConfigManager(Proxis proxis) {
        File folder = new File(proxis.getDataFolder(), "skills");
        if (!folder.exists()) {
            folder.mkdirs();
        }
        for (File file : folder.listFiles()) {
            FileConfiguration config = new YamlConfiguration(file);
            try {
                config.load();
                //name
                String name = config.getNode("name").getString("noname");
                //types
                HashSet<String> types = new HashSet<>();
                for (String type : config.getNode("types").getStringList(new ArrayList<String>())) {
                    types.add(type);
                }
                
                //skills.put(name, new Skill(proxis, name));
            } catch (Exception e) {
                proxis.log(Level.SEVERE, Proxis.NAME + "failed to load " + file.getName());
            }
        }
    }
}
