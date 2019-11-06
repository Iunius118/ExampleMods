package com.example.exampleitem;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ObjectHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ExampleItemMod.MOD_ID)
public class ExampleItemMod {
    public static final String MOD_ID = "exampleitem";
    public static final String EXAMPLE_ITEM_ID = "example_item";

    public static final Logger LOGGER = LogManager.getLogger();

    public ExampleItemMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // サーバー・クライアント共通の初期化処理
        // アイテムが登録されているか確認
        LOGGER.info("EXAMPLE ITEM >> {}", ExampleItems.EXAMPLE_ITEM.getRegistryName());
    }

    // 登録したアイテムのインスタンスが自動的に代入されるObjectHolder
    @ObjectHolder(MOD_ID)
    public static class ExampleItems {
        public static final Item EXAMPLE_ITEM = null;
    }

    // アイテムやブロックなどの登録を行うクラス
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        // アイテム登録イベント
        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event) {
            LOGGER.info("REGISTER_ITEMS");
            // アイテムを登録
            // ItemGroup（クリエイティブタブ等に使用）には独自のExampleItemGroups.EXAMPLEを指定。バニラのものを指定するにはItemGroup.～
            event.getRegistry().register(new ExampleItem(new Item.Properties().group(ExampleItemGroups.EXAMPLE)).setRegistryName(EXAMPLE_ITEM_ID));
        }
    }
}
