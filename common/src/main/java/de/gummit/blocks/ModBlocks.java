package de.gummit.blocks;

import de.gummit.RiftThingsMod;
import dev.architectury.registry.registries.RegistrySupplier;

public class ModBlocks {

    public static final RegistrySupplier<RiftBlock> RIFT_BLOCK = RiftThingsMod.BLOCKS.register(RiftBlock.BLOCK_ID, RiftBlock::new);
    public static final RegistrySupplier<RiftCore> RIFT_CORE = RiftThingsMod.BLOCKS.register(RiftCore.BLOCK_ID, RiftCore::new);

    public static void init() {
        RiftThingsMod.BLOCKS.register();
    }

}