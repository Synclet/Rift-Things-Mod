package de.gummit.items;

import de.gummit.RiftThingsMod;
import de.gummit.blocks.RiftCore;
import de.gummit.core.RoomHandler;
import de.gummit.utils.ServerUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Rarity;

public class RiftShard extends Item {

    public static final String ITEM_ID = "rift_shard";

    public RiftShard() {
        super(new Item.Settings()
                .maxCount(64)
                .rarity(Rarity.RARE)
                .group(RiftThingsMod.RIFT_THINGS_TAB));
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(context.getWorld().isClient()) {
            return ActionResult.PASS;
        }
        if(context.getPlayer() == null) {
            return super.useOnBlock(context);
        }
        // if not used on the Rift Core
        if(!(context.getPlayer().world.getBlockState(context.getBlockPos()).getBlock() instanceof RiftCore)) {
            return ActionResult.PASS;
        }
        RoomHandler roomHandler = new RoomHandler(context.getWorld());
        ServerUtils.getServer(context.getWorld()).execute(() -> {
            int used = roomHandler.getRoomFromPlayer(context.getPlayer()).increaseHeight(context.getStack().getCount());
            context.getStack().setCount(context.getStack().getCount() - used);
        });
        return ActionResult.SUCCESS;
    }
}
