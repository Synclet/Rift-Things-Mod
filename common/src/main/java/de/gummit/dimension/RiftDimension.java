package de.gummit.dimension;

import net.minecraft.server.MinecraftServer;

import java.util.Objects;

public class RiftDimension {

    public static final String DIMENSION_ID = "rift";

    public static void prepareDimensionType(MinecraftServer server) {
        ModDimensions.RIFT_TYPE = Objects.requireNonNull(server.getWorld(ModDimensions.RIFT)).getDimension();
    }
}
