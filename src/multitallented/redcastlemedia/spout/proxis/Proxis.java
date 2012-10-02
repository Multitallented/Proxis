package multitallented.redcastlemedia.spout.proxis;

import java.util.logging.Level;
import multitallented.redcastlemedia.spout.proxis.managers.SkillJarManager;
import multitallented.redcastlemedia.spout.proxis.models.ProxisConfiguration;
import multitallented.redcastlemedia.spout.proxis.orders.DisableOrder;
import multitallented.redcastlemedia.spout.proxis.orders.EnableOrder;
import org.spout.api.UnsafeMethod;
import org.spout.api.plugin.CommonPlugin;

/**
 *
 * @author multitallented
 */
public class Proxis extends CommonPlugin {
    public ProxisConfiguration config;
    private SkillJarManager sjm;
    
    @Override
    public void onLoad() {
        config = new ProxisConfiguration(getDataFolder());
    }
    
    @Override
    @UnsafeMethod
    public void onEnable() {
        new EnableOrder(this);
    }

    @Override
    @UnsafeMethod
    public void onDisable() {
        new DisableOrder(this);
    }
    
    public void log(String text) {
        log(text);
    }
    
    public void log(Level level, String text) {
        getLogger().log(level, text);
    }
    
    public void setManagers(SkillJarManager sjm) {
        this.sjm = sjm;
    }
    public SkillJarManager getSkillJarManager() {
        return sjm;
    }
}
