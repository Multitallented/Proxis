package multitallented.redcastlemedia.spout.proxis.orders;

import java.io.File;
import multitallented.redcastlemedia.spout.proxis.Proxis;
import multitallented.redcastlemedia.spout.proxis.commands.ProxisCommands;
import multitallented.redcastlemedia.spout.proxis.listeners.ProxisListener;
import multitallented.redcastlemedia.spout.proxis.managers.SkillConfigManager;
import multitallented.redcastlemedia.spout.proxis.managers.SkillJarManager;
import multitallented.redcastlemedia.spout.proxis.managers.SkillManager;
import multitallented.redcastlemedia.spout.proxis.models.skill.SkillConfiguration;
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
        SkillManager sm = new SkillManager(proxis);
        sm.loadAllSkills();
        
        //init managers
        
        proxis.setManagers(sm);
    }
}
