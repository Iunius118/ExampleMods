package com.example.exampledistexecutor;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ExampleDistExecutorMod.MOD_ID)
public class ExampleDistExecutorMod {
    public static final String MOD_ID = "exampledistexecutor";
    // Proxyクラスの登録
    public static ServerProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    public static final Logger LOGGER = LogManager.getLogger();

    public ExampleDistExecutorMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // Proxy経由でメソッド呼び出し
        // 物理サーバー側ではServerProxyのメソッド、物理クライアント側ではClientProxyのメソッドが呼び出される
        // （物理サーバー側＝マルチプレイのサーバー側、物理クライアント側＝マルチプレイのクライアント側・シングルプレイ）
        proxy.setup();

        // 以下はproxyではないが、DistExecutorを使って物理サーバー側または物理クライアント側でのみ指定メソッドを呼び出す例
        // 物理サーバー側でのみserverSideMethodを呼び出す
        DistExecutor.runWhenOn(Dist.DEDICATED_SERVER, () -> ExampleDistExecutorMod::serverSideMethod);
        // 物理クライアント側でのみclientSideMethodを呼び出す
        DistExecutor.runWhenOn(Dist.CLIENT, () -> ExampleDistExecutorMod::clientSideMethod);
    }

    public static void serverSideMethod() {
        LOGGER.info("This is serverSideMethod");
    }

    public static void clientSideMethod() {
        LOGGER.info("This is clientSideMethod");
    }
}
