package de.gummit.core;

import de.gummit.RiftThingsMod;
import de.gummit.dimensions.ModDimensions;
import net.minecraft.nbt.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

public class RiftSavedData extends PersistentState {

    private static final String PERSISTENT_IDENTIFIER = RiftThingsMod.MOD_ID + ModDimensions.RIFT.getValue().toString();
    private static final String TAG_IDENTIFIER = ModDimensions.RIFT.getValue().toString();

    public HashMap<UUID, RiftRoom> cubes;

    public RiftSavedData() {
        super();
        cubes = new HashMap<>();
    }

    public RiftSavedData(HashMap<UUID, @NotNull RiftRoom> cubes) {
        super();
        this.cubes = cubes;
    }

    public static RiftSavedData getSaveData(MinecraftServer server) {
        ServerWorld world = server.getWorld(ModDimensions.RIFT);
        if(world == null) {
            return new RiftSavedData();
        }
        PersistentStateManager persistentStateManager = world.getPersistentStateManager();
        RiftSavedData state = persistentStateManager.getOrCreate(RiftSavedData::readNbt, RiftSavedData::new, PERSISTENT_IDENTIFIER);
        state.markDirty();
        return state;
    }

    @Override
    public NbtCompound writeNbt(NbtCompound compoundTag) {
        compoundTag.put(TAG_IDENTIFIER, mapToTag(cubes));
        return compoundTag;
    }

    public static RiftSavedData readNbt(NbtCompound compoundTag) {
        return new RiftSavedData(tagToMap(compoundTag.getCompound(TAG_IDENTIFIER)));
    }

    private static NbtCompound mapToTag(HashMap<UUID, RiftRoom> cubes) {
        NbtCompound compoundTag = new NbtCompound();
        NbtList cubeTags = new NbtList();

        for (RiftRoom cube : cubes.values())
        {
            NbtCompound cubeCompound = new NbtCompound();
            cube.writeToNBT(cubeCompound);
            cubeTags.add(cubeCompound);
        }

        compoundTag.put("cubes", cubeTags);
        return compoundTag;
    }

    private static HashMap<UUID, RiftRoom> tagToMap(NbtCompound compoundTag) {
        NbtList cubeTags = compoundTag.getList("cubes", (byte) 10);
        HashMap<UUID, RiftRoom> result = new HashMap<>();

        for (NbtElement tag : cubeTags) {
            RiftRoom cube = new RiftRoom();
            cube.readFromNBT((NbtCompound) tag);
            result.put(cube.owner, cube);
        }

        return result;
    }
}