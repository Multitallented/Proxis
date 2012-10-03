package multitallented.redcastlemedia.spout.proxis.models.skill;

import multitallented.redcastlemedia.spout.proxis.Proxis;
import multitallented.redcastlemedia.spout.proxis.models.SourceType;
import org.spout.api.entity.Player;
import org.spout.api.util.config.ConfigurationHolderConfiguration;
import org.spout.api.util.config.yaml.YamlConfiguration;
import org.spout.vanilla.VanillaPlugin;
import org.spout.vanilla.component.living.VanillaEntity;

/**
 *
 * @author Multitallented
 */
public abstract class SkillSource extends ConfigurationHolderConfiguration {
    private SourceType sourceType;
    private String sourceName;
    private Proxis plugin;
    
    public SkillSource() {
        super(new YamlConfiguration());
    }
    
    public void init(Proxis plugin, String sourceName, SourceType sourceType) {
        this.plugin = plugin;
        setSource(sourceName, sourceType);
    }
    
    protected void setSource(String sourceName, SourceType sourceType) {
        this.sourceName = sourceName;
        this.sourceType = sourceType;
    }
    
    public Proxis getPlugin() {
        return plugin;
    }
    public String getSourceName() {
        return sourceName;
    }
    public SourceType getSourceType() {
        return sourceType;
    }
    
    public boolean damageCheck(Player damager, VanillaEntity damagee) {
        plugin.getEngine().getEventManager().callEvent(null);
        return true;
    }
    public boolean damageCheck(Player damager, Player damagee) {
        return true;
    }
}
