package de.gummit.items;

import de.gummit.blocks.ModBlocks;
import de.gummit.blocks.RiftBlock;
import de.gummit.blocks.RiftCore;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Rarity;

public class RiftCoreBlockItem extends BlockItem {

    public static final String ITEM_ID = RiftCore.BLOCK_ID;

    public RiftCoreBlockItem() {
        super(ModBlocks.RIFT_CORE.get(), new Properties()
                .stacksTo(64)
                .rarity(Rarity.COMMON)
                .tab(null));
    }
}
