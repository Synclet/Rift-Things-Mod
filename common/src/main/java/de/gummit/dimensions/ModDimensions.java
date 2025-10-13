package de.gummit.dimensions;

import de.gummit.RiftThingsMod;
import dev.architectury.event.events.common.LifecycleEvent;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

public class ModDimensions {

    public static final RegistryKey<World> RIFT = RegistryKey.of(RegistryKeys.WORLD, new Identifier(RiftThingsMod.MOD_ID, RiftDimension.DIMENSION_ID));
    public static DimensionType RIFT_TYPE;

    public static void init() {
        LifecycleEvent.SERVER_STARTED.register(RiftDimension::prepareDimensionType);
    }

}