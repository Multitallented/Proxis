package com.redcastlemedia.multitallented.spout.proxis.models.users.states;

import com.redcastlemedia.multitallented.spout.proxis.Proxis;
import com.redcastlemedia.multitallented.spout.proxis.models.effects.EffectSource;
import com.redcastlemedia.multitallented.spout.proxis.models.skills.Skill;
import com.redcastlemedia.multitallented.spout.proxis.models.users.User;
import java.util.HashMap;
import java.util.HashSet;
import org.spout.api.Spout;
import org.spout.api.entity.Entity;
import org.spout.api.geo.cuboid.Block;
import org.spout.api.scheduler.TaskPriority;

/**
 *
 * @author Multitallented
 */
public abstract class UserState extends EffectSource {
    private final long period;
    private final long duration;
    private final HashSet<Integer> currentTasks = new HashSet<>();
    public UserState(String name, long duration, long period) {
        super(name);
        this.duration = duration;
        this.period = period;
    }
    
    public HashSet<Integer> getCurrentTasks() {
        return currentTasks;
    }
    
    public abstract HashSet<BuiltInUserStates> getDefaultStates();
    
    public abstract HashSet<UserState> getInternalStates();
    
    public void apply(final User user) {
        for (final UserState u : getInternalStates()) {
            u.apply(user);
        }
        if (duration > 0) {
            int idDuration = Spout.getScheduler().scheduleSyncDelayedTask(getPlugin(), new Runnable() {
                @Override
                public void run() {
                    remove(user);
                    for (int i : getCurrentTasks()) {
                        Spout.getScheduler().cancelTask(i);
                    }
                }
            }, getDuration(), TaskPriority.NORMAL);
            getCurrentTasks().add(idDuration);
        }
        if (period > 0) {
            int idPeriod = Spout.getScheduler().scheduleSyncRepeatingTask(getPlugin(), new Runnable() {
                @Override
                public void run() {
                    tick(user);
                }
            }, period, period, TaskPriority.NORMAL);
            getCurrentTasks().add(idPeriod);
        }
    }
    public void apply(Block block) {
        
    }
    public void apply(Entity e) {
        
    }
    
    public void tick(User user) {
        
    }
    public void tick(Block block) {
        
    }
    public void tick(Entity e) {
        
    }
    
    public void remove(User user) {
        for (int i : getCurrentTasks()) {
            Spout.getScheduler().cancelTask(i);
        }
        for (UserState us : getInternalStates()) {
            us.remove(user);
        }
    }
    public void remove(Block block) {
        
    }
    public void remove(Entity e) {
        
    }
    
    @Override
    public void execute(Proxis plugin, Skill.CastSkill cs, User target, HashMap<String, Object> node) {
        apply(target);
        //TODO add to scheduler
    }
    
    
    @Override
    public void execute(Proxis plugin, Skill.CastSkill cs, Entity target, HashMap<String, Object> node) {
        apply(target);
        //TODO add to scheduler
    }

    @Override
    public void execute(Proxis plugin, Skill.CastSkill cs, Block target, HashMap<String, Object> node) {
        apply(target);
        //TODO add to scheduler
    }
    
    public abstract void sendCancelledMessage(String username, CancelledMessageTypes type);
    
    public enum CancelledMessageTypes {
        CHAT,
        COMMAND,
        HEAL,
        DAMAGE,
        SKILL,
        MANA
    }
    
    public long getPeriod() {
        return period;
    }
    public long getDuration() {
        return duration;
    }
}