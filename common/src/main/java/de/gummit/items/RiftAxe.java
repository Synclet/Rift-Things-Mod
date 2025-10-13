package de.gummit.items;

import net.minecraft.item.AxeItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Rarity;

public class RiftAxe extends AxeItem {

    public static final String ITEM_ID = "rift_axe";

    public RiftAxe() {
        super(ToolMaterials.DIAMOND, // Tier
              5,             // Attack Damage Modifier
              -3f,         // Attack Speed Modifier
              new Settings()
                      .rarity(Rarity.RARE)
                      .arch$tab(ModItems.RIFT_THINGS_TAB));
    }

}