package de.gummit;

import de.gummit.blocks.ModBlocks;
import de.gummit.dimensions.ModDimensions;
import de.gummit.entity.ModEntities;
import de.gummit.items.ModItems;
import de.gummit.utils.ServerUtils;
import dev.architectury.event.events.common.LifecycleEvent;
import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.RegistryKeys;

public final class RiftThingsMod {
    public static final String MOD_ID = "rift_things";

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, RegistryKeys.ITEM);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(MOD_ID, RegistryKeys.BLOCK);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(MOD_ID, RegistryKeys.ENTITY_TYPE);
    public static final DeferredRegister<ItemGroup> TABS = DeferredRegister.create(MOD_ID, RegistryKeys.ITEM_GROUP);

    public static void init() {
        LifecycleEvent.SERVER_STARTING.register((server) -> ServerUtils.dedicatedServer = server);
        ModBlocks.init();
        ModItems.init();
        ModEntities.init();
        ModDimensions.init();
    }
}