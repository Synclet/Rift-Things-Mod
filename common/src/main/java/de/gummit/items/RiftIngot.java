package de.gummit.items;

import net.minecraft.item.Item;
import net.minecraft.util.Rarity;

public class RiftIngot extends Item {

    public static final String ITEM_ID = "rift_ingot";

    public RiftIngot() {
        super(new Settings()
                .maxCount(64)
                .rarity(Rarity.RARE)
                .arch$tab(ModItems.RIFT_THINGS_TAB));
    }
}