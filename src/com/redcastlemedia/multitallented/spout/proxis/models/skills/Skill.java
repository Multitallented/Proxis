package com.redcastlemedia.multitallented.spout.proxis.models.skills;

import com.redcastlemedia.multitallented.spout.proxis.Proxis;
import com.redcastlemedia.multitallented.spout.proxis.api.events.SkillCastEvent;
import com.redcastlemedia.multitallented.spout.proxis.api.events.SkillPreCastEvent;
import com.redcastlemedia.multitallented.spout.proxis.models.conditions.Condition;
import com.redcastlemedia.multitallented.spout.proxis.models.conditions.ConditionSource;
import com.redcastlemedia.multitallented.spout.proxis.models.effects.Effect;
import com.redcastlemedia.multitallented.spout.proxis.models.targets.Target;
import com.redcastlemedia.multitallented.spout.proxis.models.targets.TargetSource.TargetScheme;
import com.redcastlemedia.multitallented.spout.proxis.models.users.User;
import com.redcastlemedia.multitallented.spout.proxis.models.users.states.BuiltInUserStates;
import com.redcastlemedia.multitallented.spout.proxis.models.users.states.UserState;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import org.spout.api.Spout;
import org.spout.api.entity.Entity;
import org.spout.api.entity.Player;
import org.spout.api.geo.cuboid.Block;
import org.spout.api.geo.discrete.Point;

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
    
    public void useSkill(User user) {
        Player caster = Spout.getEngine().getPlayer(user.NAME, true);
        if (caster == null) {
            return;
        }
        HashMap<String, HashSet<Object>> targetMap = new HashMap<>();
        HashSet<Target> targetsClone = (HashSet<Target>) targets.clone();
        HashMap<Target, Point> processedTargets = new HashMap<>();
        do {
            HashSet<Target> removeLater = new HashSet<>();
            for (Target tar : targetsClone) {
                if (!tar.getNode().containsKey("origin")) {
                    removeLater.add(tar);
                }
                String originName = "self";
                try {
                    originName = (String) tar.getNode().get("origin");
                } catch (Exception e) {
                    removeLater.add(tar);
                }
                if (!originName.equals("self")) {
                    for (Target targ : processedTargets.keySet()) {
                        if (!targ.NAME.equals(originName)) {
                            continue;
                        }
                        TargetScheme ts = tar.getTargets(plugin, user, processedTargets.get(targ));
                        targetMap.put(originName, ts.targets);
                        removeLater.add(tar);
                        processedTargets.put(tar, ts.originPoint);
                        break;
                    }
                } else {
                    TargetScheme ts = tar.getTargets(plugin, user, caster.getTransform().getPosition());
                    targetMap.put(originName, ts.targets);
                    removeLater.add(tar);
                    processedTargets.put(tar, ts.originPoint);
                }
            }
            for (Target tar : removeLater) {
                targetsClone.remove(tar);
            }
            if (removeLater.isEmpty() && !targetsClone.isEmpty()) {
                return;
            }
        } while (!targetsClone.isEmpty());
        HashSet<Object> tempSet = new HashSet<>();
        tempSet.add(user);
        targetMap.put("self", tempSet);
        CastSkill cs = new CastSkill(caster, this, targetMap);
        cs.checkConditions();
    }

    public class CastSkill {
        private final HashMap<String, HashSet<Object>> targetMap;
        private final Skill skill;
        public final HashMap<Integer, HashSet<SkillResult>> pendingConditions = new HashMap<>();
        public final HashMap<Integer, Integer> pendingConditionsSize = new HashMap<>();
        public boolean preCast = true;
        private Player caster;
        public final ArrayList<HashMap<Condition, String>> instancedPreCastConditions;
        public final ArrayList<HashMap<Condition, String>> instancedPostCastConditions;
        public final ArrayList<HashMap<Effect, String>> instancedPreCastEffects;
        public final ArrayList<HashMap<Effect, String>> instancedPostCastEffects;
        
        public CastSkill(Player caster, Skill skill, HashMap<String, HashSet<Object>> targetMap) {
            this.targetMap = targetMap;
            this.skill = skill;
            this.caster = caster;
            this.instancedPreCastConditions = (ArrayList<HashMap<Condition, String>>) preCastConditions.clone();
            this.instancedPostCastConditions = (ArrayList<HashMap<Condition, String>>) postCastConditions.clone();
            this.instancedPreCastEffects = (ArrayList<HashMap<Effect, String>>) preCastEffects.clone();
            this.instancedPostCastEffects = (ArrayList<HashMap<Effect, String>>) postCastEffects.clone();
        }
        
        public void checkConditions() {
            if (preCast) {
                SkillPreCastEvent sPCEvent = Spout.getEventManager().callEvent(new SkillPreCastEvent(caster.getName(), this));
                if (sPCEvent.isCancelled()) {
                    return;
                }
                for (int i=0; i<instancedPreCastConditions.size(); i++) {
                    HashMap<Condition, String> conditions = instancedPreCastConditions.get(i);
                    pendingConditionsSize.put(i, conditions.size());
                    for (Condition con : conditions.keySet()) {
                        con.testCondition(this, targetMap.get(conditions.get(con)));
                    }
                }
            } else {
                SkillCastEvent sCEvent = Spout.getEventManager().callEvent(new SkillCastEvent(caster.getName(), this));
                if (sCEvent.isCancelled()) {
                    return;
                }
                for (int i=0; i<instancedPostCastConditions.size(); i++) {
                    HashMap<Condition, String> conditions = instancedPostCastConditions.get(i);
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
                for (Effect effect : instancedPreCastEffects.get(index).keySet()) {
                    effect.execute(this, targetMap.get(instancedPreCastEffects.get(index).get(effect)));
                }
            } else {
                for (Effect effect : instancedPostCastEffects.get(index).keySet()) {
                    effect.execute(this, targetMap.get(instancedPostCastEffects.get(index).get(effect)));
                }
            }
        }
        
        public Skill getSkill() {
            return skill;
        }
        public Player getCaster() {
            return caster;
        }
        public HashMap<String, HashSet<Object>> getTargetMap() {
            return targetMap;
        }
    }
}