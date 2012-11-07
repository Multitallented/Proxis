package multitallented.redcastlemedia.spout.proxis.models.skills;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import multitallented.redcastlemedia.spout.proxis.Proxis;
import multitallented.redcastlemedia.spout.proxis.models.conditions.ConditionSource;
import multitallented.redcastlemedia.spout.proxis.models.effects.EffectSource;
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
    private final HashSet<ConditionSource> conditions;
    private final Target target;
    private final HashMap<EffectSource, String> effects;

    public Skill(Proxis plugin, String filename, Target target, HashMap<EffectSource, String> effects, HashSet<ConditionSource> conditions) {
        super(new File(new File(plugin.getDataFolder(), "skills"), filename + ".yml"));
        this.plugin = plugin;
        this.target = target;
        this.conditions = conditions;
        this.effects = effects;
    }

    public HashMap<EffectSource, String> getEffects() {
        return effects;
    }

    public void execute(User user) {
        HashMap<String, List<String>> targets = target.getTargets();

        if (targets.isEmpty()) {
            //TODO tell them it's invalid target
            return;
        }

        //Player self = plugin.getEngine().getPlayer(user.NAME, true);
        boolean failed = false;
        boolean invalid = false;
        conditionCheck: for (ConditionSource con : conditions) {
            switch (con.testCondition(user)) {
                case FAILED:
                    failed = true;
                    break conditionCheck;
                case REFUND:
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

        invalid = true;
        failed = true;
        for (EffectSource effect : effects.keySet()) {
            boolean effectfailed = false;
            boolean effectinvalid = false;
            outer: for (ConditionSource con : effect.conditions) {
                for (Object tar : targets.get(effects.get(effect))) {
                    switch (testConditionOnUnknownTarget(con, tar)) {
                        case REFUND:
                            effectinvalid = true;
                            break;
                        case FAILED:
                            effectfailed = true;
                            break outer;
                    }
                }
            }

            if (effectinvalid) {
                continue;
            }
            invalid = false;
            if (effectfailed) {
                for (ConditionSource con : effect.conditions) {
                    con.useCondition(user);
                }
                continue;
            }
            failed = false;

            for (Object tar : targets.get(targets.get(effects.get()))) {
                effect.execute(user, tar);
                //cast target to user, block, or vanillaentity and execute for each target
            }
        }
        if(invalid) {
            //TODO tell them it's invalid
            return;
        } else if(failed) {
            for (ConditionSource con : conditions) {
                con.useCondition(user);
            }
            //TODO tell them it failed
            return;
        }
    }
    
    private SkillResult testConditionOnUnknownTarget(Condition con, Object target) {
        
    }
}