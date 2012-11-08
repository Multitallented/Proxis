package multitallented.redcastlemedia.spout.proxis.models.skills;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import multitallented.redcastlemedia.spout.proxis.Proxis;
import multitallented.redcastlemedia.spout.proxis.models.conditions.Condition;
import multitallented.redcastlemedia.spout.proxis.models.effects.Effect;
import multitallented.redcastlemedia.spout.proxis.models.targets.Target;
import multitallented.redcastlemedia.spout.proxis.models.users.User;
import org.spout.api.util.config.ConfigurationHolder;
import org.spout.api.util.config.yaml.YamlConfiguration;

/**
 *
 * @author Multitallented
 */
public class Skill extends YamlConfiguration {
    public final ConfigurationHolder NAME = new ConfigurationHolder("default", "name");
    private final Proxis plugin;
    private final ArrayList<HashMap<Condition, String>> preCastConditions;
    private final ArrayList<HashMap<Condition, String>> postCastConditions;
    private final HashSet<Target> targets;
    private final ArrayList<HashMap<Effect, String>> preCastEffects;
    private final ArrayList<HashMap<Effect, String>> postCastEffects;

    public Skill(Proxis plugin, String filename, HashSet<Target> targets, ArrayList<HashMap<Effect, String>> preCastEffects,
            ArrayList<HashMap<Effect, String>> postCastEffects, ArrayList<HashMap<Condition, String>> preCastConditions,
            ArrayList<HashMap<Condition, String>> postCastConditions) {
        super(new File(new File(plugin.getDataFolder(), "skills"), filename + ".yml"));
        this.plugin = plugin;
        this.targets = targets;
        this.preCastConditions = preCastConditions;
        this.postCastConditions = postCastConditions;
        this.preCastEffects = preCastEffects;
        this.postCastEffects = postCastEffects;
    }
    
    public void useSkill(User user) {
        HashMap<String, HashSet<Object>> targetMap = new HashMap<>();
        for (Target tar : targets) {
            targetMap.put(tar.NAME, tar.getTargets(plugin, user));
        }
        HashSet<Object> tempSet = new HashSet<>();
        tempSet.add(user);
        targetMap.put("self", tempSet);
        
        CastSkill cs = new CastSkill(this, targetMap);
        cs.checkConditions();
    }

    public class CastSkill {
        private final HashMap<String, HashSet<Object>> targetMap;
        private final Skill skill;
        public final HashMap<Integer, HashSet<SkillResult>> pendingConditions = new HashMap<>();
        public final HashMap<Integer, Integer> pendingConditionsSize = new HashMap<>();
        public boolean preCast = true;
        
        public CastSkill(Skill skill, HashMap<String, HashSet<Object>> targetMap) {
            this.targetMap = targetMap;
            this.skill = skill;
        }
        
        public void checkConditions() {
            if (preCast) {
                for (int i=0; i<preCastConditions.size(); i++) {
                    HashMap<Condition, String> conditions = preCastConditions.get(i);
                    pendingConditionsSize.put(i, conditions.size());
                    for (Condition con : conditions.keySet()) {
                        con.testCondition(this, targetMap.get(conditions.get(con)));
                    }
                }
            } else {
                for (int i=0; i<postCastConditions.size(); i++) {
                    HashMap<Condition, String> conditions = postCastConditions.get(i);
                    pendingConditionsSize.put(i, conditions.size());
                    for (Condition con : conditions.keySet()) {
                        con.testCondition(this, targetMap.get(conditions.get(con)));
                    }
                }
            }
        }
        
        public void checkInCondition(int index, SkillResult result) {
            if (result != SkillResult.FAILED) {
                if (pendingConditionsSize.get(index) <= pendingConditions.get(index).size()) {
                    executeEffects(index);
                } else {
                    return;
                }
            } else if (!preCast) {
                return;
            } else {
                int difference = pendingConditionsSize.get(index) - pendingConditions.get(index).size();
                for (int i=0; i<difference ; i++) {
                    pendingConditions.get(index).add(SkillResult.FAILED);
                }
            }
            for (int i : pendingConditions.keySet()) {
                if (pendingConditionsSize.get(i) > pendingConditions.get(i).size()) {
                    return;
                }
            }
            pendingConditions.clear();
            pendingConditionsSize.clear();
            preCast = false;
            //TODO announce the skill cast?
            checkConditions();
        }
        
        private void executeEffects(int index) {
            if (preCast) {
                for (Effect effect : preCastEffects.get(index).keySet()) {
                    effect.execute(this, targetMap.get(preCastEffects.get(index).get(effect)));
                }
            } else {
                for (Effect effect : postCastEffects.get(index).keySet()) {
                    effect.execute(this, targetMap.get(postCastEffects.get(index).get(effect)));
                }
            }
        }
        
        public Skill getSkill() {
            return skill;
        }
        
        public HashMap<String, HashSet<Object>> getTargetMap() {
            return targetMap;
        }
    }
}