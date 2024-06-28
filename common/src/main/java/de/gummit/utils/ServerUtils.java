package de.gummit.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class ServerUtils {

    public static MinecraftServer getServer(LivingEntity livingEntity) {
        return Optional.ofNullable(livingEntity.getServer()).orElse(Minecraft.getInstance().getSingleplayerServer());
    }

    public static MinecraftServer getServer(Level level) {
        return Optional.ofNullable(level.getServer()).orElse(Minecraft.getInstance().getSingleplayerServer());
    }

}
