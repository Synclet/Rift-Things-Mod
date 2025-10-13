package de.gummit.items;

import net.minecraft.item.Item;
import net.minecraft.util.Rarity;

public class RiftPearl extends Item {

    public static final String ITEM_ID = "rift_pearl";

    public RiftPearl() {
        super(new Settings()
                .maxCount(64)
                .rarity(Rarity.RARE)
                .arch$tab(ModItems.RIFT_THINGS_TAB));
    }
}