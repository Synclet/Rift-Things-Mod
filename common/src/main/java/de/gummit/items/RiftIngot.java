package de.gummit.items;

import de.gummit.RiftThingsMod;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class RiftIngot extends Item {

    public static final String ITEM_ID = "rift_ingot";

    public RiftIngot() {
        super(new Properties()
                .stacksTo(64)
                .rarity(Rarity.RARE)
                .tab(RiftThingsMod.RIFT_THINGS_TAB));
    }
}
