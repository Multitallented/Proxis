package main.java.multitallented.redcastlemedia.spout.proxis;

import main.java.multitallented.redcastlemedia.spout.proxis.models.Config;
import org.spout.api.Engine;
import org.spout.api.exception.ConfigurationException;
import org.spout.api.plugin.CommonPlugin;

/**
 *
 * @author multitallented
 */
public class Proxis extends CommonPlugin {
    public Config config;
    private Engine engine;
    
    @Override
    public void onLoad() {
        config = new Config(getDataFolder());
        engine = getEngine();
    }
    
    @Override
    public void onEnable() {
        try {
            config.load();
        } catch (ConfigurationException ce) {
            
        }
    }

    @Override
    public void onDisable() {
        
    }
    
}
