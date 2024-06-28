package de.gummit.items;

import de.gummit.RiftThingsMod;
import net.minecraft.world.item.*;

public class RiftSword extends SwordItem {

    public static final String ITEM_ID = "rift_sword";

    public RiftSword() {
        super(Tiers.DIAMOND, // Tier
              3,             // Attack Damage Modifier
              -2.4f,         // Attack Speed Modifier
              new Item.Properties()
                      .rarity(Rarity.RARE)
                      .tab(RiftThingsMod.RIFT_THINGS_TAB));
    }

}
