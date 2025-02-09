package de.gummit.entity;

import de.gummit.RiftThingsMod;
import me.shedaniel.architectury.event.events.EntityEvent;
import me.shedaniel.architectury.registry.RegistrySupplier;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;

public class ModEntities {

    public static final RegistrySupplier<EntityType<RiftRemnant>> RIFT_REMNANT = RiftThingsMod.ENTITIES.register(RiftRemnant.ENTITY_ID,
            () -> EntityType.Builder.create(RiftRemnant::new, SpawnGroup.MISC).build(RiftRemnant.ENTITY_ID));


    public static void init() {
        RiftThingsMod.ENTITIES.register();

        EntityEvent.LIVING_DEATH.register(RiftRemnant::spawnOnDeath);
    }

    public static DefaultAttributeContainer.Builder getDefaultAttributes() {
        return DefaultAttributeContainer.builder()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20d)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 0d)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0d)
                .add(EntityAttributes.GENERIC_ARMOR, 0d)
                .add(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, 0d)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 0d)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0d)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 1d)
                .add(EntityAttributes.GENERIC_FLYING_SPEED, 1d)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1d)
                .add(EntityAttributes.GENERIC_LUCK, 0d);
    }

}
