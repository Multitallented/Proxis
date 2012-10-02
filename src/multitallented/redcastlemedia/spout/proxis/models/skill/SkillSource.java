package multitallented.redcastlemedia.spout.proxis.models.skill;

import java.util.ArrayList;
import multitallented.redcastlemedia.spout.proxis.Proxis;
import multitallented.redcastlemedia.spout.proxis.models.SourceType;
import multitallented.redcastlemedia.spout.proxis.models.effects.EffectType;

/**
 *
 * @author Multitallented
 */
public abstract class SkillSource {
    protected SourceType sourceType;
    protected String sourceName;
    protected ArrayList<EffectType> types;
    protected Proxis plugin;
    
    public void init(Proxis plugin, String sourceName, SourceType sourceType) {
        this.plugin = plugin;
        SkillConfiguration skillConfig = getConfiguration();
        skillConfig.save();
        setTypes();
        setSource(sourceName, sourceType);
    }
    
    protected abstract void setTypes();
    
    protected void setSource(String sourceName, SourceType sourceType) {
        this.sourceName = sourceName;
        this.sourceType = sourceType;
    }
    
    public abstract SkillConfiguration getConfiguration();
    
    public Proxis getPlugin() {
        return plugin;
    }
}
