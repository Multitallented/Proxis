package multitallented.redcastlemedia.spout.proxis.models;

import java.io.File;
import java.util.ArrayList;
import org.spout.api.exception.ConfigurationException;
import org.spout.api.util.config.ConfigurationHolder;
import org.spout.api.util.config.ConfigurationHolderConfiguration;
import org.spout.api.util.config.yaml.YamlConfiguration;

/**
 *
 * @author Multitallented
 */
public class ProxisConfiguration extends ConfigurationHolderConfiguration {
    public static final ConfigurationHolder LOCALE = new ConfigurationHolder("en", "locale");
    public static final ConfigurationHolder USE_DB = new ConfigurationHolder(false, "use-database");
    public static final ConfigurationHolder DATABASE_ADDRESS = new ConfigurationHolder("192.168.1.10", "database-address");
    public static final ConfigurationHolder DATABASE_PORT = new ConfigurationHolder("3237", "database-port");
    public static final ConfigurationHolder DATABASE_USERNAME = new ConfigurationHolder("root", "database-username");
    public static final ConfigurationHolder DATABASE_PASSWORD = new ConfigurationHolder("pass", "database-password");
    public static final ConfigurationHolder DATABASE_NAME = new ConfigurationHolder("proxis", "database-name");

    
    public ProxisConfiguration(File dataFolder) {
        super(new YamlConfiguration(new File(dataFolder, "config.yml")));
    }
    
    @Override
    public void load() {
        try {
            super.load();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void save() {
        try {
            super.save();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }
}
