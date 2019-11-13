package com.example.exampledatagenerator;

import com.example.exampleblock.ExampleBlockMod;
import com.example.examplefluid.ExampleFluidMod;
import com.example.exampleitem.ExampleItemMod;
import com.example.exampleitemcontainer.ExampleItemContainerMod;
import com.example.exampletileentity.ExampleTileEntityMod;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class ExampleDataProviders {
    // レシピ生成
    public static class Recipes extends RecipeProvider implements IConditionBuilder {
        public Recipes(DataGenerator generatorIn) {
            super(generatorIn);
        }

        @Override
        public void registerRecipes(Consumer<IFinishedRecipe> consumer) {
            // 定形レシピ：土→ダイヤ
            ShapedRecipeBuilder.shapedRecipe(Blocks.DIAMOND_BLOCK, 1)
                    .patternLine("###")
                    .patternLine("###")
                    .patternLine("###")
                    .key('#', Blocks.DIRT)
                    .addCriterion("has_dirt", hasItem(Blocks.DIRT))
                    .build(consumer, new ResourceLocation(ExampleDataGeneratorMod.MOD_ID, "dirt_to_diamond"));

            // 不定形レシピ：→Example Item
            ShapelessRecipeBuilder.shapelessRecipe(ExampleItemMod.ExampleItems.EXAMPLE_ITEM, 1)
                    .addIngredient(ItemTags.PLANKS) // バニラの木材タグ
                    .addIngredient(Tags.Items.EGGS) // Forgeの卵タグ
                    .addCriterion("has_plank", hasItem(ItemTags.PLANKS))
                    .build(consumer, ExampleItemMod.ExampleItems.EXAMPLE_ITEM.getRegistryName());

            // 定形レシピ：→Example Block
            ShapedRecipeBuilder.shapedRecipe(ExampleBlockMod.ExampleBlocks.EXAMPLE_BLOCK, 1)
                    .patternLine(" C ")
                    .patternLine("CeC")
                    .patternLine(" C ")
                    .key('e', ExampleItemMod.ExampleItems.EXAMPLE_ITEM)
                    .key('C', Tags.Items.COBBLESTONE)   // Forgeの丸石タグ
                    .addCriterion("has_example_item", hasItem(ExampleItemMod.ExampleItems.EXAMPLE_ITEM))
                    .build(consumer, ExampleBlockMod.ExampleBlocks.EXAMPLE_BLOCK.getRegistryName());

            // 不定形レシピ：→Example TE Block
            ShapelessRecipeBuilder.shapelessRecipe(ExampleTileEntityMod.ExampleTileEntityBlocks.EXAMPLE_TE_BLOCK, 1)
                    .addIngredient(ExampleBlockMod.ExampleBlocks.EXAMPLE_BLOCK)
                    .addIngredient(ItemTags.BUTTONS)    // バニラのボタンタグ
                    .addCriterion("has_example_block", hasItem(ExampleBlockMod.ExampleBlocks.EXAMPLE_BLOCK))
                    .build(consumer, ExampleTileEntityMod.ExampleTileEntityBlocks.EXAMPLE_TE_BLOCK.getRegistryName());

            // 不定形レシピ：→Example Fluid Bucket
            ShapelessRecipeBuilder.shapelessRecipe(ExampleFluidMod.ExampleFluidItems.EXAMPLE_FLUID_BUCKET, 1)
                    .addIngredient(Items.WATER_BUCKET)
                    .addIngredient(ExampleItemMod.ExampleItems.EXAMPLE_ITEM)
                    .addCriterion("has_example_item", hasItem(ExampleItemMod.ExampleItems.EXAMPLE_ITEM))
                    .build(consumer, ExampleFluidMod.ExampleFluidItems.EXAMPLE_FLUID_BUCKET.getRegistryName());

            // 不定形レシピ：→Example Block
            ShapelessRecipeBuilder.shapelessRecipe(ExampleBlockMod.ExampleBlocks.EXAMPLE_BLOCK, 1)
                    .addIngredient(ExampleFluidMod.ExampleFluidItems.EXAMPLE_FLUID_BUCKET)
                    .addIngredient(Tags.Items.COBBLESTONE)   // Forgeの丸石タグ
                    .addCriterion("has_example_fluid_bucket", hasItem(ExampleFluidMod.ExampleFluidItems.EXAMPLE_FLUID_BUCKET))
                    .build(consumer, ExampleFluidMod.MOD_ID + ":example_block_from_fluid_bucket");

            // 定形レシピ：→Example Item Container Block
            ShapedRecipeBuilder.shapedRecipe(ExampleItemContainerMod.ExampleItemContainerBlocks.EXAMPLE_ITEM_CONTAINER_BLOCK, 1)
                    .patternLine("CeC")
                    .patternLine("C C")
                    .patternLine("CCC")
                    .key('e', ExampleItemMod.ExampleItems.EXAMPLE_ITEM)
                    .key('C', Tags.Items.COBBLESTONE)   // Forgeの丸石タグ
                    .addCriterion("has_example_item", hasItem(ExampleItemMod.ExampleItems.EXAMPLE_ITEM))
                    .build(consumer, ExampleItemContainerMod.ExampleItemContainerBlocks.EXAMPLE_ITEM_CONTAINER_BLOCK.getRegistryName());

            // 精錬レシピ（かまど）：土→ダイヤ
            CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(Blocks.DIRT), Items.DIAMOND, 1.0F, 200)
                    .addCriterion("has_dirt", hasItem(Blocks.DIRT))
                    .build(consumer, new ResourceLocation(ExampleDataGeneratorMod.MOD_ID, "diamond_from_smelting"));

            // 精錬レシピ（溶鉱炉）：土→ダイヤ
            CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(Blocks.DIRT), Items.DIAMOND, 1.0F, 100)
                    .addCriterion("has_dirt", hasItem(Blocks.DIRT))
                    .build(consumer, new ResourceLocation(ExampleDataGeneratorMod.MOD_ID, "diamond_from_blasting"));

            // 精錬レシピ（燻製器）：土→ダイヤ
            CookingRecipeBuilder.cookingRecipe(Ingredient.fromItems(Blocks.DIRT), Items.DIAMOND, 1.0F, 100, IRecipeSerializer.SMOKING)
                    .addCriterion("has_dirt", this.hasItem(Blocks.DIRT))
                    .build(consumer, new ResourceLocation(ExampleDataGeneratorMod.MOD_ID, "diamond_from_smoking"));

            // 精錬レシピ（焚き火）：土→ダイヤ
            CookingRecipeBuilder.cookingRecipe(Ingredient.fromItems(Blocks.DIRT), Items.DIAMOND, 1.0F, 600, IRecipeSerializer.CAMPFIRE_COOKING)
                    .addCriterion("has_dirt", this.hasItem(Blocks.DIRT))
                    .build(consumer, new ResourceLocation(ExampleDataGeneratorMod.MOD_ID, "diamond_from_campfire_cooking"));
        }
    }
}
