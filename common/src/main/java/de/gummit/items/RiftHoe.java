package de.gummit.items;

import de.gummit.RiftThingsMod;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Rarity;

public class RiftHoe extends HoeItem {

    public static final String ITEM_ID = "rift_hoe";

    public RiftHoe() {
        super(ToolMaterials.DIAMOND, // Tier
              -3,             // Attack Damage Modifier
              0,         // Attack Speed Modifier
              new Settings()
                      .rarity(Rarity.RARE)
                      .group(RiftThingsMod.RIFT_THINGS_TAB));
    }

}
