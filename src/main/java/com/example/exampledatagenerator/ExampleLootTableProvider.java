package com.example.exampledatagenerator;

import com.example.exampleblock.ExampleBlockMod;
import com.example.exampleitemcontainer.ExampleItemContainerMod;
import com.example.exampletileentity.ExampleTileEntityMod;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ExampleLootTableProvider extends LootTableProvider {
    public ExampleLootTableProvider(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables() {
        // Mod追加ブロックのルートテーブル
        return ImmutableList.of(Pair.of(ModBlockLootTables::new, LootParameterSets.BLOCK));
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationResults validationresults) {
        map.forEach((resourceLocation, lootTable) -> LootTableManager.func_215302_a(validationresults, resourceLocation, lootTable, map::get));
    }

    public class ModBlockLootTables extends BlockLootTables {
        @Override
        protected void addTables() {
            // Mod追加ブロックのルートテーブルを登録
            func_218492_c(ExampleBlockMod.ExampleBlocks.EXAMPLE_BLOCK);
            func_218492_c(ExampleTileEntityMod.ExampleTileEntityBlocks.EXAMPLE_TE_BLOCK);
            registerLootTable(ExampleItemContainerMod.ExampleItemContainerBlocks.EXAMPLE_ITEM_CONTAINER_BLOCK, BlockLootTables::func_218481_e);
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            // ルートテーブルを追加したmod追加ブロックのリストを返す
            ImmutableList.Builder<Block> builder = ImmutableList.builder();
            return builder
                    .add(ExampleBlockMod.ExampleBlocks.EXAMPLE_BLOCK)
                    .add(ExampleTileEntityMod.ExampleTileEntityBlocks.EXAMPLE_TE_BLOCK)
                    .add(ExampleItemContainerMod.ExampleItemContainerBlocks.EXAMPLE_ITEM_CONTAINER_BLOCK)
                    .build();
        }
    }
}
