package multitallented.redcastlemedia.spout.proxis.models.targets;

import java.util.HashSet;
import multitallented.redcastlemedia.spout.proxis.Proxis;
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
    
    public abstract HashSet<Object> getTargets(Proxis plugin, HashSet<String> types, ConfigurationNode node);
}