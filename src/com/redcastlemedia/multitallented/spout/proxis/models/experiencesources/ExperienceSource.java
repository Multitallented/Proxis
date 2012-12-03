package com.redcastlemedia.multitallented.spout.proxis.models.experiencesources;

import com.redcastlemedia.multitallented.spout.proxis.models.SourceType;
import com.redcastlemedia.multitallented.spout.proxis.models.skills.SkillSource;

/**
 *
 * @author Multitallented
 */
public class ExperienceSource extends SkillSource {
    public final String NAME;
    public ExperienceSource(String name) {
        super(SourceType.EXPERIENCE);
        NAME = name;
    }
    //TODO write helper functions here
    
    //TODO build these into the listeners?
    /*public enum BuiltInExperienceSources {
        KILL_PLAYER,
        DAMAGE,
        DAMAGE_INCOMING,
        DAMAGE_OUTGOING,
        DAMAGE_PLAYER,
        DAMAGE_PLAYER_INCOMING,
        DAMAGE_PLAYER_OUTGOING,
        DAMAGE_MOB,
        DAMAGE_MOB_OUTGOING,
        DAMAGE_MOB_INCOMING,
        
    }*/
}
