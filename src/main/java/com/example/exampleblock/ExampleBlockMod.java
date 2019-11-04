package com.example.exampleblock;

import com.example.exampleitem.ExampleItemGroups;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ObjectHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ExampleBlockMod.MOD_ID)
public class ExampleBlockMod {
    public static final String MOD_ID = "exampleblock";
    public static final String EXAMPLE_BLOCK_ID = "example_block";

    public static final Logger LOGGER = LogManager.getLogger();

    public ExampleBlockMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // サーバー・クライアント共通の初期化処理
        LOGGER.info("HELLO FROM SETUP");
        // ブロックが登録されているか確認
        LOGGER.info("EXAMPLE BLOCK >> {}", ExampleBlocks.EXAMPLE_BLOCK.getRegistryName());
    }

    // 登録したブロックのインスタンスが自動的に代入されるObjectHolder
    @ObjectHolder(MOD_ID)
    public static class ExampleBlocks {
        public static final Block EXAMPLE_BLOCK = null;
    }

    // アイテムやブロックなどの登録を行うクラス
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        // ブロック登録イベント
        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> event) {
            LOGGER.info("REGISTER_BLOCKS");
            // ブロックを登録
            event.getRegistry().register(new ExampleBlock(Block.Properties.create(Material.EARTH)).setRegistryName(EXAMPLE_BLOCK_ID));
        }

        // アイテム登録イベント
        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event) {
            LOGGER.info("REGISTER_ITEMS");
            // アイテム化したブロックも忘れずに登録
            // ItemGroup（クリエイティブタブ等に使用）には独自のExampleItemGroups.EXAMPLEを指定。バニラのものを指定するにはItemGroup.～
            event.getRegistry().register(new BlockItem(ExampleBlocks.EXAMPLE_BLOCK, new Item.Properties().group(ExampleItemGroups.EXAMPLE)).setRegistryName(EXAMPLE_BLOCK_ID));
        }
    }
}
