package de.gummit.items;

import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Rarity;

public class RiftShovel extends ShovelItem {

    public static final String ITEM_ID = "rift_shovel";

    public RiftShovel() {
        super(ToolMaterials.DIAMOND, // Tier
              1.5f,             // Attack Damage Modifier
              -3f,         // Attack Speed Modifier
              new Settings()
                      .rarity(Rarity.RARE)
                      .arch$tab(ModItems.RIFT_THINGS_TAB));
    }

}