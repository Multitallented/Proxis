package multitallented.redcastlemedia.spout.proxis.models.targets;

import org.spout.api.util.config.ConfigurationHolderConfiguration;
import org.spout.api.util.config.yaml.YamlConfiguration;

/**
 *
 * @author Multitallented
 */
public class TargetConfiguration extends ConfigurationHolderConfiguration {
    public TargetConfiguration() {
        super(new YamlConfiguration());
    }
}
