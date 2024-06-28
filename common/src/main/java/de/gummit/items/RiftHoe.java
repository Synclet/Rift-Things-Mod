package de.gummit.items;

import de.gummit.RiftThingsMod;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tiers;

public class RiftHoe extends HoeItem {

    public static final String ITEM_ID = "rift_hoe";

    public RiftHoe() {
        super(Tiers.DIAMOND, // Tier
              -3,             // Attack Damage Modifier
              0,         // Attack Speed Modifier
              new Properties()
                      .rarity(Rarity.RARE)
                      .tab(RiftThingsMod.RIFT_THINGS_TAB));
    }

}
