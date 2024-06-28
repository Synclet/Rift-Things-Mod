package de.gummit.utils;

import net.minecraft.ReportedException;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.TicketType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;

public class TeleportUtils {

    public static void teleport(Player player, BlockPos target) throws ReportedException, IllegalStateException {
        teleport(player.level.dimension(), player, target);
    }

    public static void teleport(ResourceKey<Level> level, Player player, BlockPos target) throws ReportedException, IllegalStateException {
        MinecraftServer server = ServerUtils.getServer(player);
        if (server == null) {
            return;
        }
        ServerLevel serverLevel = server.getLevel(level);
        if (serverLevel == null) {
            return;
        }
        serverLevel.getChunkSource().addRegionTicket(TicketType.POST_TELEPORT, new ChunkPos(target), 1, player.getId());

        ServerPlayer serverPlayer;
        if (player instanceof ServerPlayer) {
            serverPlayer = (ServerPlayer) player;
        } else {
            serverPlayer = server.getPlayerList().getPlayer(player.getUUID());
        }

        if (serverPlayer == null) {
            return;
        }

        //player.stopUsingItem();
        //SynchedEntityData data = serverPlayer.getEntityData();
        server.executeBlocking(() -> {serverPlayer.teleportTo(serverLevel, target.getX(), target.getY(), target.getZ(), player.yRot, player.xRot);});
        //serverPlayer.refreshDimensions();
        //serverPlayer.getEntityData().assignValues(data.getAll());
        //player.stopUsingItem();

        // https://github.com/AbsolemJackdaw/Telepads2016/blob/1.17/src/main/java/subaraki/telepads/utility/masa/Teleport.java
        // https://github.com/TwelveIterationMods/Waystones/blob/cfdd18cbe913beaed4793cfee958eb453f5dfcbe/common/src/main/java/net/blay09/mods/waystones/core/WaystoneTeleportManager.java#L131
    }

}
