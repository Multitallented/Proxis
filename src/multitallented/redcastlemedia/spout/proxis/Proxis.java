package multitallented.redcastlemedia.spout.proxis;

import multitallented.redcastlemedia.spout.proxis.listeners.ProxisListener;
import java.util.logging.Level;
import multitallented.redcastlemedia.spout.proxis.commands.ProxisCommands;
import multitallented.redcastlemedia.spout.proxis.models.ProxisConfiguration;
import org.spout.api.Engine;
import org.spout.api.UnsafeMethod;
import org.spout.api.command.CommandRegistrationsFactory;
import org.spout.api.command.annotated.AnnotatedCommandRegistrationFactory;
import org.spout.api.command.annotated.SimpleAnnotatedCommandExecutorFactory;
import org.spout.api.command.annotated.SimpleInjector;
import org.spout.api.plugin.CommonPlugin;

/**
 *
 * @author multitallented
 */
public class Proxis extends CommonPlugin {
    public ProxisConfiguration config;
    
    @Override
    public void onLoad() {
        config = new ProxisConfiguration(getDataFolder());
    }
    
    @Override
    @UnsafeMethod
    public void onEnable() {
        CommandRegistrationsFactory<Class<?>> commandRegFactory = new AnnotatedCommandRegistrationFactory(new SimpleInjector(this), new SimpleAnnotatedCommandExecutorFactory());
        getEngine().getRootCommand().addSubCommands(this, ProxisCommands.class, commandRegFactory);
        getEngine().getEventManager().registerEvents(new ProxisListener(this), this);
        config.load();
    }

    @Override
    @UnsafeMethod
    public void onDisable() {
        
    }
    
    public void log(String text) {
        log(text);
    }
    
    public void log(Level level, String text) {
        getLogger().log(level, text);
    }
    
}
