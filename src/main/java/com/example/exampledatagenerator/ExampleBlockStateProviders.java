package com.example.exampledatagenerator;

import com.example.exampleblock.ExampleBlockMod;
import com.example.examplefluid.ExampleFluidMod;
import com.example.exampleitemcontainer.ExampleItemContainerMod;
import com.example.exampletileentity.ExampleTileEntityMod;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ModelFile;

import javax.annotation.Nonnull;

public class ExampleBlockStateProviders {
    // 今回はmodが複数あるのでここでプロバイダーをまとめて登録している
    public static void add(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        generator.addProvider(new ExampleBlockBlockStateProvider(generator, existingFileHelper));
        generator.addProvider(new ExampleTileEntityBlockStateProvider(generator, existingFileHelper));
        generator.addProvider(new ExampleFluidBlockStateProvider(generator, existingFileHelper));
        generator.addProvider(new ExampleItemContainerBlockStateProvider(generator, existingFileHelper));
    }

    public static class ExampleBlockBlockStateProvider extends ModBlockStateProvider {
        public ExampleBlockBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
            super(gen, ExampleBlockMod.MOD_ID, exFileHelper);
        }

        @Override
        protected void registerStatesAndModels() {
            simpleBlock(ExampleBlockMod.ExampleBlocks.EXAMPLE_BLOCK);
        }
    }

    public static class ExampleTileEntityBlockStateProvider extends ModBlockStateProvider {
        public ExampleTileEntityBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
            super(gen, ExampleTileEntityMod.MOD_ID, exFileHelper);
        }

        @Override
        protected void registerStatesAndModels() {
            simpleBlock(ExampleTileEntityMod.ExampleTileEntityBlocks.EXAMPLE_TE_BLOCK);
        }
    }

    public static class ExampleFluidBlockStateProvider extends ModBlockStateProvider {
        public ExampleFluidBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
            super(gen, ExampleFluidMod.MOD_ID, exFileHelper);
        }

        @Override
        protected void registerStatesAndModels() {
            simpleBlock(ExampleFluidMod.ExampleFluidBlocks.EXAMPLE_FLUID_BLOCK, new ModelFile.UncheckedModelFile("block/water"));
        }
    }

    public static class ExampleItemContainerBlockStateProvider extends ModBlockStateProvider {
        public ExampleItemContainerBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
            super(gen, ExampleItemContainerMod.MOD_ID, exFileHelper);
        }

        @Override
        protected void registerStatesAndModels() {
            simpleBlock(ExampleItemContainerMod.ExampleItemContainerBlocks.EXAMPLE_ITEM_CONTAINER_BLOCK);
        }
    }


    // データ生成ログにmod IDを表示するためだけの継承
    public static abstract class ModBlockStateProvider extends BlockStateProvider {
        public ModBlockStateProvider(DataGenerator gen, String modid, ExistingFileHelper exFileHelper) {
            super(gen, modid, exFileHelper);
        }

        @Nonnull
        @Override
        public String getName() {
            return super.getName() + ": " + modid;
        }
    }
}
