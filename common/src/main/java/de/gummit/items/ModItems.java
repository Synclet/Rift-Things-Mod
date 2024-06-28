package de.gummit.items;

import de.gummit.RiftThingsMod;
import me.shedaniel.architectury.registry.RegistrySupplier;

public class ModItems {

    public static final RegistrySupplier<RiftShard> RIFT_SHARD = RiftThingsMod.ITEMS.register(RiftShard.ITEM_ID, RiftShard::new);
    public static final RegistrySupplier<RiftIngot> RIFT_INGOT = RiftThingsMod.ITEMS.register(RiftIngot.ITEM_ID, RiftIngot::new);
    public static final RegistrySupplier<RiftPearl> RIFT_PEARL = RiftThingsMod.ITEMS.register(RiftPearl.ITEM_ID, RiftPearl::new);
    public static final RegistrySupplier<RiftSword> RIFT_SWORD = RiftThingsMod.ITEMS.register(RiftSword.ITEM_ID, RiftSword::new);
    public static final RegistrySupplier<RiftPickaxe> RIFT_PICKAXE = RiftThingsMod.ITEMS.register(RiftPickaxe.ITEM_ID, RiftPickaxe::new);
    public static final RegistrySupplier<RiftAxe> RIFT_AXE = RiftThingsMod.ITEMS.register(RiftAxe.ITEM_ID, RiftAxe::new);
    public static final RegistrySupplier<RiftShovel> RIFT_SHOVEL = RiftThingsMod.ITEMS.register(RiftShovel.ITEM_ID, RiftShovel::new);
    public static final RegistrySupplier<RiftHoe> RIFT_HOE = RiftThingsMod.ITEMS.register(RiftHoe.ITEM_ID, RiftHoe::new);
    public static final RegistrySupplier<RiftKey> RIFT_Key = RiftThingsMod.ITEMS.register(RiftKey.ITEM_ID, RiftKey::new);

    public static void init() {
        RiftThingsMod.ITEMS.register();
    }

}
