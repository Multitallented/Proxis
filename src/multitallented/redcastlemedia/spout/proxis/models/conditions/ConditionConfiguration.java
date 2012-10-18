package multitallented.redcastlemedia.spout.proxis.models.conditions;

import org.spout.api.util.config.ConfigurationHolderConfiguration;
import org.spout.api.util.config.yaml.YamlConfiguration;

/**
 *
 * @author Multitallented
 */
public abstract class ConditionConfiguration extends ConfigurationHolderConfiguration {
    
    public ConditionConfiguration() {
        super(new YamlConfiguration());
    }
}
