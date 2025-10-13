package de.gummit.items;

import de.gummit.blocks.ModBlocks;
import de.gummit.blocks.RiftCore;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Rarity;

public class RiftCoreBlockItem extends BlockItem {

    public static final String ITEM_ID = RiftCore.BLOCK_ID;

    public RiftCoreBlockItem() {
        super(ModBlocks.RIFT_CORE.get(), new Settings()
                .maxCount(64)
                .rarity(Rarity.COMMON));
    }
}