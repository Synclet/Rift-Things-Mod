package de.gummit.dimension;

import de.gummit.RiftThingsMod;
import me.shedaniel.architectury.event.events.LifecycleEvent;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

public class ModDimensions {

    public static final RegistryKey<World> RIFT = RegistryKey.of(Registry.DIMENSION, new Identifier(RiftThingsMod.MOD_ID, RiftDimension.DIMENSION_ID));
    public static DimensionType RIFT_TYPE;

    public static void init() {
        LifecycleEvent.SERVER_STARTED.register(RiftDimension::prepareDimensionType);
    }

}
