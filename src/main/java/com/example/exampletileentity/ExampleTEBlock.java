package com.example.exampletileentity;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class ExampleTEBlock extends ContainerBlock {
    protected ExampleTEBlock(Properties properties) {
        super(properties);
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        // ブロックを右クリックしたときの処理
        ExampleTileEntity tile = (ExampleTileEntity) worldIn.getTileEntity(pos);

        if (tile != null & !worldIn.isRemote) {
            // タイルエンティティーに記録されているクリックされた回数を更新
            tile.countUp();
            // プレイヤーにブロックが設置されてからクリックされた回数をメッセージで伝える
            int count = tile.getCount();
            player.sendMessage(new StringTextComponent("This block was clicked " + (count == 1 ? "once." : (count == 2 ? "twice." : count + " times."))));
            // NBTをクライアントへ反映させるためにブロックのアップデートを通知
            worldIn.notifyBlockUpdate(pos, state, state, 2);
        }

        return true;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new ExampleTileEntity();
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockRenderType getRenderType(BlockState state) {
        // ブロックの外見（今回はブロックのモデルを利用する）
        return BlockRenderType.MODEL;
    }
}
