package com.example.exampletileentity;

import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ExampleTileEntityRenderer extends TileEntityRenderer<ExampleTileEntity> {
    @Override
    public void render(ExampleTileEntity tileEntityIn, double x, double y, double z, float partialTicks, int destroyStage) {
        if (tileEntityIn != null) {
            renderExampleTileEntityAt(tileEntityIn, x, y, z, partialTicks, destroyStage);
        }
    }

    private void renderExampleTileEntityAt(ExampleTileEntity tileEntityIn, double x, double y, double z, float partialTicks, int destroyStage) {
        int count = tileEntityIn.getCount();
        // ブロックがクリックされた回数を名札として描画する
        if (count != 0) {
            setLightmapDisabled(true);
            GameRenderer.drawNameplate(getFontRenderer(), Integer.toString(count), (float) x + 0.5F, (float) y + 1.25F, (float) z + 0.5F, 0, rendererDispatcher.renderInfo.getYaw(), rendererDispatcher.renderInfo.getPitch(), false);
            setLightmapDisabled(false);
        }
    }

    // 簡略化のためここでレンダラーを登録（実際にはproxy等でやった方がいいかも）
    public static void bindTileEntity() {
        ClientRegistry.bindTileEntitySpecialRenderer(ExampleTileEntity.class, new ExampleTileEntityRenderer());
    }
}
