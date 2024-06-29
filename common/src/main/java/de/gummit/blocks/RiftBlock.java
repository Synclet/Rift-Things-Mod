package de.gummit.blocks;

import me.shedaniel.architectury.registry.BlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.Nullable;

public class RiftBlock extends Block {

    public static final String BLOCK_ID = "rift_block";

    public RiftBlock() {
        super(BlockProperties.of(Material.BARRIER));
    }

    @Override
    public float getExplosionResistance() {
        return 3_600_000;
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos blockPos, BlockState blockState, Player player) {
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos blockPos, BlockState blockState, @Nullable BlockEntity blockEntity, ItemStack itemStack) {
    }

    //@Override
    //public void destroy(LevelAccessor levelAccessor, BlockPos blockPos, BlockState blockState) {
    //    levelAccessor.setBlock(blockPos, this.defaultBlockState(), 0);
    //}

    @Override
    public float getDestroyProgress(BlockState blockState, Player player, BlockGetter blockGetter, BlockPos blockPos) {
        return 0f;
    }
}
