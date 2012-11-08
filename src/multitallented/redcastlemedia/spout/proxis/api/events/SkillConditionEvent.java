package multitallented.redcastlemedia.spout.proxis.api.events;

import multitallented.redcastlemedia.spout.proxis.models.skills.Skill.CastSkill;
import multitallented.redcastlemedia.spout.proxis.models.skills.SkillResult;
import org.spout.api.event.Event;
import org.spout.api.event.HandlerList;

/**
 * This is thrown whenever a condition is being tested
 * @author Multitallented
 */
public class SkillConditionEvent extends Event {
    private SkillResult result = SkillResult.NORMAL;
    private static final HandlerList hList = new HandlerList();
    public final CastSkill CAST_SKILL;
    public final int INDEX;
    
    public SkillConditionEvent(CastSkill cs, int index) {
        this.CAST_SKILL = cs;
        this.INDEX = index;
    }
    
    public void setResult(SkillResult result) {
        this.result = result;
    }
    
    public SkillResult getResult() {
        return result;
    }

    public static HandlerList getHandlerList() {
        return hList;
    }
    
    @Override
    public HandlerList getHandlers() {
        return hList;
    }
}
