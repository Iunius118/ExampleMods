package com.example.exampleitemcontainer;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class ExampleItemContainerBlock extends ContainerBlock {
    protected ExampleItemContainerBlock(Properties builder) {
        super(builder);
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        // ブロックを右クリックしたときの処理
        if (!worldIn.isRemote) {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof ExampleItemContainerTileEntity) {
                // ExampleItemContainerのGUIを開く
                player.openContainer((ExampleItemContainerTileEntity) tileentity);
            }
        }

        return true;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        // ブロックが設置されたときの処理
        if (stack.hasDisplayName()) {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof ExampleItemContainerTileEntity) {
                // このスタックの表示名が変更されている場合はその名前をGUIに反映
                ((ExampleItemContainerTileEntity) tileentity).setCustomName(stack.getDisplayName());
            }
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        // ブロックが置換されたときの処理
        if (state.getBlock() != newState.getBlock()) {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof IInventory) {
                // インベントリのアイテムをドロップする
                InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory) tileentity);
                worldIn.updateComparatorOutputLevel(pos, this);
            }

            super.onReplaced(state, worldIn, pos, newState, isMoving);
        }
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new ExampleItemContainerTileEntity();
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockRenderType getRenderType(BlockState state) {
        // ブロックの外見（今回はブロックのモデルを利用する）
        return BlockRenderType.MODEL;
    }
}
