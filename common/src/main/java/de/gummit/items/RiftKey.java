package de.gummit.items;

import de.gummit.RiftThingsMod;
import de.gummit.core.RoomHandler;
import de.gummit.dimension.ModDimensions;
import de.gummit.utils.NBTUtils;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class RiftKey extends Item {

    public static final String ITEM_ID = "rift_key";

    public RiftKey() {
        super(new Properties()
                .stacksTo(1)
                .rarity(Rarity.RARE)
                .tab(RiftThingsMod.RIFT_THINGS_TAB));
    }

    @Override
    public UseAnim getUseAnimation(ItemStack itemStack) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack itemStack) {
        return 1000;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {

        player.stopUsingItem();
        RoomHandler roomHandler = new RoomHandler(player);
        // Is the player already in the Rift
        if (player.level.dimensionType().equalTo(ModDimensions.RIFT_TYPE)) {
            try {
                roomHandler.teleportPlayerBack(
                        player,
                        NBTUtils.readBlockPosFromNBT(player.getItemInHand(interactionHand).getTag(), "originPos"),
                        player.getItemInHand(interactionHand).getTag().getString("originDim"));
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        } else {
            if(player.getItemInHand(interactionHand).getTag() == null) {
                player.getItemInHand(interactionHand).setTag(new CompoundTag());
            }
            CompoundTag tag = player.getItemInHand(interactionHand).getTag();
            NBTUtils.writeBlockPosToNBT(tag, "originPos", player.blockPosition());
            tag.putString("originDim", player.level.dimension().location().toString());
            roomHandler.teleportPlayerToSpectreCube(player);
        }

        return InteractionResultHolder.success(player.getItemInHand(interactionHand));
    }
}