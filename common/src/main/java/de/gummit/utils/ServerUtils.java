package de.gummit.utils;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public class ServerUtils {

    public static MinecraftServer dedicatedServer = null;

    public static MinecraftServer getServer() {
        if(dedicatedServer != null) {
            return dedicatedServer;
        } else {
            return getIntegratedServer();
        }
        //return Optional.ofNullable(dedicatedServer).orElse(getIntegratedServer());
    }

    public static MinecraftServer getServer(World world) {
        MinecraftServer server = ServerUtils.getServer();
        if(server == null && world instanceof ServerWorld) {
            server = ((ServerWorld)world).getServer();
        }
        return server;
    }

    @Environment(EnvType.CLIENT)
    private static MinecraftServer getIntegratedServer() {
        return MinecraftClient.getInstance().getServer();
    }

}