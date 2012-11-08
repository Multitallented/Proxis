package multitallented.redcastlemedia.spout.proxis.models.users;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import multitallented.redcastlemedia.spout.proxis.Proxis;
import multitallented.redcastlemedia.spout.proxis.models.skills.Skill;
import multitallented.redcastlemedia.spout.proxis.models.states.UserState;
import org.spout.api.util.config.ConfigurationHolder;

/**
 *
 * @author Multitallented
 */
public class User {
    public static final ConfigurationHolder LOCALE = new ConfigurationHolder("locale", "en");
    public final String NAME;
    public final HashSet<UserState> states = new HashSet<>();
    private HashMap<String, Double> experience = new HashMap<>();
    private HashMap<String, Skill> skills = new HashMap<>();

    public User(File file, Proxis plugin) {
        NAME = file.getName().replace(".yml", "");
        loadSkills(plugin);
    }
    
    public synchronized HashMap<String, Double> getExperience() {
        return experience;
    }
    public synchronized void putExperience(String type, double amount) {
        experience.put(type, amount);
    }
    
    public synchronized void putSkill(String name, Skill skill) {
        skills.put(name, skill);
    }
    
    public synchronized void removeSkill(String name) {
        skills.remove(name);
    }
    
    private void loadSkills(Proxis plugin) {
//        outer: for (String s : super.getChild("skills").getKeys(false)) {
//            TargetSource ts = plugin.getSkillManager().getTargetSource(super.getNode("skills." + s + ".target.pattern").getString());
//            if (ts == null) {
//                plugin.log(Level.SEVERE, Proxis.NAME + " " + NAME + ".yml skill " + s + " target is corrupted!");
//                continue;
//            }
//            
//            HashSet<EffectSource> effects = new HashSet<>();
//            for (String effectName : super.getChild("skills." + s + ".effects").getKeys(false)) {
//                EffectSource es = plugin.getSkillManager().getEffectSource(effectName);
//                if (es == null) {
//                    plugin.log(Level.SEVERE, Proxis.NAME + " " + NAME + ".yml skill " + s + " " + es + " is corrupted!");
//                    continue outer;
//                }
//                effects.add(es);
//            }
//            HashSet<ConditionSource> conditions = new HashSet<>();
//            for (String conditionName : super.getChild("skills." + s + ".conditions").getKeys(false)) {
//                ConditionSource cs = plugin.getSkillManager().getConditionSource(conditionName);
//                if (cs == null) {
//                    plugin.log(Level.SEVERE, Proxis.NAME + " " + NAME + ".yml skill " + s + " " + cs + " is corrupted!");
//                    continue outer;
//                }
//                conditions.add(cs);
//            }
//            
//            
//            skills.put(s, new Skill(plugin, s, ts, effects, conditions));
//        }
    }
}
