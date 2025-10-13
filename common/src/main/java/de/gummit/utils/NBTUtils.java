package de.gummit.utils;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;

public class NBTUtils
{
	public static void writeBlockPosToNBT(@NotNull NbtCompound compound, String tagName, BlockPos pos)
	{
		NbtCompound posPounds = new NbtCompound();

		posPounds.putDouble("posX", pos.getX());
		posPounds.putDouble("posY", pos.getY());
		posPounds.putDouble("posZ", pos.getZ());

		compound.put(tagName, posPounds);
	}

	public static BlockPos readBlockPosFromNBT(@NotNull NbtCompound compound, String tagName)
	{
		NbtCompound posPounds = compound.getCompound(tagName);

		return new BlockPos(posPounds.getInt("posX"), posPounds.getInt("posY"), posPounds.getInt("posZ"));
	}
}