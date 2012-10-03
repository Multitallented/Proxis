package multitallented.redcastlemedia.spout.proxis.models.users;

import java.util.ArrayList;
import java.util.HashMap;
import multitallented.redcastlemedia.spout.proxis.models.effects.Effect;
import multitallented.redcastlemedia.spout.proxis.models.skill.SkillComponentType;

/**
 *
 * @author Multitallented
 */
public class User {
    private String locale = "en";
    private final String name;
    private ArrayList<Effect> effects = new ArrayList<>();
    private HashMap<SkillComponentType, Double> experience = new HashMap<>();
    
    public User(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setLocale(String locale) {
        this.locale = locale;
    }
    public String getLocale() {
        return locale;
    }
    public synchronized HashMap<SkillComponentType, Double> getExperience() {
        return experience;
    }
    public synchronized void putExperience(SkillComponentType type, double amount) {
        this.experience.put(type, amount);
    }
}
