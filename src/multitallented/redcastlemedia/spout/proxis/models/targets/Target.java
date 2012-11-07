package multitallented.redcastlemedia.spout.proxis.models.targets;

import java.util.HashMap;
import java.util.List;
import org.spout.api.util.config.ConfigurationNode;

/**
 *
 * @author Multitallented
 */
public class Target {
    private final TargetSource ts;
    private final ConfigurationNode config;
    private final HashMap<String, List<String>> targetTypes;
    public Target(TargetSource ts, ConfigurationNode config) {
        this.ts = ts;
        this.config = config;
        this.targetTypes = new HashMap<>();
        for (String s : config.getChild("patterns").getKeys(false)) {
            targetTypes.put(s, config.getNode("patterns." + s).getStringList());
        }
    }
    
    public HashMap<String, List<String>> getTargets() {
        
        return ts.getTargets(targetTypes, config);
    }
}
