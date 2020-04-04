package com.example.exampledatagenerator;

import com.example.examplefluid.ExampleFluidMod;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.FluidTagsProvider;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ResourceLocation;

public class ExampleFluidTagsProvider extends FluidTagsProvider {
    public ExampleFluidTagsProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerTags() {
        // Mod追加流体にバニラの水の性質を与える
        getBuilder(new FluidTags.Wrapper(new ResourceLocation("water")))
                .add(ExampleFluidMod.ExampleFluids.EXAMPLE_FLUID)
                .add(ExampleFluidMod.ExampleFluids.EXAMPLE_FLUID_FLOWING);
    }

    @Override
    public String getName() {
        return super.getName() + ": " + ExampleDataGeneratorMod.MOD_ID;
    }
}
