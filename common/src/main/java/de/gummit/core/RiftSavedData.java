package de.gummit.core;

import de.gummit.RiftThingsMod;
import de.gummit.dimension.ModDimensions;
import net.minecraft.nbt.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;

import java.util.HashMap;
import java.util.UUID;

public class RiftSavedData extends PersistentState {

    private static final String PERSISTENT_IDENTIFIER = RiftThingsMod.MOD_ID + ModDimensions.RIFT.getValue().toString();
    private static final String TAG_IDENTIFIER = ModDimensions.RIFT.getValue().toString();

    public HashMap<UUID, RiftRoom> cubes;

    public RiftSavedData() {
        super(PERSISTENT_IDENTIFIER);
        cubes = new HashMap<>();
    }

    public static RiftSavedData getSaveData(MinecraftServer server) {
        ServerWorld world = server.getWorld(ModDimensions.RIFT);
        if(world == null) {
            return new RiftSavedData();
        }
        PersistentStateManager persistentStateManager = world.getPersistentStateManager();
        RiftSavedData state = persistentStateManager.getOrCreate(RiftSavedData::new, PERSISTENT_IDENTIFIER);
        state.markDirty();
        return state;
    }

    @Override
    public void fromTag(CompoundTag compoundTag) {
        cubes = tagToMap(compoundTag.getCompound(TAG_IDENTIFIER));
    }

    @Override
    public CompoundTag toTag(CompoundTag compoundTag) {
        compoundTag.put(TAG_IDENTIFIER, mapToTag(cubes));
        return compoundTag;
    }



    private static CompoundTag mapToTag(HashMap<UUID, RiftRoom> cubes) {
        CompoundTag compoundTag = new CompoundTag();
        ListTag cubeTags = new ListTag();

        for (RiftRoom cube : cubes.values())
        {
            CompoundTag cubeCompound = new CompoundTag();
            cube.writeToNBT(cubeCompound);
            cubeTags.add(cubeCompound);
        }

        compoundTag.put("cubes", cubeTags);
        return compoundTag;
    }

    private static HashMap<UUID, RiftRoom> tagToMap(CompoundTag compoundTag) {
        ListTag cubeTags = compoundTag.getList("cubes", (byte) 10);
        HashMap<UUID, RiftRoom> result = new HashMap<>();

        for (Tag tag : cubeTags) {
            RiftRoom cube = new RiftRoom();
            cube.readFromNBT((CompoundTag) tag);
            result.put(cube.owner, cube);
        }

        return result;
    }
}
