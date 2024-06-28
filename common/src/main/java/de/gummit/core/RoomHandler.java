package de.gummit.core;

import de.gummit.dimension.ModDimensions;
import de.gummit.utils.ServerUtils;
import de.gummit.utils.TeleportUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunk;
import java.util.UUID;

public class RoomHandler {

    private final RiftSavedData savedData;
    public MinecraftServer server;

    public RoomHandler(LivingEntity livingEntity) {
        server = ServerUtils.getServer(livingEntity);
        savedData = new RiftSavedData(server);
    }

    public RiftRoom getSpectreCubeFromPos(Level level, BlockPos pos) {
        if (level.dimension() != ModDimensions.RIFT) {
            return null;
        }
        if (pos.getZ() > 16 || pos.getZ() < 0) {
            return null;
        }

        LevelChunk c = level.getChunkAt(pos);

        int position = c.getPos().x / 16;

        for (RiftRoom cube : savedData.cubes.values()) {
            if (cube.position / 16 == position) {
                if (pos.getY() <= 0 || pos.getY() > cube.height + 1 || pos.getX() < position * 16 || pos.getX() > cube.position * 16 + 15) {
                    return null;
                }
                else {
                    return cube;
                }
            }
        }

        return null;
    }

    public void teleportPlayerToSpectreCube(Player player) {
        UUID uuid = player.getGameProfile().getId();
        RiftRoom riftRoom;

        if (savedData.cubes.containsKey(uuid)) {
            riftRoom = savedData.cubes.get(uuid);
        } else {
            riftRoom = generateSpectreCube(uuid);
        }

        TeleportUtils.teleport(
                ModDimensions.RIFT,
                player,
                new BlockPos(riftRoom.spawnBlock.getX() + 0.5, riftRoom.spawnBlock.getY() + 1, riftRoom.spawnBlock.getZ() + 0.5));
    }

    public void teleportPlayerBack(Player player, BlockPos pos, String dim) {
        ResourceKey<Level> level = ResourceKey.create(
                ResourceKey.createRegistryKey(new ResourceLocation("minecraft:dimension")),
                new ResourceLocation(dim));

        TeleportUtils.teleport(level, player, pos);
    }

    private RiftRoom generateSpectreCube(UUID uuid) {
        RiftRoom cube = new RiftRoom(savedData, uuid, savedData.cubes.size() * 16);

        cube.generate(server.getLevel(ModDimensions.RIFT));
        savedData.cubes.put(uuid, cube);
        savedData.setDirty();
        return cube;
    }


}
