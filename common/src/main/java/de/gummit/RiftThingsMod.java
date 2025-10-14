package de.gummit;

import de.gummit.blocks.ModBlocks;
import de.gummit.dimension.ModDimensions;
import de.gummit.entity.ModEntities;
import de.gummit.items.ModItems;
import de.gummit.utils.ServerUtils;
import me.shedaniel.architectury.event.events.LifecycleEvent;
import me.shedaniel.architectury.registry.CreativeTabs;
import me.shedaniel.architectury.registry.DeferredRegister;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public final class RiftThingsMod {
    public static final String MOD_ID = "rift_things";

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registry.ITEM_KEY);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(MOD_ID, Registry.BLOCK_KEY);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(MOD_ID, Registry.ENTITY_TYPE_KEY);

    public static final ItemGroup RIFT_THINGS_TAB = CreativeTabs.create(new Identifier(MOD_ID, "rift_things_tab"),
            () -> new ItemStack(ModItems.RIFT_INGOT.get()));

    public static void init() {
        LifecycleEvent.SERVER_STARTING.register((server) -> ServerUtils.dedicatedServer = server);
        ModBlocks.init();
        ModItems.init();
        ModEntities.init();
        ModDimensions.init();
    }
}
