package de.gummit.core;

import de.gummit.blocks.ModBlocks;
import de.gummit.dimensions.ModDimensions;
import de.gummit.utils.NBTUtils;
import de.gummit.utils.ServerUtils;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class RiftRoom {
	private final int DIM_HEIGHT = 384;

	public UUID owner;
	public int height;
	public int position;

	public BlockPos spawnBlock;

	public RiftRoom() {
		this.height = 2;
	}

	public RiftRoom(UUID owner, int position) {
		this();

		this.owner = owner;
		this.position = position;

		spawnBlock = new BlockPos(position * 16 + 8, 0, 8);
	}

	public void writeToNBT(NbtCompound compound) {
		compound.putString("owner", owner.toString());
		compound.putInt("position", position);
		compound.putInt("height", height);
		NBTUtils.writeBlockPosToNBT(compound, "spawnBlock", spawnBlock);
	}

	public void readFromNBT(NbtCompound compound) {
		this.owner = UUID.fromString(compound.getString("owner"));
		this.position = compound.getInt("position");
		this.height = compound.getInt("height");
		this.spawnBlock = NBTUtils.readBlockPosFromNBT(compound, "spawnBlock");
	}

	public void generate(World world) {
		updateCube(world, position, 0, height);
	}

	public int increaseHeight(int amount) {
		int heightLeft = DIM_HEIGHT - (height + 1);
		int newHeight = height;

		if (heightLeft - amount > 0) {
			newHeight = height + amount;
		} else {
			newHeight = height + heightLeft;
		}
		int result = newHeight - height;
		if (newHeight != height) {
			changeHeight(ServerUtils.getServer().getWorld(ModDimensions.RIFT), newHeight);
		}
		return result;
	}

	private void changeHeight(World world, int newHeight) {
		updateCube(world, position, height, newHeight);
		this.height = newHeight;
	}

	private void updateCube(@NotNull World world, int position, int oldHeight, int newHeight) {
		BlockPos corner = new BlockPos(position * 16, 0, 0);
		BlockPos corner2 = corner.add(15, newHeight + 1, 15);

		int minX = Math.min(corner.getX(), corner2.getX());
		int minY = Math.min(corner.getY(), corner2.getY());
		int minZ = Math.min(corner.getZ(), corner2.getZ());

		int maxX = Math.max(corner.getX(), corner2.getX());
		int maxY = Math.max(corner.getY(), corner2.getY());
		int maxZ = Math.max(corner.getZ(), corner2.getZ());

		int coreX1 = (int) Math.floor((corner.getX() + corner2.getX()) / 2f);
		int coreX2 = (int) Math.ceil((corner.getX() + corner2.getX()) / 2f);
		int coreZ1 = (int) Math.floor((corner.getZ() + corner2.getZ()) / 2f);
		int coreZ2 = (int) Math.ceil((corner.getZ() + corner2.getZ()) / 2f);

		ServerUtils.getServer().execute(() -> {
			// generating floor and ceiling
			for (int x = minX; x <= maxX; x++) {
				for (int z = minZ; z <= maxZ; z++) {
					if((x == coreX1 || x == coreX2) && (z == coreZ1 || z == coreZ2)) {
						world.setBlockState(new BlockPos(x, minY, z), ModBlocks.RIFT_CORE.get().getDefaultState(), 3);
					} else {
						world.setBlockState(new BlockPos(x, minY, z), ModBlocks.RIFT_BLOCK.get().getDefaultState(), 3);
					}
					world.setBlockState(new BlockPos(x, maxY, z), ModBlocks.RIFT_BLOCK.get().getDefaultState(), 3);
					// remove old ceiling
					if(oldHeight < 2) {
						continue;
					}
					world.setBlockState(new BlockPos(x, oldHeight + 1, z), Blocks.AIR.getDefaultState(), 2);
				}
			}
			// generating walls on x-axis
			for (int x = minX; x <= maxX; x++) {
				for (int y = minY; y <= maxY; y++) {
					world.setBlockState(new BlockPos(x, y, minZ), ModBlocks.RIFT_BLOCK.get().getDefaultState(), 3);
					world.setBlockState(new BlockPos(x, y, maxZ), ModBlocks.RIFT_BLOCK.get().getDefaultState(), 3);
				}
			}
			// generating walls on z-axis
			for (int z = minZ; z <= maxZ; z++) {
				for (int y = minY; y <= maxY; y++) {
					world.setBlockState(new BlockPos(minX, y, z), ModBlocks.RIFT_BLOCK.get().getDefaultState(), 3);
					world.setBlockState(new BlockPos(maxX, y, z), ModBlocks.RIFT_BLOCK.get().getDefaultState(), 3);
				}
			}
		});

	}


}