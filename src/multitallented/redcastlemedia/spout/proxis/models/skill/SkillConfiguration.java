package multitallented.redcastlemedia.spout.proxis.models.skill;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import multitallented.redcastlemedia.spout.proxis.models.effects.EffectType;
import org.spout.api.exception.ConfigurationException;
import org.spout.api.util.config.ConfigurationHolder;
import org.spout.api.util.config.ConfigurationHolderConfiguration;
import org.spout.api.util.config.yaml.YamlConfiguration;

/**
 *
 * @author Multitallented
 */
public class SkillConfiguration extends ConfigurationHolderConfiguration {
    public final ConfigurationHolder NAME;
    private final ConfigurationHolder TYPES;
    public final ConfigurationHolder ATTRIBUTES;
    
    public SkillConfiguration(File skillFolder, String fileName, ArrayList<String> types, HashMap<String, Object> attributes) {
        super(new YamlConfiguration(new File(skillFolder, fileName)));
        NAME = new ConfigurationHolder(fileName.replace(".yml", ""), "name");
        TYPES = new ConfigurationHolder(types, "types");
        ATTRIBUTES = new ConfigurationHolder(attributes, "attributes");
    }
    
    public ArrayList<EffectType> getTypes() {
        ArrayList<EffectType> tempList = new ArrayList<>();
        for (String s : TYPES.getStringList()) {
            try {
                tempList.add(EffectType.valueOf(s));
            } catch (IllegalArgumentException iae) {
                
            }
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
    public void save() {
        try {
            super.save();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }
}
