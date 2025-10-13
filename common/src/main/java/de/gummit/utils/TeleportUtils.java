package de.gummit.utils;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TeleportUtils {

    public static void teleport(PlayerEntity player, BlockPos target) {
        teleport(player.getWorld().getRegistryKey(), player, target);
    }

    public static void teleport(RegistryKey<World> world, PlayerEntity player, BlockPos target) {
        MinecraftServer server = ServerUtils.getServer();
        if (server == null) {
            return;
        }
        ServerWorld serverWorld = server.getWorld(world);
        if (serverWorld == null) {
            return;
        }
        //serverWorld.getChunkManager().addTicket(ChunkTicketType.POST_TELEPORT, new ChunkPos(target), 1, player.getEntityId());

        ServerPlayerEntity serverPlayer;
        if (player instanceof ServerPlayerEntity) {
            serverPlayer = (ServerPlayerEntity) player;
        } else {
            serverPlayer = server.getPlayerManager().getPlayer(player.getUuid());
        }

        if (serverPlayer == null) {
            return;
        }

        player.stopUsingItem();
        player.stopRiding();
        server.execute(() -> {
            serverWorld.getChunk(target);
            serverPlayer.teleport(serverWorld, target.getX(), target.getY(), target.getZ(), player.getYaw(), player.getPitch());
        });

        // https://github.com/AbsolemJackdaw/Telepads2016/blob/1.17/src/main/java/subaraki/telepads/utility/masa/Teleport.java
        // https://github.com/TwelveIterationMods/Waystones/blob/cfdd18cbe913beaed4793cfee958eb453f5dfcbe/common/src/main/java/net/blay09/mods/waystones/core/WaystoneTeleportManager.java#L131
    }

}