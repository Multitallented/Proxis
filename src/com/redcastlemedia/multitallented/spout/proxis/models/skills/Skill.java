package com.redcastlemedia.multitallented.spout.proxis.models.skills;

import com.redcastlemedia.multitallented.spout.proxis.Proxis;
import com.redcastlemedia.multitallented.spout.proxis.models.conditions.Condition;
import com.redcastlemedia.multitallented.spout.proxis.models.conditions.ConditionSource;
import com.redcastlemedia.multitallented.spout.proxis.models.effects.Effect;
import com.redcastlemedia.multitallented.spout.proxis.models.targets.Target;
import com.redcastlemedia.multitallented.spout.proxis.models.users.User;
import com.redcastlemedia.multitallented.spout.proxis.models.users.states.BuiltInUserStates;
import com.redcastlemedia.multitallented.spout.proxis.models.users.states.UserState;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import org.spout.api.Spout;
import org.spout.api.entity.Entity;
import org.spout.api.geo.cuboid.Block;
import org.spout.api.util.config.ConfigurationHolder;
import org.spout.api.util.config.ConfigurationNode;
import org.spout.api.util.config.yaml.YamlConfiguration;

/**
 *
 * @author Multitallented
 */
public class Skill {
    public final String NAME;
    private final Proxis plugin;
    private final ArrayList<HashMap<Condition, String>> preCastConditions;
    private final ArrayList<HashMap<Condition, String>> postCastConditions;
    private final HashSet<Target> targets;
    private final ArrayList<HashMap<Effect, String>> preCastEffects;
    private final ArrayList<HashMap<Effect, String>> postCastEffects;

    public Skill(Proxis plugin, String name, HashSet<Target> targets, ArrayList<HashMap<Effect, String>> preCastEffects,
            ArrayList<HashMap<Effect, String>> postCastEffects, ArrayList<HashMap<Condition, String>> preCastConditions,
            ArrayList<HashMap<Condition, String>> postCastConditions) {
        NAME = name;
        this.plugin = plugin;
        this.targets = targets;
        this.preCastConditions = preCastConditions;
        this.postCastConditions = postCastConditions;
        this.preCastEffects = preCastEffects;
        this.postCastEffects = postCastEffects;
        
        for (HashMap<Condition, String> tempMap : preCastConditions) {
            tempMap.put(new Condition(plugin, new ConditionSource("ConditionTargetable") {

                        @Override
                        public SkillResult testCondition(Proxis plugin, CastSkill cs, User user, HashMap<String, Object> node) {
                            for (UserState us : user.getStates().values()) {
                                if (us.getDefaultStates().contains(BuiltInUserStates.NO_INCOMING_SKILLS) ||
                                        us.getDefaultStates().contains(BuiltInUserStates.NO_SKILLS)) {
                                    us.sendCancelledMessage(((User) cs.getTargetMap().get("self").iterator().next()).NAME, UserState.CancelledMessageTypes.SKILL);
                                    return SkillResult.FAILED;
                                }
                            }
                            return SkillResult.NORMAL;
                        }

                        @Override
                        public SkillResult testCondition(Proxis plugin, CastSkill cs, Block block, HashMap<String, Object> node) {
                            return SkillResult.NORMAL;
                        }

                        @Override
                        public SkillResult testCondition(Proxis plugin, CastSkill cs, Entity ve, HashMap<String, Object> node) {
                            return SkillResult.NORMAL;
                        }

            }, null), "ConditionTargetable");
        }
    }
    
    public void applyState(User user, UserState us) {
        plugin.getDamageManager().putBuiltInUserStates(user.NAME, us.getDefaultStates());
        us.apply(user);
        //TODO apply State for duration + ticks?
    }
    
    public void applyState(Block block, UserState us) {
        //TODO applyState block
    }
    
    public void applyState(Entity e, UserState us) {
        //TODO applyState Entity
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
                return;
            }
            boolean success = false;
            for (int i : pendingConditions.keySet()) {
                if (pendingConditionsSize.get(i) > pendingConditions.get(i).size()) {
                    return;
                }
                if (!pendingConditions.get(i).contains(SkillResult.FAILED)) {
                    success = true;
                }
            }
            if (!success) {
                return;
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