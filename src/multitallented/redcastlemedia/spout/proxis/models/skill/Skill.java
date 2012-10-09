package multitallented.redcastlemedia.spout.proxis.models.skill;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import multitallented.redcastlemedia.spout.proxis.Proxis;
import multitallented.redcastlemedia.spout.proxis.models.conditions.ConditionSource;
import multitallented.redcastlemedia.spout.proxis.models.effects.EffectSource;
import multitallented.redcastlemedia.spout.proxis.models.targets.TargetSource;
import multitallented.redcastlemedia.spout.proxis.models.users.User;
import org.spout.api.entity.Player;
import org.spout.api.util.config.yaml.YamlConfiguration;

/**
 *
 * @author Multitallented
 */
public class Skill extends YamlConfiguration {
        public final String NAME;
        public final HashSet<SkillComponentType> TYPES = new HashSet<>();
        public final SkillConfiguration SKILL_CONFIG;
        
        private final Proxis plugin;
        private final HashSet<ConditionSource> conditions;
        private final TargetSource target;
        private final HashSet<EffectSource> effects;

        public Skill(Proxis plugin, String name, SkillConfiguration skillConfig, TargetSource target, HashSet<EffectSource> effects, HashSet<ConditionSource> conditions) {
                super(new File(new File(plugin.getDataFolder(), "skills"), name + ".yml"));
                NAME = name;
                SKILL_CONFIG = skillConfig;
                this.plugin = plugin;
                this.target = target;
                this.conditions = conditions;
                this.effects = effects;
        }
        
        public HashSet<EffectSource> getEffects() {
                return effects;
        }
        
        public void execute(User user) {
            ArrayList<ArrayList<Object>> targets = target.getTarget(this, user);
            ArrayList<Class> targetTypes = target.getTargetTypes();
            
            if (targets.isEmpty()) {
                //TODO tell them it's invalid target
                return;
            }
            
            Player self = getPlugin().getEngine().getPlayer(user.getName());
            boolean failed = false;
            boolean invalid = false;
            conditionCheck: for (ConditionSource con : conditions) {
                switch (con.testCondition(self, Player.class) {
                    case FAIL:
                        failed = true;
                        break conditionCheck;
                    case INVALID:
                        invalid = true;
                        break;
                }
            }
            if (failed) {
                for (ConditionSource con : conditions) {
                    con.useCondition(user);
                }
                //TODO tell them it failed
                return;
            } else if (invalid) {
                //TODO tell them it's invalid
                return;
            }
            
            for (EffectSource effect : effects) {
                failed = false;
                invalid = false;
                outer: for (ConditionSource con : effect.conditions) {
                    for (Object tar : targets.get(target.getIndex(effect.TARGET.getString()))) {
                        switch (con.testCondition(tar, targetTypes.get(i)) {
                            case INVALID:
                                invalid = true;
                                break;
                            case FAIL:
                                failed = true;
                                break outer;
                        }
                    }
                }
                
                if (failed) {
                    for (ConditionSource con : effect.conditions) {
                        con.useCondition(user);
                    }
                    continue;
                } else if (invalid) {
                    continue;
                }
                for (Object tar : targets.get(target.getIndex(effect.TARGET.getString()))) {
                    //figure out what Class the target is and execute
                }
            }
        }
}
