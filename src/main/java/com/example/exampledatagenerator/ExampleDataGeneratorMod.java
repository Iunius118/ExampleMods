package com.example.exampledatagenerator;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ExampleDataGeneratorMod.MOD_ID)
public class ExampleDataGeneratorMod {
    public static final String MOD_ID = "exampledatagenerator";

    public static final Logger LOGGER = LogManager.getLogger();

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventHandler {
        // runData実行時のみ発生するデータ生成用のイベント
        @SubscribeEvent
        public static void gatherData(final GatherDataEvent event) {
            DataGenerator generator = event.getGenerator();

            if (event.includeServer()) {
                // 実行パラメータに--serverオプションが含まれていた場合に実行される（デフォルトのrunDataは--allなので実行される）
                generator.addProvider(new ExampleRecipeProvider(generator));
                generator.addProvider(new ExampleLootTableProvider(generator));
                generator.addProvider(new ExampleFluidTagsProvider(generator));
            }

            if (event.includeClient()) {
                // 実行パラメータに--clientオプションが含まれていた場合に実行される（デフォルトのrunDataは--allなので実行される）
                ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
                ExampleBlockStateProviders.add(generator, existingFileHelper);
                ExampleItemModelProviders.add(generator, existingFileHelper);
                ExampleLanguageProviders.add(generator);
            }
        }
    }
}
