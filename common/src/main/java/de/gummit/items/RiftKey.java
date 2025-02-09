package de.gummit.items;

import de.gummit.RiftThingsMod;
import de.gummit.core.RoomHandler;
import de.gummit.dimension.ModDimensions;
import de.gummit.utils.NBTUtils;
import de.gummit.utils.ServerUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class RiftKey extends Item {

    public static final String ITEM_ID = "rift_key";

    public RiftKey() {
        super(new Settings()
                .maxCount(1)
                .rarity(Rarity.RARE)
                .group(RiftThingsMod.RIFT_THINGS_TAB));
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 1000;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        player.stopUsingItem();
        if(world.isClient()) {
            return TypedActionResult.pass(player.getStackInHand(hand));
        }
        RoomHandler roomHandler = new RoomHandler(world);

        // Is the player already in the Rift
        if (player.world.getDimension() == ModDimensions.RIFT_TYPE) {
            try {
                roomHandler.teleportPlayerBack(
                        player,
                        NBTUtils.readBlockPosFromNBT(player.getStackInHand(hand).getTag(), "originPos"),
                        player.getStackInHand(hand).getTag().getString("originDim"));
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        } else {
            if(player.getStackInHand(hand).getTag() == null) {
                player.getStackInHand(hand).setTag(new CompoundTag());
            }
            CompoundTag tag = player.getStackInHand(hand).getTag();
            NBTUtils.writeBlockPosToNBT(tag, "originPos", player.getBlockPos());
            tag.putString("originDim", player.world.getRegistryKey().getValue().toString());

            roomHandler.teleportPlayerToRoom(player);
        }

        return TypedActionResult.success(player.getStackInHand(hand));
    }
}