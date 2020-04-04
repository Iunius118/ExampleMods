package com.example.exampledatagenerator;

import com.example.exampleblock.ExampleBlockMod;
import com.example.examplefluid.ExampleFluidMod;
import com.example.exampleitem.ExampleItemMod;
import com.example.exampleitemcontainer.ExampleItemContainerMod;
import com.example.exampletileentity.ExampleTileEntityMod;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;

public class ExampleItemModelProviders {
    // 今回はmodが複数あるのでここでプロバイダーをまとめて登録している
    public static void add(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        generator.addProvider(new ExampleItemItemModelProvider(generator, existingFileHelper));
        generator.addProvider(new ExampleBlockItemModelProvider(generator, existingFileHelper));
        generator.addProvider(new ExampleTileEntityItemModelProvider(generator, existingFileHelper));
        generator.addProvider(new ExampleFluidItemModelProvider(generator, existingFileHelper));
        generator.addProvider(new ExampleItemContainerItemModelProvider(generator, existingFileHelper));
    }

    public static class ExampleItemItemModelProvider extends ModItemModelProvider {
        public ExampleItemItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
            super(generator, ExampleItemMod.MOD_ID, existingFileHelper);
        }

        @Override
        protected void registerModels() {
            ResourceLocation exampleItem = ExampleItemMod.ExampleItems.EXAMPLE_ITEM.getRegistryName();
            singleTexture(exampleItem.getPath(), mcLoc("item/generated"), "layer0", modLoc("item/" + exampleItem.getPath()));
        }
    }

    public static class ExampleBlockItemModelProvider extends ModItemModelProvider {
        public ExampleBlockItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
            super(generator, ExampleBlockMod.MOD_ID, existingFileHelper);
        }

        @Override
        protected void registerModels() {
            ResourceLocation exampleBlock = ExampleBlockMod.ExampleBlocks.EXAMPLE_BLOCK.getRegistryName();
            getBuilder(exampleBlock.getPath())
                    .parent(new ModelFile.UncheckedModelFile(modLoc("block/" + exampleBlock.getPath())));
        }
    }

    public static class ExampleTileEntityItemModelProvider extends ModItemModelProvider {
        public ExampleTileEntityItemModelProvider(DataGenerator generator,ExistingFileHelper existingFileHelper) {
            super(generator, ExampleTileEntityMod.MOD_ID, existingFileHelper);
        }

        @Override
        protected void registerModels() {
            ResourceLocation exampleTEBlock = ExampleTileEntityMod.ExampleTileEntityBlocks.EXAMPLE_TE_BLOCK.getRegistryName();
            getBuilder(exampleTEBlock.getPath())
                    .parent(new ModelFile.UncheckedModelFile(modLoc("block/" + exampleTEBlock.getPath())));
        }
    }

    public static class ExampleFluidItemModelProvider extends ModItemModelProvider {
        public ExampleFluidItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
            super(generator, ExampleFluidMod.MOD_ID, existingFileHelper);
        }

        @Override
        protected void registerModels() {
            // Forge式バケツアイテムモデルは現在未実装（Forge 1.14.4-28.2.2で実装）につきバニラモデルで代用（テクスチャは自前で用意）
            ResourceLocation exampleFluidBucketItem = ExampleFluidMod.ExampleFluidItems.EXAMPLE_FLUID_BUCKET.getRegistryName();
            singleTexture(exampleFluidBucketItem.getPath(), mcLoc("item/generated"), "layer0", modLoc("item/" + exampleFluidBucketItem.getPath()));
        }
    }

    public static class ExampleItemContainerItemModelProvider extends ModItemModelProvider {
        public ExampleItemContainerItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
            super(generator, ExampleItemContainerMod.MOD_ID, existingFileHelper);
        }

        @Override
        protected void registerModels() {
            ResourceLocation exampleItemContainerBlock = ExampleItemContainerMod.ExampleItemContainerBlocks.EXAMPLE_ITEM_CONTAINER_BLOCK.getRegistryName();
            getBuilder(exampleItemContainerBlock.getPath())
                    .parent(new ModelFile.UncheckedModelFile(modLoc("block/" + exampleItemContainerBlock.getPath())));
        }
    }

    // データ生成ログにmod IDを表示するためだけの継承
    public static abstract class ModItemModelProvider extends ItemModelProvider {
        public ModItemModelProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
            super(generator, modid, existingFileHelper);
        }

        @Override
        public String getName() {
            return "Item Models: " + modid;
        }
    }
}
