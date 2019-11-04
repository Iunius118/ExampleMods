package com.example.examplemod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ExampleMod.MOD_ID)
public class ExampleMod {
    public static final String MOD_ID = "examplemod";

    public static final Logger LOGGER = LogManager.getLogger();

    public ExampleMod() {
        // Modロード時に呼び出される各種メソッドを登録
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // サーバー・クライアント共通の初期化処理
        LOGGER.info("HELLO FROM SETUP");
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // クライアント限定の初期化処理
        LOGGER.info("HELLO FROM DO_CLIENT_STUFF");
    }
}
