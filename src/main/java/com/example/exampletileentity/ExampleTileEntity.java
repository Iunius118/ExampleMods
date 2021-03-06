package com.example.exampletileentity;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

import javax.annotation.Nullable;

public class ExampleTileEntity extends TileEntity {
    private int count = 0;

    public ExampleTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public ExampleTileEntity() {
        this(ExampleTileEntityMod.ExampleTileEntityTypes.EXAMPLE_TE);
    }

    /*
     * とりあえずタイルエンティティーに適当な仕事を与える
     * */
    public void countUp() {
        count++;
    }

    public int getCount() {
        return count;
    }

    /*
     * NBTの読み書き
     * */
    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        count = compound.getInt("count");
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        compound.putInt("count", count);
        return compound;
    }

    /*
     * パケットの送信・受信処理
     * サーバー側のデータをクライアント側に反映させる
     * */
    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(getPos(), -1, getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        handleUpdateTag(pkt.getNbtCompound());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        ExampleTileEntityMod.LOGGER.info("SEND UPDATE TAG at {}", getPos());
        CompoundNBT tag = new CompoundNBT();
        write(tag);
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundNBT tag) {
        ExampleTileEntityMod.LOGGER.info("RECEIVED UPDATE TAG at {}", getPos());
        read(tag);
    }
}
