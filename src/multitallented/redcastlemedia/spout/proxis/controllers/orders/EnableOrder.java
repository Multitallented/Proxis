package multitallented.redcastlemedia.spout.proxis.controllers.orders;

import multitallented.redcastlemedia.spout.proxis.Proxis;
import multitallented.redcastlemedia.spout.proxis.controllers.SkillJarManager;
import multitallented.redcastlemedia.spout.proxis.controllers.TypeManager;
import multitallented.redcastlemedia.spout.proxis.views.commands.ProxisCommands;
import multitallented.redcastlemedia.spout.proxis.views.listeners.ProxisListener;
import org.spout.api.command.CommandRegistrationsFactory;
import org.spout.api.command.annotated.AnnotatedCommandRegistrationFactory;
import org.spout.api.command.annotated.SimpleAnnotatedCommandExecutorFactory;
import org.spout.api.command.annotated.SimpleInjector;

/**
 * Handles all controller functions related to the enabling this plugin.
 * @author Multitallented
 */
public class EnableOrder {
    public EnableOrder(Proxis proxis) {
        //dependencies
        if (proxis.getEngine().getPluginManager().getPlugin("Vanilla") == null) {
            proxis.getEngine().getPluginManager().disablePlugin(proxis);
            return;
        }
        
        //commands
        CommandRegistrationsFactory<Class<?>> commandRegFactory = new AnnotatedCommandRegistrationFactory(new SimpleInjector(proxis), new SimpleAnnotatedCommandExecutorFactory());
        proxis.getEngine().getRootCommand().addSubCommands(proxis, ProxisCommands.class, commandRegFactory);
        proxis.getEngine().getEventManager().registerEvents(new ProxisListener(proxis), proxis);
        
        //configs
        proxis.config.load();
        proxis.config.save();
        
        //load skill jars
        SkillJarManager sjm = new SkillJarManager(proxis);
        sjm.loadSkillSources();
        
        TypeManager tm = new TypeManager(proxis);
        
        //init managers
        
        proxis.setManagers(sjm);
    }
}
