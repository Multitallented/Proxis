package multitallented.redcastlemedia.spout.proxis.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.Map.Entry;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import multitallented.redcastlemedia.spout.proxis.Proxis;
import multitallented.redcastlemedia.spout.proxis.models.conditions.ConditionSource;
import multitallented.redcastlemedia.spout.proxis.models.effects.EffectSource;
import multitallented.redcastlemedia.spout.proxis.models.skills.SkillSource;
import multitallented.redcastlemedia.spout.proxis.models.targets.TargetSource;

/**
 *
 * @author Multitallented
 */
public class SkillJarManager {
    private final LinkedHashMap<String, SkillSource> skills;
    private final HashMap<String, File> skillFiles;
    private final Proxis plugin;
    private final File dir;
    private final URLClassLoader classLoader;
    public SkillJarManager(Proxis plugin) {
        skills = new LinkedHashMap<>();
        skillFiles = new HashMap<>();
        this.plugin = plugin;
        dir = new File(plugin.getDataFolder(), "effects");
        dir.mkdir();

        List<URL> urls = new ArrayList<>();
        if (dir.list().length == 0) {
            plugin.log(Level.WARNING, "No effects found in the effects folder!");
        }
        for (String skillFile : dir.list()) {
            if (skillFile.contains(".jar")) {
                File file = new File(dir, skillFile);
                String name = skillFile.toLowerCase().replace(".jar", "").replace("skill", "");
                if (skillFiles.containsKey(name)) {
                    plugin.log(Level.WARNING, "Duplicate skill jar found! Please remove " + skillFile + " or " + skillFiles.get(name).getName());
                    continue;
                }
                skillFiles.put(name, file);
                try {
                    urls.add(file.toURI().toURL());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }
        ClassLoader cl = plugin.getClass().getClassLoader();
        classLoader = URLClassLoader.newInstance(urls.toArray(new URL[0]), cl);

        loadSkillSources();
    }

    public SkillSource getSkillSource(String name) {
        if (name == null) {
            return null;
        }
        return skills.get(name);
    }

    public boolean hasSkillSource(String name) {
        return skills.containsKey(name);
    }

    public SkillSource loadSkillSource(File file) {
        try {
            JarFile jarFile = new JarFile(file);
            Enumeration<JarEntry> entries = jarFile.entries();

            String mainClass = null;
            while (entries.hasMoreElements()) {
                JarEntry element = entries.nextElement();
                if (element.getName().equalsIgnoreCase("skill.info")) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(jarFile.getInputStream(element)));
                    mainClass = reader.readLine().substring(12);
                    break;
                }
            }

            if (mainClass != null) {
                Class<?> clazz = Class.forName(mainClass, true, classLoader);
                for (Class<?> subclazz : clazz.getClasses()) {
                    Class.forName(subclazz.getName(), true, classLoader);
                }
                Class<? extends SkillSource> effectClass = clazz.asSubclass(SkillSource.class);
                Constructor<? extends SkillSource> ctor = effectClass.getConstructor(plugin.getClass());
                SkillSource skill = ctor.newInstance(plugin);
                skill.init(plugin, "jar;" + jarFile.getName());
                return skill;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            plugin.log(Level.WARNING, "The skill " + file.getName() + " failed to load");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Load all the skills into skill map.
     */
    public void loadSkillSources() {
        for (Entry<String, File> entry : skillFiles.entrySet()) {
            // if the Skill is already loaded, skip it
            if (hasSkillSource(entry.getKey())) {
                continue;
            }

            SkillSource skill = loadSkillSource(entry.getValue());
            if (skill != null) {
                skills.put(entry.getKey(),skill);
            }
        }
    }
    
    public TargetSource getTargetSource(String name) {
        SkillSource ss = skills.get(name);
        if (ss == null || !ss.getClass().equals(TargetSource.class)) {
            return null;
        }
        
        return (TargetSource) ss;
    }
    public ConditionSource getConditionSource(String name) {
        SkillSource ss = skills.get(name);
        if (ss == null || !ss.getClass().equals(ConditionSource.class)) {
            return null;
        }
        
        return (ConditionSource) ss;
    }
    public EffectSource getEffectSource(String name) {
        SkillSource ss = skills.get(name);
        if (ss == null || !ss.getClass().equals(EffectSource.class)) {
            return null;
        }
        
        return (EffectSource) ss;
    }

    private boolean loadSkillSource(String name) {
        // If the skill is already loaded, don't try to load it
        if (hasSkillSource(name)) {
            return true;
        }

        // Lets try loading the skill file
        SkillSource skill = loadSkillSource(skillFiles.get(name.toLowerCase()));
        if (skill == null) {
            return false;
        }

        skills.put(name, skill);
        return true;
    }
}
