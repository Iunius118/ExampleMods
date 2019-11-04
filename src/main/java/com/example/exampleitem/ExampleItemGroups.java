package com.example.exampleitem;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ExampleItemGroups {
    public static final ItemGroup EXAMPLE = new ItemGroup("examples") {
        @Override
        @OnlyIn(Dist.CLIENT)
        public ItemStack createIcon() {
            return new ItemStack(ExampleItemMod.ExampleItems.EXAMPLE_ITEM);
        }
    };
}
