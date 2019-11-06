package com.example.exampledatagenerator;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ExampleDataGeneratorMod.MOD_ID)
public class ExampleDataGeneratorMod {
    public static final String MOD_ID = "exampledatagenerator";

    public static final Logger LOGGER = LogManager.getLogger();

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventHandler {
        // runData実行時のみ発生するデータ生成用のイベント
        @SubscribeEvent
        public static void gatherData(final GatherDataEvent event) {
            DataGenerator generator = event.getGenerator();

            if (event.includeServer()) {
                generator.addProvider(new ExampleDataProviders.Recipes(generator));
            }

            if (event.includeClient()) {
                // 今後こちらにはクライアント用のモデル関連やlangのProviderが追加される予定
            }
        }
    }
}
