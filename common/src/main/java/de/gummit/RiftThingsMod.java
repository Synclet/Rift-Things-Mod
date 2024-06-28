package de.gummit;

import de.gummit.blocks.ModBlocks;
import de.gummit.dimension.ModDimensions;
import de.gummit.entity.ModEntities;
import de.gummit.items.ModItems;
import me.shedaniel.architectury.registry.CreativeTabs;
import me.shedaniel.architectury.registry.DeferredRegister;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public final class RiftThingsMod {
    public static final String MOD_ID = "rift_things";

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registry.ITEM_REGISTRY);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(MOD_ID, Registry.BLOCK_REGISTRY);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(MOD_ID, Registry.ENTITY_TYPE_REGISTRY);

    public static final CreativeModeTab RIFT_THINGS_TAB = CreativeTabs.create(new ResourceLocation(MOD_ID, "rift_things_tab"),
            () -> new ItemStack(ModItems.RIFT_INGOT.get()));

    public static void init() {
        ModItems.init();
        ModBlocks.init();
        ModEntities.init();
        ModDimensions.init();
    }
}
