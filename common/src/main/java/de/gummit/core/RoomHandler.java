package de.gummit.core;

import de.gummit.dimensions.ModDimensions;
import de.gummit.utils.ServerUtils;
import de.gummit.utils.TeleportUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.UUID;

public class RoomHandler {

    MinecraftServer server;

    public RoomHandler(World world) {
        if(world.isClient()) {
            throw new RuntimeException("Trying to directly access Server-data from the Client - Rift-Things");
        }
        server = ServerUtils.getServer(world);
    }

    public RiftRoom getRoomFromPlayer(PlayerEntity player) {
        return RiftSavedData.getSaveData(server).cubes.get(player.getUuid());
    }

    public void teleportPlayerToRoom(PlayerEntity player) {
        RiftSavedData savedData = RiftSavedData.getSaveData(server);
        UUID uuid = player.getGameProfile().getId();
        RiftRoom riftRoom;

        if (savedData.cubes.containsKey(uuid)) {
            riftRoom = savedData.cubes.get(uuid);
        } else {
            riftRoom = generateRoom(player);
        }

        TeleportUtils.teleport(
                ModDimensions.RIFT,
                player,
                new BlockPos(riftRoom.spawnBlock.getX(), riftRoom.spawnBlock.getY() + 1, riftRoom.spawnBlock.getZ()));
    }

    public void teleportPlayerBack(PlayerEntity player, BlockPos pos, String dim) {
        RegistryKey<World> level = RegistryKey.of(
                RegistryKey.ofRegistry(new Identifier("minecraft:dimension")),
                new Identifier(dim));

        TeleportUtils.teleport(level, player, pos);
    }

    private RiftRoom generateRoom(PlayerEntity player) {
        RiftSavedData savedData = RiftSavedData.getSaveData(server);
        RiftRoom cube = new RiftRoom(player.getUuid(), savedData.cubes.size() * 16);

        cube.generate(server.getWorld(ModDimensions.RIFT));
        savedData.cubes.put(player.getUuid(), cube);
        savedData.markDirty();
        return cube;
    }


}