package multitallented.redcastlemedia.spout.proxis.managers;

import java.util.HashMap;
import multitallented.redcastlemedia.spout.proxis.Proxis;
import multitallented.redcastlemedia.spout.proxis.models.skill.Skill;

/**
 *
 * @author Multitallented
 */
public class SkillManager {
    private final Proxis proxis;
    private SkillJarManager sjm;
    private final static HashMap<String, Skill> skills = new HashMap<>();
    public SkillManager(Proxis proxis) {
        this.proxis = proxis;
        this.sjm = new SkillJarManager(proxis);
    }
    
    public void loadAllSkills() {
        sjm.loadSkills();
    }
    
    public static Skill getSkill(String name) {
        return skills.get(name);
    }
	
	
}
