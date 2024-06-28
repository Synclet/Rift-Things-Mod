package de.gummit.blocks;

import me.shedaniel.architectury.registry.BlockProperties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;

public class RiftCore extends Block {

    public static final String BLOCK_ID = "rift_core";

    public RiftCore() {
        super(BlockProperties.of(Material.BARRIER));
    }
}
