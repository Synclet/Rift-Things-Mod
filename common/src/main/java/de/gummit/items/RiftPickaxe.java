package de.gummit.items;

import de.gummit.RiftThingsMod;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tiers;

public class RiftPickaxe extends PickaxeItem {

    public static final String ITEM_ID = "rift_pickaxe";

    public RiftPickaxe() {
        super(Tiers.DIAMOND, // Tier
              1,             // Attack Damage Modifier
              -2.8f,         // Attack Speed Modifier
              new Properties()
                      .rarity(Rarity.RARE)
                      .tab(RiftThingsMod.RIFT_THINGS_TAB));
    }

}
