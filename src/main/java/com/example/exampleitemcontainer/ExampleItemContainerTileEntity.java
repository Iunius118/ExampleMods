package com.example.exampleitemcontainer;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class ExampleItemContainerTileEntity extends LockableLootTileEntity {
    public static final int INVENTORY_SIZE = 1;
    private NonNullList<ItemStack> items = NonNullList.withSize(INVENTORY_SIZE, ItemStack.EMPTY);

    protected ExampleItemContainerTileEntity(TileEntityType<?> typeIn) {
        super(typeIn);
    }

    public ExampleItemContainerTileEntity() {
        this(ExampleItemContainerMod.ExampleItemContainerTETypes.EXAMPLE_ITEM_CONTAINER_TE);
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> itemsIn) {
        items = itemsIn;
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container.exampleitemcontainer.example_item_container");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        // GUI表示のためのContainerを返す
        return ExampleItemContainer.createExampleItemContainer(id, player, this);
    }

    @Override
    public int getSizeInventory() {
        return INVENTORY_SIZE;
    }

    @Override
    public boolean isEmpty() {
        for(ItemStack itemstack : items) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    /*
     * NBTの読み書き
     * インベントリのアイテムを読み込み/書き出し
     * */
    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        items = NonNullList.withSize(getSizeInventory(), ItemStack.EMPTY);

        if (!checkLootAndRead(compound))
        {
            ItemStackHelper.loadAllItems(compound, items);
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);

        if (!checkLootAndWrite(compound)) {
            ItemStackHelper.saveAllItems(compound, items);
        }

        return compound;
    }

    /*
     * インベントリの内容はGUIによって同期されるため、こちらでパケットを扱う必要はない
     * */
}
