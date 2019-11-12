package com.example.exampletileentity;

import com.example.exampleitem.ExampleItemGroups;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ObjectHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

@Mod(ExampleTileEntityMod.MOD_ID)
public class ExampleTileEntityMod {
    public static final String MOD_ID = "exampletileentity";
    public static final String EXAMPLE_TE_BLOCK_ID = "example_te_block";
    public static final String EXAMPLE_TE_ID = "example_te";

    public static final Logger LOGGER = LogManager.getLogger();

    public ExampleTileEntityMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // サーバー・クライアント共通の初期化処理
        // タイルエンティティーが登録されているか確認
        LOGGER.info("EXAMPLE TILE ENTITY >> {}", ExampleTileEntityTypes.EXAMPLE_TE.getRegistryName());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // クライアント限定の初期化処理
        // TileEntityにTileEntityRendererを登録
        // ・クライアント限定の処理なのでサーバー側からは見えないところで行う
        // ・ここでは簡略化のためレンダラークラスのメソッドで登録しているが、実際にはproxy等でやった方がいいかも
        ExampleTileEntityRenderer.bindTileEntity();
    }

    // 登録したブロックのインスタンスが自動的に代入されるObjectHolder
    @ObjectHolder(MOD_ID)
    public static class ExampleTileEntityBlocks {
        public static final Block EXAMPLE_TE_BLOCK = null;
    }

    // 登録したTileEntityTypeのインスタンスが自動的に代入されるObjectHolder
    @ObjectHolder(MOD_ID)
    public static class ExampleTileEntityTypes {
        public static final TileEntityType<?> EXAMPLE_TE = null;
    }

    // アイテムやブロックなどの登録を行うクラス
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        // ブロック登録イベント
        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> event) {
            LOGGER.info("REGISTER_BLOCKS");
            event.getRegistry().register(new ExampleTEBlock(Block.Properties.create(Material.EARTH)).setRegistryName(EXAMPLE_TE_BLOCK_ID));
        }

        // アイテム登録イベント
        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event) {
            LOGGER.info("REGISTER_ITEMS");
            event.getRegistry().register(new BlockItem(ExampleTileEntityMod.ExampleTileEntityBlocks.EXAMPLE_TE_BLOCK, new Item.Properties().group(ExampleItemGroups.EXAMPLE)).setRegistryName(EXAMPLE_TE_BLOCK_ID));
        }

        // タイルエンティティー登録イベント
        @SubscribeEvent
        public static void registerTE(final RegistryEvent.Register<TileEntityType<?>> event) {
            LOGGER.info("REGISTER_TE");
            event.getRegistry().register(TileEntityType.Builder.create((Supplier<TileEntity>) ExampleTileEntity::new, ExampleTileEntityBlocks.EXAMPLE_TE_BLOCK).build(null).setRegistryName(EXAMPLE_TE_ID));
        }
    }
}
