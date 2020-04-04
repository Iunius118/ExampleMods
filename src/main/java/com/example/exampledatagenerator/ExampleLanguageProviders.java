package com.example.exampledatagenerator;

import com.example.exampleblock.ExampleBlockMod;
import com.example.examplefluid.ExampleFluidMod;
import com.example.exampleitem.ExampleItemMod;
import com.example.exampleitemcontainer.ExampleItemContainerMod;
import com.example.exampletileentity.ExampleTileEntityMod;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ExampleLanguageProviders {
    // 今回はmodが複数あるのでここでプロバイダーをまとめて登録している
    public static void add(DataGenerator gen) {
        gen.addProvider(new ExampleItemLanguageProvider(gen));
        gen.addProvider(new ExampleItemJaJpLanguageProvider(gen));

        gen.addProvider(new ExampleBlockLanguageProvider(gen));
        gen.addProvider(new ExampleBlockJaJpLanguageProvider(gen));

        gen.addProvider(new ExampleTileEntityLanguageProvider(gen));
        gen.addProvider(new ExampleTileEntityJaJpLanguageProvider(gen));

        gen.addProvider(new ExampleFluidLanguageProvider(gen));
        gen.addProvider(new ExampleFluidJaJpLanguageProvider(gen));

        gen.addProvider(new ExampleItemContainerLanguageProvider(gen));
        gen.addProvider(new ExampleItemContainerJaJpLanguageProvider(gen));
    }

    public static final String KEY_ITEM_GROUP = "itemGroup.examples";
    public static final String KEY_EXAMPLE_ITEM_CONTAINER = "container.exampleitemcontainer.example_item_container";

    public static class ExampleItemLanguageProvider extends ModLanguageProviders {
        public ExampleItemLanguageProvider(DataGenerator gen) {
            super(gen, ExampleItemMod.MOD_ID, "en_us");
        }

        @Override
        protected void addTranslations() {
            add(KEY_ITEM_GROUP, "Example");
            add(ExampleItemMod.ExampleItems.EXAMPLE_ITEM, "Example Item");
        }
    }

    public static class ExampleItemJaJpLanguageProvider extends ModLanguageProviders {
        public ExampleItemJaJpLanguageProvider(DataGenerator gen) {
            super(gen, ExampleItemMod.MOD_ID, "ja_jp");
        }

        @Override
        protected void addTranslations() {
            add(KEY_ITEM_GROUP, "例の");
            add(ExampleItemMod.ExampleItems.EXAMPLE_ITEM, "例のアイテム");
        }
    }

    public static class ExampleBlockLanguageProvider extends ModLanguageProviders {
        public ExampleBlockLanguageProvider(DataGenerator gen) {
            super(gen, ExampleBlockMod.MOD_ID, "en_us");
        }

        @Override
        protected void addTranslations() {
            add(ExampleBlockMod.ExampleBlocks.EXAMPLE_BLOCK, "Example Block");
        }
    }

    public static class ExampleBlockJaJpLanguageProvider extends ModLanguageProviders {
        public ExampleBlockJaJpLanguageProvider(DataGenerator gen) {
            super(gen, ExampleBlockMod.MOD_ID, "ja_jp");
        }

        @Override
        protected void addTranslations() {
            add(ExampleBlockMod.ExampleBlocks.EXAMPLE_BLOCK, "例のブロック");
        }
    }

    public static class ExampleTileEntityLanguageProvider extends ModLanguageProviders {
        public ExampleTileEntityLanguageProvider(DataGenerator gen) {
            super(gen, ExampleTileEntityMod.MOD_ID, "en_us");
        }

        @Override
        protected void addTranslations() {
            add(ExampleTileEntityMod.ExampleTileEntityBlocks.EXAMPLE_TE_BLOCK, "Example TE Block");
        }
    }

    public static class ExampleTileEntityJaJpLanguageProvider extends ModLanguageProviders {
        public ExampleTileEntityJaJpLanguageProvider(DataGenerator gen) {
            super(gen, ExampleTileEntityMod.MOD_ID, "ja_jp");
        }

        @Override
        protected void addTranslations() {
            add(ExampleTileEntityMod.ExampleTileEntityBlocks.EXAMPLE_TE_BLOCK, "例のTEブロック");
        }
    }

    public static class ExampleFluidLanguageProvider extends ModLanguageProviders {
        public ExampleFluidLanguageProvider(DataGenerator gen) {
            super(gen, ExampleFluidMod.MOD_ID, "en_us");
        }

        @Override
        protected void addTranslations() {
            add(ExampleFluidMod.ExampleFluidItems.EXAMPLE_FLUID_BUCKET, "Example Fluid Bucket");
        }
    }

    public static class ExampleFluidJaJpLanguageProvider extends ModLanguageProviders {
        public ExampleFluidJaJpLanguageProvider(DataGenerator gen) {
            super(gen, ExampleFluidMod.MOD_ID, "ja_jp");
        }

        @Override
        protected void addTranslations() {
            add(ExampleFluidMod.ExampleFluidItems.EXAMPLE_FLUID_BUCKET, "例の流体入りバケツ");
        }
    }

    public static class ExampleItemContainerLanguageProvider extends ModLanguageProviders {
        public ExampleItemContainerLanguageProvider(DataGenerator gen) {
            super(gen, ExampleItemContainerMod.MOD_ID, "en_us");
        }

        @Override
        protected void addTranslations() {
            add(ExampleItemContainerMod.ExampleItemContainerBlocks.EXAMPLE_ITEM_CONTAINER_BLOCK, "Example Item Container Block");
            add(KEY_EXAMPLE_ITEM_CONTAINER, "Example Item Container");
        }
    }

    public static class ExampleItemContainerJaJpLanguageProvider extends ModLanguageProviders {
        public ExampleItemContainerJaJpLanguageProvider(DataGenerator gen) {
            super(gen, ExampleItemContainerMod.MOD_ID, "ja_jp");
        }

        @Override
        protected void addTranslations() {
            add(ExampleItemContainerMod.ExampleItemContainerBlocks.EXAMPLE_ITEM_CONTAINER_BLOCK, "例のアイテムコンテナブロック");
            add(KEY_EXAMPLE_ITEM_CONTAINER, "例のアイテムコンテナ");
        }
    }

    // データ生成ログにmod IDを表示するためだけの継承
    public static abstract class ModLanguageProviders extends LanguageProvider {
        public final String modid;

        public ModLanguageProviders(DataGenerator genIn, String modidIn, String localeIn) {
            super(genIn, modidIn, localeIn);
            this.modid = modidIn;
        }

        @Override
        public String getName() {
            return super.getName() + ": " + modid;
        }
    }
}
