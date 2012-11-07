package multitallented.redcastlemedia.spout.proxis.models.targets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import multitallented.redcastlemedia.spout.proxis.models.SourceType;
import multitallented.redcastlemedia.spout.proxis.models.skills.SkillSource;
import org.spout.api.util.config.ConfigurationNode;

/**
 *
 * @author Multitallented
 */
public abstract class TargetSource extends SkillSource {
    public TargetSource() {
        super(SourceType.TARGET);
    }
    
    public abstract HashMap<String, List<String>> getTargets(HashMap<String, List<String>> types, ConfigurationNode node);
}