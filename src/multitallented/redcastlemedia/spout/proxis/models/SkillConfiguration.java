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
public class SkillConfiguration extends ConfigurationHolderConfiguration {
    public static final ConfigurationHolder SKILLS = new ConfigurationHolder(new ArrayList<String>(), "skills"); //TODO fix this

    
    public SkillConfiguration(File dataFolder) {
        super(new YamlConfiguration(new File(dataFolder, "config.yml")));
        buildDefaults();
    }
    
    private void buildDefaults() {
        //TODO fix this
        ArrayList<String> defaultList = new ArrayList<>();
        defaultList.add("Earth");
        defaultList.add("Fire");
        defaultList.add("Water");
        defaultList.add("Air");
        SKILLS.setDefaultValue(defaultList);
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
