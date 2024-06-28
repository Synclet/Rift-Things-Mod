package de.gummit.items;

import de.gummit.RiftThingsMod;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tiers;

public class RiftShovel extends ShovelItem {

    public static final String ITEM_ID = "rift_shovel";

    public RiftShovel() {
        super(Tiers.DIAMOND, // Tier
              1.5f,             // Attack Damage Modifier
              -3f,         // Attack Speed Modifier
              new Properties()
                      .rarity(Rarity.RARE)
                      .tab(RiftThingsMod.RIFT_THINGS_TAB));
    }

}
