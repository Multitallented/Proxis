package com.redcastlemedia.multitallented.spout.proxis.controllers.orders;

import com.redcastlemedia.multitallented.spout.proxis.Proxis;
import com.redcastlemedia.multitallented.spout.proxis.controllers.ClassManager;
import com.redcastlemedia.multitallented.spout.proxis.controllers.SkillJarManager;
import com.redcastlemedia.multitallented.spout.proxis.controllers.SkillManager;
import com.redcastlemedia.multitallented.spout.proxis.controllers.TypeManager;
import com.redcastlemedia.multitallented.spout.proxis.controllers.UserManager;
import com.redcastlemedia.multitallented.spout.proxis.views.ProxisCommands;
import com.redcastlemedia.multitallented.spout.proxis.views.ProxisListener;
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
        //commands
        CommandRegistrationsFactory<Class<?>> commandRegFactory = new AnnotatedCommandRegistrationFactory(new SimpleInjector(proxis), new SimpleAnnotatedCommandExecutorFactory());
        proxis.getEngine().getRootCommand().addSubCommands(proxis, ProxisCommands.class, commandRegFactory);
        proxis.getEngine().getEventManager().registerEvents(new ProxisListener(proxis), proxis);
        
        //load skill jars
        SkillJarManager sjm = new SkillJarManager(proxis);
        sjm.loadSkillSources();
        
        //init other managers
        SkillManager sm = new SkillManager(proxis);
        TypeManager tm = new TypeManager(proxis);
        UserManager um = new UserManager(proxis);
        ClassManager cm = new ClassManager(proxis);
        
        proxis.setManagers(sjm, sm, tm, um, cm);
        
        //register listeners
        proxis.getEngine().getEventManager().registerEvents(new ProxisListener(proxis), proxis);
    }
}
