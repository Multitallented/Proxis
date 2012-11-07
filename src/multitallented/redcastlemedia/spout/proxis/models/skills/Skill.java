package multitallented.redcastlemedia.spout.proxis.models.skills;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import multitallented.redcastlemedia.spout.proxis.Proxis;
import multitallented.redcastlemedia.spout.proxis.models.conditions.ConditionSource;
import multitallented.redcastlemedia.spout.proxis.models.effects.EffectConfiguration;
import multitallented.redcastlemedia.spout.proxis.models.effects.EffectSource;
import multitallented.redcastlemedia.spout.proxis.models.targets.TargetSource;
import multitallented.redcastlemedia.spout.proxis.models.users.User;
import org.spout.api.entity.Player;
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
    private final TargetSource target;
    private final HashSet<EffectSource> effects;

    public Skill(Proxis plugin, String filename, TargetSource target, HashSet<EffectSource> effects, HashSet<ConditionSource> conditions) {
        super(new File(new File(plugin.getDataFolder(), "skills"), filename + ".yml"));
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
        for (EffectSource effect : effects) {
            boolean effectfailed = false;
            boolean effectinvalid = false;
            outer: for (ConditionSource con : effect.conditions) {
                for (Object tar : targets.get(target(effect.TARGET.getString()))) {
                    switch (con.testCondition(tar)) {
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

            for (Object tar : targets.get(target.getIndex(effect.TARGET.getString()))) {
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
}