package de.gummit.entity;

import de.gummit.RiftThingsMod;
import me.shedaniel.architectury.event.events.EntityEvent;
import me.shedaniel.architectury.registry.RegistrySupplier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class ModEntities {

    public static final RegistrySupplier<EntityType<RiftRemnant>> RIFT_REMNANT = RiftThingsMod.ENTITIES.register(RiftRemnant.ENTITY_ID,
            () -> EntityType.Builder.of(RiftRemnant::new, MobCategory.MISC).build(RiftRemnant.ENTITY_ID));


    public static void init() {
        RiftThingsMod.ENTITIES.register();

        EntityEvent.LIVING_DEATH.register(RiftRemnant::spawnOnDeath);
    }

    public static AttributeSupplier.Builder getDefaultAttributes() {
        return AttributeSupplier.builder()
                .add(Attributes.MAX_HEALTH, 20d)
                .add(Attributes.FOLLOW_RANGE, 0d)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0d)
                .add(Attributes.ARMOR, 0d)
                .add(Attributes.ARMOR_TOUGHNESS, 0d)
                .add(Attributes.ATTACK_DAMAGE, 0d)
                .add(Attributes.ATTACK_KNOCKBACK, 0d)
                .add(Attributes.ATTACK_SPEED, 1d)
                .add(Attributes.FLYING_SPEED, 1d)
                .add(Attributes.JUMP_STRENGTH, 1d)
                .add(Attributes.MOVEMENT_SPEED, 1d)
                .add(Attributes.LUCK, 0d);
    }

}
