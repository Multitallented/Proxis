package multitallented.redcastlemedia.spout.proxis.models.effects;

import java.util.ArrayList;
import multitallented.redcastlemedia.spout.proxis.models.conditions.ConditionConfiguration;
import multitallented.redcastlemedia.spout.proxis.models.conditions.ConditionSource;
import org.spout.api.util.config.ConfigurationHolder;
import org.spout.api.util.config.ConfigurationHolderConfiguration;
import org.spout.api.util.config.yaml.YamlConfiguration;

/**
 *
 * @author Multitallented
 */
public abstract class EffectConfiguration extends ConfigurationHolderConfiguration {
    public final ConfigurationHolder PATTERN;
    public final ConfigurationHolder CONDITIONS;
    
    public EffectConfiguration(ArrayList<ConditionSource> conditions) {
        super(new YamlConfiguration());
        PATTERN = new ConfigurationHolder(setPattern(), "pattern");
        ArrayList<ConditionConfiguration> conditionConfigs = new ArrayList<>();
        for (ConditionSource currentCond : conditions) {
            conditionConfigs.add(currentCond.getConditionConfig());
        }
        CONDITIONS = new ConfigurationHolder(conditionConfigs, "conditions");
    }
    
    public abstract String setPattern();
}
