package de.gummit.items;

import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Rarity;

public class RiftPickaxe extends PickaxeItem {

    public static final String ITEM_ID = "rift_pickaxe";

    public RiftPickaxe() {
        super(ToolMaterials.DIAMOND, // Tier
              1,             // Attack Damage Modifier
              -2.8f,         // Attack Speed Modifier
              new Settings()
                      .rarity(Rarity.RARE)
                      .arch$tab(ModItems.RIFT_THINGS_TAB));
    }

}