package multitallented.redcastlemedia.spout.proxis.models.skills;

/**
 *
 * @author Multitallented
 */
public enum SkillResult {
    FAILED, //use up costs but dont throw cast event
    REFUND, //don't use up costs or throw cast event
    NORMAL //use costs and throw event
}
