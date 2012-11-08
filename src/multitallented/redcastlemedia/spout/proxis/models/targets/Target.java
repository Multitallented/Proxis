package multitallented.redcastlemedia.spout.proxis.models.targets;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import multitallented.redcastlemedia.spout.proxis.Proxis;
import multitallented.redcastlemedia.spout.proxis.models.users.User;
import org.spout.api.util.config.ConfigurationNode;

/**
 *
 * @author Multitallented
 */
public class Target {
    private final TargetSource ts;
    private final ConfigurationNode config;
    private final HashSet<String> targetTypes = new HashSet<>();
    public final String NAME;
    
    public Target(TargetSource ts, ConfigurationNode config) {
        this.ts = ts;
        this.config = config;
        for (String s : config.getChild("pattern").getKeys(false)) {
            targetTypes.add(s);
        }
        NAME = config.getNode("name").getString();
    }
    
    public HashSet<Object> getTargets(Proxis plugin, User user) {
        HashSet<Object> tempSet = ts.getTargets(plugin, targetTypes, config);
        return tempSet;
    }
}
