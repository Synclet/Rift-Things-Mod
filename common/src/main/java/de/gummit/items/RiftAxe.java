package de.gummit.items;

import de.gummit.RiftThingsMod;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tiers;

public class RiftAxe extends AxeItem {

    public static final String ITEM_ID = "rift_axe";

    public RiftAxe() {
        super(Tiers.DIAMOND, // Tier
              5,             // Attack Damage Modifier
              -3f,         // Attack Speed Modifier
              new Properties()
                      .rarity(Rarity.RARE)
                      .tab(RiftThingsMod.RIFT_THINGS_TAB));
    }

}
