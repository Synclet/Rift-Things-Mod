package de.gummit.utils;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;

public class NBTUtils
{
	public static void writeBlockPosToNBT(@NotNull CompoundTag compound, String tagName, BlockPos pos)
	{
		CompoundTag posPounds = new CompoundTag();

		posPounds.putDouble("posX", pos.getX());
		posPounds.putDouble("posY", pos.getY());
		posPounds.putDouble("posZ", pos.getZ());

		compound.put(tagName, posPounds);
	}

	public static BlockPos readBlockPosFromNBT(@NotNull CompoundTag compound, String tagName)
	{
		CompoundTag posPounds = compound.getCompound(tagName);

		return new BlockPos(posPounds.getDouble("posX"), posPounds.getDouble("posY"), posPounds.getDouble("posZ"));
	}
}