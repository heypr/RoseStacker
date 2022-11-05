package dev.rosewood.rosestacker.spawner.conditions.tags;

import dev.rosewood.rosestacker.RoseStacker;
import dev.rosewood.rosestacker.manager.ConfigurationManager.Setting;
import dev.rosewood.rosestacker.manager.EntityCacheManager;
import dev.rosewood.rosestacker.manager.LocaleManager;
import dev.rosewood.rosestacker.manager.StackManager;
import dev.rosewood.rosestacker.spawner.conditions.ConditionTag;
import dev.rosewood.rosestacker.stack.StackedEntity;
import dev.rosewood.rosestacker.stack.StackedSpawner;
import dev.rosewood.rosestacker.stack.settings.SpawnerStackSettings;
import java.util.Collection;
import java.util.List;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

public class MaxNearbyEntityConditionTag extends ConditionTag {

    private int maxNearbyEntities;
    private StackManager stackManager;
    private EntityCacheManager entityCacheManager;

    public MaxNearbyEntityConditionTag(String tag) {
        super(tag, false);
    }

    @Override
    public boolean check(StackedSpawner stackedSpawner, Block spawnBlock) {
        if (this.stackManager == null || this.entityCacheManager == null) {
            this.stackManager = RoseStacker.getInstance().getManager(StackManager.class);
            this.entityCacheManager = RoseStacker.getInstance().getManager(EntityCacheManager.class);
        }

        SpawnerStackSettings stackSettings = stackedSpawner.getStackSettings();
        int detectionRange = stackSettings.getEntitySearchRange() == -1 ? stackedSpawner.getSpawnerTile().getSpawnRange() : stackSettings.getEntitySearchRange();
        Block block = stackedSpawner.getBlock();
        EntityType entityType = stackedSpawner.getSpawnerTile().getSpawnedType();

        Collection<Entity> nearbyEntities = this.entityCacheManager.getNearbyEntities(
                block.getLocation().clone().add(0.5, 0.5, 0.5),
                detectionRange,
                entity -> entity.getType() == entityType);

        if (Setting.SPAWNER_MAX_NEARBY_ENTITIES_INCLUDE_STACKS.getBoolean()) {
            return nearbyEntities.stream().mapToInt(x -> {
                StackedEntity stackedEntity = this.stackManager.getStackedEntity((LivingEntity) x);
                return stackedEntity == null ? 1 : stackedEntity.getStackSize();
            }).sum() < this.maxNearbyEntities;
        } else {
            return nearbyEntities.size() < this.maxNearbyEntities;
        }
    }

    @Override
    public boolean parseValues(String[] values) {
        if (values.length != 1)
            return false;

        try {
            this.maxNearbyEntities = Integer.parseInt(values[0]);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    @Override
    protected List<String> getInfoMessageValues(LocaleManager localeManager) {
        return List.of(String.valueOf(this.maxNearbyEntities));
    }

}
