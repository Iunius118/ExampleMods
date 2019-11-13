package com.example.exampleitemcontainer;

import com.example.exampleitem.ExampleItemGroups;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ObjectHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

@Mod(ExampleItemContainerMod.MOD_ID)
public class ExampleItemContainerMod {
    public static final String MOD_ID = "exampleitemcontainer";
    public static final String EXAMPLE_ITEM_CONTAINER_BLOCK_ID = "example_item_container_block";
    public static final String EXAMPLE_ITEM_CONTAINER_TE_ID = "example_item_container_te";
    public static final String EXAMPLE_ITEM_CONTAINER_TYPE_ID = "example_item_container_type";
    // Proxyクラスの登録
    public static ServerProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    public static final Logger LOGGER = LogManager.getLogger();

    public ExampleItemContainerMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // サーバー・クライアント共通の初期化処理
        proxy.setup(event);
        // コンテナが登録されているか確認
        LOGGER.info("EXAMPLE ITEM CONTAINER >> {}", ExampleItemContainerTypes.EXAMPLE_ITEM_CONTAINER_TYPE.getRegistryName());
    }

    // 登録したブロックのインスタンスが自動的に代入されるObjectHolder
    @ObjectHolder(MOD_ID)
    public static class ExampleItemContainerBlocks {
        public static final Block EXAMPLE_ITEM_CONTAINER_BLOCK = null;
    }

    // 登録したTileEntityTypeのインスタンスが自動的に代入されるObjectHolder
    @ObjectHolder(MOD_ID)
    public static class ExampleItemContainerTETypes {
        public static final TileEntityType<ExampleItemContainerTileEntity> EXAMPLE_ITEM_CONTAINER_TE = null;
    }

    // 登録したItemContainerTypeのインスタンスが自動的に代入されるObjectHolder
    @ObjectHolder(MOD_ID)
    public static class ExampleItemContainerTypes {
        public static final ContainerType<ExampleItemContainer> EXAMPLE_ITEM_CONTAINER_TYPE = null;
    }

    // アイテムやブロックなどの登録を行うクラス
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        // ブロック登録イベント
        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> event) {
            LOGGER.info("REGISTER_BLOCKS");
            event.getRegistry().register(new ExampleItemContainerBlock(Block.Properties.create(Material.EARTH)).setRegistryName(EXAMPLE_ITEM_CONTAINER_BLOCK_ID));
        }

        // アイテム登録イベント
        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event) {
            LOGGER.info("REGISTER_ITEMS");
            event.getRegistry().register(new BlockItem(ExampleItemContainerBlocks.EXAMPLE_ITEM_CONTAINER_BLOCK, new Item.Properties().group(ExampleItemGroups.EXAMPLE)).setRegistryName(EXAMPLE_ITEM_CONTAINER_BLOCK_ID));
        }

        // タイルエンティティー登録イベント
        @SubscribeEvent
        public static void registerTE(final RegistryEvent.Register<TileEntityType<?>> event) {
            LOGGER.info("REGISTER_TE");
            event.getRegistry().register(TileEntityType.Builder.create((Supplier<TileEntity>) ExampleItemContainerTileEntity::new, ExampleItemContainerBlocks.EXAMPLE_ITEM_CONTAINER_BLOCK).build(null).setRegistryName(EXAMPLE_ITEM_CONTAINER_TE_ID));
        }

        // コンテナ登録イベント
        @SubscribeEvent
        public static void registerContainer(final RegistryEvent.Register<ContainerType<?>> event) {
            LOGGER.info("REGISTER_CONTAINER");
            event.getRegistry().register(new ContainerType<>(ExampleItemContainer::createExampleItemContainer).setRegistryName(EXAMPLE_ITEM_CONTAINER_TYPE_ID));
        }
    }
}
