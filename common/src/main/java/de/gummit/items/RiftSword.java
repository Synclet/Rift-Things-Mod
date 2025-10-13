package de.gummit.items;

import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Rarity;

public class RiftSword extends SwordItem {

    public static final String ITEM_ID = "rift_sword";

    public RiftSword() {
        super(ToolMaterials.DIAMOND, // Tier
              3,             // Attack Damage Modifier
              -2.4f,         // Attack Speed Modifier
              new Item.Settings()
                      .rarity(Rarity.RARE)
                      .arch$tab(ModItems.RIFT_THINGS_TAB));
    }

}