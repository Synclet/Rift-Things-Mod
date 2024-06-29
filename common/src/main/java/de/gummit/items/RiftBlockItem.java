package de.gummit.items;

import de.gummit.blocks.ModBlocks;
import de.gummit.blocks.RiftBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Rarity;

public class RiftBlockItem extends BlockItem {

    public static final String ITEM_ID = RiftBlock.BLOCK_ID;

    public RiftBlockItem() {
        super(ModBlocks.RIFT_BLOCK.get(), new Properties()
                .stacksTo(64)
                .rarity(Rarity.COMMON)
                .tab(null));
    }
}
