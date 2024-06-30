package de.gummit.core;

import de.gummit.blocks.ModBlocks;
import de.gummit.dimension.ModDimensions;
import de.gummit.utils.NBTUtils;
import de.gummit.utils.ServerUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.UUID;

public class RiftRoom {
	public UUID owner;
	public int height;
	public int position;

	private final RiftSavedData savedData;

	public BlockPos spawnBlock;

	public RiftRoom(RiftSavedData savedData) {
		this.savedData = savedData;
		this.height = 2;
	}

	public RiftRoom(RiftSavedData savedData, UUID owner, int position) {
		this(savedData);

		this.owner = owner;
		this.position = position;

		spawnBlock = new BlockPos(position * 16 + 8, 0, 8);
	}

	public void writeToNBT(CompoundTag compound) {
		compound.putString("owner", owner.toString());
		compound.putInt("position", position);
		compound.putInt("height", height);
		NBTUtils.writeBlockPosToNBT(compound, "spawnBlock", spawnBlock);
	}

	public void readFromNBT(CompoundTag compound) {
		this.owner = UUID.fromString(compound.getString("owner"));
		this.position = compound.getInt("position");
		this.height = compound.getInt("height");
		this.spawnBlock = NBTUtils.readBlockPosFromNBT(compound, "spawnBlock");
	}

	public void generate(Level level) {
		BlockPos corner = new BlockPos(position * 16, 0, 0);
		BlockPos corner2 = corner.offset(15, height + 1, 15);

		generateCube(level, corner, corner2, ModBlocks.RIFT_BLOCK.get().defaultBlockState(), 3);
		generateCube(level, corner.offset(7, 0, 7), corner.offset(8, 0, 8), ModBlocks.RIFT_CORE.get().defaultBlockState(), 3);
	}

	public int increaseHeight(int amount, Player player) {
		int heightLeft = 255 - (height + 1);
		int newHeight = height;

		if (heightLeft - amount > 0) {
			newHeight = height + amount;
		}
		else {
			newHeight = height + heightLeft;
		}
		int result = newHeight - height;
		if (newHeight != height) {
			changeHeight(savedData.server.getLevel(ModDimensions.RIFT), newHeight);
		}

		savedData.setDirty();
		return result;
	}

	private void changeHeight(Level level, int newHeight) {
		BlockPos corner = new BlockPos(position * 16, 0, 0);
		BlockPos corner2 = corner.offset(15, height + 1, 15);

		generateCube(level, corner, corner2, Blocks.AIR.defaultBlockState(), 2);
		this.height = newHeight;
		generate(level);
	}

	private static void generateCube(Level level, BlockPos pos1, BlockPos pos2, BlockState state, int flag)
	{
		int minX = Math.min(pos1.getX(), pos2.getX());
		int minY = Math.min(pos1.getY(), pos2.getY());
		int minZ = Math.min(pos1.getZ(), pos2.getZ());

		int maxX = Math.max(pos1.getX(), pos2.getX());
		int maxY = Math.max(pos1.getY(), pos2.getY());
		int maxZ = Math.max(pos1.getZ(), pos2.getZ());

		for (int x = minX; x <= maxX; x++) {
			for (int y = minY; y <= maxY; y++) {
				for (int z = minZ; z <= maxZ; z++) {
					if (x == minX || y == minY || z == minZ || x == maxX || y == maxY || z == maxZ) {
						BlockPos finpos = new BlockPos(x, y, z);
						ServerUtils.getServer(level).executeBlocking(() -> {
							level.setBlock(finpos, state, flag);
						});
					}
				}
			}
		}
	}
}