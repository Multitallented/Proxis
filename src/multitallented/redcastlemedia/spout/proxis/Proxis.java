package multitallented.redcastlemedia.spout.proxis;

import java.util.logging.Level;
import multitallented.redcastlemedia.spout.proxis.controllers.SkillJarManager;
import multitallented.redcastlemedia.spout.proxis.controllers.SkillManager;
import multitallented.redcastlemedia.spout.proxis.controllers.TypeManager;
import multitallented.redcastlemedia.spout.proxis.controllers.orders.DisableOrder;
import multitallented.redcastlemedia.spout.proxis.controllers.orders.EnableOrder;
import multitallented.redcastlemedia.spout.proxis.models.ProxisConfiguration;
import org.spout.api.UnsafeMethod;
import org.spout.api.plugin.CommonPlugin;

/**
 *
 * @author multitallented
 */
public class Proxis extends CommonPlugin {
    public ProxisConfiguration config;
    private SkillJarManager sjm;
    public static String NAME = "[ProxisRPG]";
    private SkillManager sm;
    private TypeManager tm;
    
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
    
    public void setManagers(SkillJarManager sjm, SkillManager sm, TypeManager tm) {
        this.sjm = sjm;
        this.sm = sm;
        this.tm = tm;
    }
    public SkillJarManager getSkillJarManager() {
        return sjm;
    }
    public SkillManager getSkillManager() {
        return sm;
    }
    public TypeManager getTypeManager() {
        return tm;
    }
}
