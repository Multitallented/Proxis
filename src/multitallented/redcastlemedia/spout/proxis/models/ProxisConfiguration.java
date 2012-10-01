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
    public static final ConfigurationHolder SKILL_CATEGORIES = new ConfigurationHolder(new ArrayList<String>(), "skill-categories");

    
    public ProxisConfiguration(File dataFolder) {
        super(new YamlConfiguration(new File(dataFolder, "config.yml")));
        buildDefaults();
    }
    
    private void buildDefaults() {
        ArrayList<String> defaultList = new ArrayList<>();
        defaultList.add("Earth");
        defaultList.add("Fire");
        defaultList.add("Water");
        defaultList.add("Air");
        SKILL_CATEGORIES.setDefaultValue(defaultList);
    }
    
    public static ArrayList<String> getLocalizedHelp() {
        ArrayList<String> tempList = new ArrayList<>();
        
        //TODO write this
        switch (LOCALE.getString()) {
            case "en":
                tempList.add("");
                break;
        }
        return tempList;
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
    public void save() throws ConfigurationException {
        try {
            super.save();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }
}
