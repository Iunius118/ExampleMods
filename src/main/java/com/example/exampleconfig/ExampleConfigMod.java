package com.example.exampleconfig;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ExampleConfigMod.MOD_ID)
public class ExampleConfigMod {
    public static final String MOD_ID = "exampleconfig";

    public static final Logger LOGGER = LogManager.getLogger();

    public ExampleConfigMod() {
        // Configファイルのロード／変更イベントを受け取るために登録（任意）
        FMLJavaModLoadingContext.get().getModEventBus().register(ExampleConfig.class);

        // Configを登録
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ExampleConfig.commonSpec);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ExampleConfig.serverSpec);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ExampleConfig.clientSpec);
    }
}
