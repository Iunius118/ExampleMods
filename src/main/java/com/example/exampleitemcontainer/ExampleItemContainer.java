package com.example.exampleitemcontainer;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

public class ExampleItemContainer extends Container {
    private final IInventory inventory;

    public static ExampleItemContainer createExampleItemContainer(int id, PlayerInventory playerInventory, IInventory tileEntity) {
        return new ExampleItemContainer(ExampleItemContainerMod.ExampleItemContainerTypes.EXAMPLE_ITEM_CONTAINER_TYPE, id, playerInventory, tileEntity);
    }

    public static ExampleItemContainer createExampleItemContainer(int id, PlayerInventory playerInventory) {
        int size = ExampleItemContainerTileEntity.INVENTORY_SIZE;
        return new ExampleItemContainer(ExampleItemContainerMod.ExampleItemContainerTypes.EXAMPLE_ITEM_CONTAINER_TYPE, id, playerInventory, new Inventory(size));
    }

    public ExampleItemContainer(@Nullable ContainerType<?> type, int id, PlayerInventory playerInventory, IInventory inventoryIn) {
        super(type, id);
        inventory = inventoryIn;
        assertInventorySize(inventory, inventory.getSizeInventory());
        inventory.openInventory(playerInventory.player);

        // インベントリのスロットを追加
        this.addSlot(new Slot(inventory, 0, 80, 18));

        // プレイヤー・インベントリのスロットを追加
        for(int l = 0; l < 3; ++l) {
            for(int j1 = 0; j1 < 9; ++j1) {
                this.addSlot(new Slot(playerInventory, j1 + l * 9 + 9, 8 + j1 * 18, 49 + l * 18));
            }
        }

        // プレイヤー・ホットバーのスロットを追加
        for(int i1 = 0; i1 < 9; ++i1) {
            this.addSlot(new Slot(playerInventory, i1, 8 + i1 * 18, 107));
        }
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return inventory.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        // スロットをシフトクリックしたときの処理
        ItemStack itemstack = ItemStack.EMPTY;
        int size = inventory.getSizeInventory();
        Slot slot = inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < size) {
                if (!this.mergeItemStack(itemstack1, size, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, size, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }

    @Override
    public void onContainerClosed(PlayerEntity playerIn) {
        super.onContainerClosed(playerIn);
        inventory.closeInventory(playerIn);
    }
}
