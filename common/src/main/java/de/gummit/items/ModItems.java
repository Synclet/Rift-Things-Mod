package de.gummit.items;

import de.gummit.RiftThingsMod;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public class ModItems {

    public static final RegistrySupplier<ItemGroup> RIFT_THINGS_TAB = RiftThingsMod.TABS.register("rift_things_tab", () -> CreativeTabRegistry.create(
            builder -> builder
                    .icon(() -> new ItemStack(ModItems.RIFT_INGOT.get()))
                    //.displayName(Text.translatable("itemGroup.rift_things.rift_things_tab"))
                    .displayName(Text.of("Rift Things"))
                    .build()
    ));

    public static final RegistrySupplier<RiftShard> RIFT_SHARD = RiftThingsMod.ITEMS.register(RiftShard.ITEM_ID, RiftShard::new);
    public static final RegistrySupplier<RiftIngot> RIFT_INGOT = RiftThingsMod.ITEMS.register(RiftIngot.ITEM_ID, RiftIngot::new);
    public static final RegistrySupplier<RiftPearl> RIFT_PEARL = RiftThingsMod.ITEMS.register(RiftPearl.ITEM_ID, RiftPearl::new);
    public static final RegistrySupplier<RiftSword> RIFT_SWORD = RiftThingsMod.ITEMS.register(RiftSword.ITEM_ID, RiftSword::new);
    public static final RegistrySupplier<RiftPickaxe> RIFT_PICKAXE = RiftThingsMod.ITEMS.register(RiftPickaxe.ITEM_ID, RiftPickaxe::new);
    public static final RegistrySupplier<RiftAxe> RIFT_AXE = RiftThingsMod.ITEMS.register(RiftAxe.ITEM_ID, RiftAxe::new);
    public static final RegistrySupplier<RiftShovel> RIFT_SHOVEL = RiftThingsMod.ITEMS.register(RiftShovel.ITEM_ID, RiftShovel::new);
    public static final RegistrySupplier<RiftHoe> RIFT_HOE = RiftThingsMod.ITEMS.register(RiftHoe.ITEM_ID, RiftHoe::new);
    public static final RegistrySupplier<RiftKey> RIFT_KEY = RiftThingsMod.ITEMS.register(RiftKey.ITEM_ID, RiftKey::new);

    public static final RegistrySupplier<RiftBlockItem> RIFT_BLOCK = RiftThingsMod.ITEMS.register(RiftBlockItem.ITEM_ID, RiftBlockItem::new);
    public static final RegistrySupplier<RiftCoreBlockItem> RIFT_CORE = RiftThingsMod.ITEMS.register(RiftCoreBlockItem.ITEM_ID, RiftCoreBlockItem::new);

    public static void init() {
        RiftThingsMod.ITEMS.register();
        RiftThingsMod.TABS.register();
    }

}