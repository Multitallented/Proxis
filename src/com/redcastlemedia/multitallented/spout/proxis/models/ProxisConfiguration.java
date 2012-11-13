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
    //<editor-fold defaultstate="collapsed" desc="database">
    public static final ConfigurationHolder USE_DB = new ConfigurationHolder(false, "database.use-database");
    public static final ConfigurationHolder DATABASE_ADDRESS = new ConfigurationHolder("192.168.1.10", "database.database-address");
    public static final ConfigurationHolder DATABASE_PORT = new ConfigurationHolder("3237", "database.database-port");
    public static final ConfigurationHolder DATABASE_USERNAME = new ConfigurationHolder("root", "database.database-username");
    public static final ConfigurationHolder DATABASE_PASSWORD = new ConfigurationHolder("pass", "database.database-password");
    public static final ConfigurationHolder DATABASE_NAME = new ConfigurationHolder("proxis", "database.database-name");
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="kill">
    public static final ConfigurationHolder DEATH_GRACE_PERIOD_SECONDS = new ConfigurationHolder(120, "kill.death-grace-period-seconds");
    public static final ConfigurationHolder MONEY_PER_POINT = new ConfigurationHolder(10.0, "kill.money-per-point");
    public static final ConfigurationHolder POINTS_PER_KILL = new ConfigurationHolder(10, "kill.points-per-kill");
    public static final ConfigurationHolder POINTS_PER_DEATH = new ConfigurationHolder(-5, "kill.points-per-death");
    public static final ConfigurationHolder POINTS_PER_KILLSTREAK = new ConfigurationHolder(1, "kill.points-per-killstreak");
    public static final ConfigurationHolder POINTS_PER_KILLJOY = new ConfigurationHolder(1, "kill.points-per-killjoy");
    public static final ConfigurationHolder POINTS_FOR_QUARTER_HP = new ConfigurationHolder(4, "kill.points-for-quarter-hp");
    public static final ConfigurationHolder POINTS_FOR_HALF_HP = new ConfigurationHolder(2, "kill.points-for-half-hp");
    //</editor-fold>
    

    
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
