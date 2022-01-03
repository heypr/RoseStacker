package dev.rosewood.rosestacker.stack;

/**
 * An enum to determine if two entities can stack.
 * If they can stack, the result is CAN_STACK.
 * They can't stack if any other result is specified.
 */
public enum EntityStackComparisonResult {
    CAN_STACK,
    DIFFERENT_ENTITY_TYPES,
    STACKING_NOT_ENABLED,
    STACK_SIZE_TOO_LARGE,
    MARKED_UNSTACKABLE,
    CUSTOM_NAMED,
    NOT_ON_GROUND,
    IN_WATER,
    NOT_SPAWNED_FROM_SPAWNER,
    PART_OF_VEHICLE,
    LEASHED,
    INVULNERABLE,
    HAS_EQUIPMENT,
    DIFFERENT_COLORS,
    SITTING,
    TAMED,
    DIFFERENT_OWNERS,
    DIFFERENT_AGES,
    BABY,
    BREEDING,
    SADDLED,
    HAS_CHEST,
    PATROL_LEADER,
    TRADING,
    SLEEPING,
    ANGRY,
    HAS_HIVE,
    DIFFERENT_HIVES,
    HAS_STUNG,
    HAS_FLOWER,
    HAS_NECTAR,
    DIFFERENT_TYPES,
    DIFFERENT_COLLAR_COLORS,
    CHARGED,
    HOLDING_BLOCK,
    SPAWNED_BY_PLAYER,
    UNHUNTABLE,
    HAS_ARMOR,
    DIFFERENT_STYLES,
    CONVERTING,
    DIFFERENT_DECORS,
    DIFFERENT_MAIN_GENES,
    DIFFERENT_RECESSIVE_GENES,
    DIFFERENT_SIZES,
    IMMUNE_TO_ZOMBIFICATION,
    UNABLE_TO_HUNT,
    DIFFERENT_INFLATIONS,
    SHEARED,
    NO_PUMPKIN,
    SHIVERING,
    DIFFERENT_BODY_COLORS,
    DIFFERENT_PATTERNS,
    DIFFERENT_PATTERN_COLORS,
    CHARGING,
    PROFESSIONED,
    DIFFERENT_PROFESSIONS,
    DIFFERENT_LEVELS,
    PART_OF_ACTIVE_RAID,
    BRAVO_SIX_GOING_DARK,
    SCREAMING,
    PLAYING_DEAD
}
