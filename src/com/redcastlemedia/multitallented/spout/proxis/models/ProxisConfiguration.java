package com.redcastlemedia.multitallented.spout.proxis.models;

import java.io.File;
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
    public static final ConfigurationHolder DEATH_GRACE_PERIOD_SECONDS = new ConfigurationHolder(120, "death-grace-period-seconds");
    public static final ConfigurationHolder POINTS_PER_KILLSTREAK = new ConfigurationHolder(1, "points-per-killstreak");
    public static final ConfigurationHolder MONEY_PER_POINT = new ConfigurationHolder(10.0, "money-per-point");
    

    
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
