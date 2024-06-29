package de.gummit.items;

import de.gummit.RiftThingsMod;
import de.gummit.blocks.RiftCore;
import de.gummit.core.RiftRoom;
import de.gummit.core.RoomHandler;
import de.gummit.utils.ServerUtils;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RiftShard extends Item {

    public static final String ITEM_ID = "rift_shard";
    private final Lock useLock = new ReentrantLock();

    public RiftShard() {
        super(new Item.Properties()
                .stacksTo(64)
                .rarity(Rarity.RARE)
                .tab(RiftThingsMod.RIFT_THINGS_TAB));
    }

    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {
        if(useOnContext.getPlayer() == null || !useLock.tryLock()) {
            return super.useOn(useOnContext);
        }
        // ist used on the Rift Core
        if(!(useOnContext.getPlayer().level.getBlockState(useOnContext.getClickedPos()).getBlock() instanceof RiftCore)) {
            useLock.unlock();
            return InteractionResult.PASS;
        }
        RoomHandler roomHandler = new RoomHandler(useOnContext.getPlayer());
        ServerUtils.getServer(useOnContext.getPlayer()).executeBlocking(() -> {
            int used = roomHandler.getRoomFromPlayer(useOnContext.getPlayer()).increaseHeight(useOnContext.getItemInHand().getCount(), useOnContext.getPlayer());
            useOnContext.getItemInHand().setCount(useOnContext.getItemInHand().getCount() - used);
        });
        useLock.unlock();
        return InteractionResult.SUCCESS;
    }
}
