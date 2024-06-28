package de.gummit.dimension;

import de.gummit.RiftThingsMod;
import me.shedaniel.architectury.event.events.LifecycleEvent;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

public class ModDimensions {

    public static final ResourceKey<Level> RIFT = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(RiftThingsMod.MOD_ID, RiftDimension.DIMENSION_ID));
    public static DimensionType RIFT_TYPE;

    public static void init() {
        LifecycleEvent.SERVER_STARTED.register(RiftDimension::prepareDimensionType);
    }

}
