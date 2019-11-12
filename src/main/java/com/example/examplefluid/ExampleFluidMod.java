package com.example.examplefluid;

import com.example.exampleitem.ExampleItemGroups;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ObjectHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ExampleFluidMod.MOD_ID)
public class ExampleFluidMod {
    public static final String MOD_ID = "examplefluid";
    public static final String EXAMPLE_FLUID_ID = "example_fluid";
    public static final String EXAMPLE_FLUID_FLOWING_ID = "example_fluid_flowing";
    public static final String EXAMPLE_FLUID_BLOCK_ID = "example_fluid_block";
    public static final String EXAMPLE_FLUID_BUCKET_ID = "example_fluid_bucket";

    public static final Logger LOGGER = LogManager.getLogger();

    public ExampleFluidMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // サーバー・クライアント共通の初期化処理
        // 流体が登録されているか確認
        LOGGER.info("EXAMPLE FLUID >> {}", ExampleFluids.EXAMPLE_FLUID.getRegistryName());
    }

    // 登録したブロックのインスタンスが自動的に代入されるObjectHolder
    @ObjectHolder(MOD_ID)
    public static class ExampleFluidBlocks {
        public static final FlowingFluidBlock EXAMPLE_FLUID_BLOCK = null;
    }

    // 登録したアイテムのインスタンスが自動的に代入されるObjectHolder
    @ObjectHolder(MOD_ID)
    public static class ExampleFluidItems {
        public static final Item EXAMPLE_FLUID_BUCKET = null;
    }

    // 登録した流体のインスタンスが自動的に代入されるObjectHolder
    @ObjectHolder(MOD_ID)
    public static class ExampleFluids {
        public static final FlowingFluid EXAMPLE_FLUID = null;
        public static final FlowingFluid EXAMPLE_FLUID_FLOWING = null;
    }

    // アイテムやブロックなどの登録を行うクラス
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        // ブロック登録イベント
        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> event) {
            LOGGER.info("REGISTER_BLOCKS");
            // 流体ブロックを登録
            event.getRegistry().register(new FlowingFluidBlock(() -> ExampleFluids.EXAMPLE_FLUID, Block.Properties.create(Material.WATER).doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops()).setRegistryName(EXAMPLE_FLUID_BLOCK_ID));
        }

        // アイテム登録イベント
        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event) {
            LOGGER.info("REGISTER_ITEMS");
            // 流体のバケツアイテムを登録
            event.getRegistry().register(new BucketItem(() -> ExampleFluids.EXAMPLE_FLUID, new Item.Properties().containerItem(Items.BUCKET).maxStackSize(1).group(ExampleItemGroups.EXAMPLE)).setRegistryName(EXAMPLE_FLUID_BUCKET_ID));
        }

        // 流体登録イベント
        @SubscribeEvent
        public static void registerFluids(final RegistryEvent.Register<Fluid> event) {
            LOGGER.info("REGISTER_FLUIDS");
            // 流体のプロパティ
            ForgeFlowingFluid.Properties example_fluid_properties = new ForgeFlowingFluid.Properties(
                    () -> ExampleFluids.EXAMPLE_FLUID,
                    () -> ExampleFluids.EXAMPLE_FLUID_FLOWING,
                    FluidAttributes.builder(new ResourceLocation("block/water_still"), new ResourceLocation("block/water_flow")).color(0x7FFFDD00))
                    .block(() -> ExampleFluidBlocks.EXAMPLE_FLUID_BLOCK)
                    .bucket(() -> ExampleFluidItems.EXAMPLE_FLUID_BUCKET);
            // 流体の流体源と流れる流体を登録
            event.getRegistry().registerAll(
                    new ForgeFlowingFluid.Source(example_fluid_properties).setRegistryName(EXAMPLE_FLUID_ID),
                    new ForgeFlowingFluid.Flowing(example_fluid_properties).setRegistryName(EXAMPLE_FLUID_FLOWING_ID)
            );
        }
    }
}
