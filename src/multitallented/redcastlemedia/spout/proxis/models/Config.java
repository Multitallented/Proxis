package multitallented.redcastlemedia.spout.proxis.models;

import java.io.File;
import org.spout.api.exception.ConfigurationException;
import org.spout.api.util.config.ConfigurationHolder;
import org.spout.api.util.config.ConfigurationHolderConfiguration;
import org.spout.api.util.config.yaml.YamlConfiguration;

/**
 *
 * @author Multitallented
 */
public class Config extends ConfigurationHolderConfiguration {
    public static final ConfigurationHolder MOTD = new ConfigurationHolder("A Spout Server", "general", "motd");

    
    public Config(File dataFolder) {
        super(new YamlConfiguration(new File(dataFolder, "config.yml")));
        super.setWritesDefaults(true);
    }
    
    @Override
    public void load() throws ConfigurationException {
        
    }
    
    @Override
    public void save() throws ConfigurationException {
        
    }
}
