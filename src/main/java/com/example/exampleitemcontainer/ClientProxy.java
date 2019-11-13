package com.example.exampleitemcontainer;

import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ClientProxy extends ServerProxy {
    @Override
    public void setup(final FMLCommonSetupEvent event) {
        // GUIの描画クラスを登録
        ScreenManager.registerFactory(ExampleItemContainerMod.ExampleItemContainerTypes.EXAMPLE_ITEM_CONTAINER_TYPE, ExampleItemContainerScreen::new);
    }
}
