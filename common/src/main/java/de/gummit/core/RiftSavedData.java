package de.gummit.core;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import de.gummit.RiftThingsMod;
import de.gummit.dimension.ModDimensions;
import de.gummit.dimension.RiftDimension;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.TagParser;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.LevelResource;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.HashMap;
import java.util.UUID;

public class RiftSavedData extends SavedData {

    private static final String IDENTIFIER = ModDimensions.RIFT.toString();
    private File SAVE_FILE;

    public final MinecraftServer server;

    public HashMap<UUID, RiftRoom> cubes;

    public RiftSavedData(MinecraftServer server) {
        super(IDENTIFIER);
        this.server = server;
        server.executeBlocking(() -> {
            SAVE_FILE = server.getWorldPath(
                    new LevelResource("dimensions"))
                    .resolve(RiftThingsMod.MOD_ID)
                    .resolve(RiftDimension.DIMENSION_ID)
                    .resolve("rift.dat").toFile();
        });
        cubes = new HashMap<>();
        load(new CompoundTag());
    }

    @Override
    public void load(@NotNull CompoundTag compoundTag) {
        server.executeBlocking(() -> {
            try (FileReader reader = new FileReader(SAVE_FILE)) {
                CompoundTag tag = TagParser.parseTag(IOUtils.toString(reader));
                cubes = tagToMap(tag.getCompound(IDENTIFIER));
                compoundTag.merge(tag.getCompound(IDENTIFIER));
            } catch (FileNotFoundException e) {
                // Do nothing
            } catch (IOException | CommandSyntaxException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public CompoundTag save(@NotNull CompoundTag compoundTag) {
        compoundTag.put(IDENTIFIER, mapToTag(cubes));
        server.executeBlocking(() -> {
            try (FileWriter writer = new FileWriter(SAVE_FILE)) {
                writer.write(compoundTag.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        this.setDirty(false);
        return compoundTag;
    }

    @Override
    public void setDirty() {
        super.setDirty();
        save(new CompoundTag());
    }

    private CompoundTag mapToTag(HashMap<UUID, RiftRoom> cubes) {
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

    private HashMap<UUID, RiftRoom> tagToMap(CompoundTag compoundTag) {
        ListTag cubeTags = compoundTag.getList("cubes", (byte) 10);
        HashMap<UUID, RiftRoom> result = new HashMap<>();

        for (Tag tag : cubeTags) {
            RiftRoom cube = new RiftRoom(this);
            cube.readFromNBT((CompoundTag) tag);
            result.put(cube.owner, cube);
        }

        return result;
    }

}
