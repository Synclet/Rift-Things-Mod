package de.gummit.items;

import de.gummit.core.RoomHandler;
import de.gummit.dimensions.ModDimensions;
import de.gummit.utils.NBTUtils;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RiftKey extends Item {

    public static final String ITEM_ID = "rift_key";

    public static final String DIM_NBT_KEY = "originDim";
    public static final String POS_NBT_KEY = "originPos";

    public RiftKey() {
        super(new Settings()
                .maxCount(1)
                .rarity(Rarity.RARE)
                .arch$tab(ModItems.RIFT_THINGS_TAB));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        player.stopUsingItem();
        if(world.isClient()) {
            return TypedActionResult.pass(player.getStackInHand(hand));
        }
        RoomHandler roomHandler = new RoomHandler(world);

        // Is the player already in the Rift
        NbtCompound nbt = player.getStackInHand(hand).getNbt();
        if (player.getWorld().getDimension() == ModDimensions.RIFT_TYPE) {

            if(nbt == null || !nbt.contains(DIM_NBT_KEY) || !nbt.contains(POS_NBT_KEY)) {
                return TypedActionResult.fail(player.getStackInHand(hand));
            }
            roomHandler.teleportPlayerBack(
                    player,
                    NBTUtils.readBlockPosFromNBT(nbt, POS_NBT_KEY),
                    nbt.getString(DIM_NBT_KEY));
        } else {
            if(nbt == null) {
                nbt = new NbtCompound();
            }
            NBTUtils.writeBlockPosToNBT(nbt, POS_NBT_KEY, player.getBlockPos());
            nbt.putString(DIM_NBT_KEY, player.getWorld().getRegistryKey().getValue().toString());
            player.getStackInHand(hand).setNbt(nbt);

            roomHandler.teleportPlayerToRoom(player);
        }

        return TypedActionResult.success(player.getStackInHand(hand));
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        NbtCompound nbt = stack.getNbt();
        if(nbt == null || !nbt.contains(DIM_NBT_KEY) || !nbt.contains(POS_NBT_KEY)) {
             super.appendTooltip(stack, world, tooltip, context);
            return;
        }
        BlockPos pos = NBTUtils.readBlockPosFromNBT(nbt, POS_NBT_KEY);

        tooltip.add(Text.of(nbt.getString(DIM_NBT_KEY) + " X: " + pos.getX() + " Y: " + pos.getY() + " Z: " + pos.getZ()));
        super.appendTooltip(stack, world, tooltip, context);
    }
}