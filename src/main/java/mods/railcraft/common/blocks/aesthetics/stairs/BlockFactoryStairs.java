/* 
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info
 * 
 * This code is the property of CovertJaguar
 * and may only be used with explicit written
 * permission unless otherwise specified on the
 * license page at http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.common.blocks.aesthetics.stairs;

import cpw.mods.fml.common.registry.GameRegistry;
import mods.railcraft.api.crafting.IRockCrusherRecipe;
import mods.railcraft.api.crafting.RailcraftCraftingManager;
import mods.railcraft.common.blocks.BlockFactory;

import static mods.railcraft.common.blocks.aesthetics.EnumBlockMaterial.*;

import mods.railcraft.common.blocks.aesthetics.EnumBlockMaterial;
import mods.railcraft.common.blocks.aesthetics.brick.BlockBrick;

import static mods.railcraft.common.blocks.aesthetics.stairs.BlockRailcraftStairs.*;

import mods.railcraft.common.core.Railcraft;
import mods.railcraft.common.plugins.forge.LocalizationPlugin;
import mods.railcraft.common.modules.ModuleManager;
import mods.railcraft.common.plugins.forestry.ForestryPlugin;
import mods.railcraft.common.plugins.forge.CraftingPlugin;
import mods.railcraft.common.plugins.forge.ItemRegistry;

import net.minecraft.item.ItemStack;

/**
 *
 * @author CovertJaguar <http://www.railcraft.info/>
 */
public class BlockFactoryStairs extends BlockFactory {

    public BlockFactoryStairs() {
        super("stair");
    }

    @Override
    protected void doBlockInit() {
        int renderId = Railcraft.getProxy().getRenderId();
        BlockRailcraftStairs.block = new BlockRailcraftStairs(renderId);
        BlockRailcraftStairs.block.setBlockName("railcraft.stair");
        GameRegistry.registerBlock(BlockRailcraftStairs.block, ItemStair.class, BlockRailcraftStairs.block.getUnlocalizedName());
        GameRegistry.registerTileEntity(TileStair.class, "RCStairTile");

        for (EnumBlockMaterial mat : EnumBlockMaterial.VALUES) {
            ItemRegistry.registerItemStack(getTag(mat), getItem(mat));

            switch (mat) {
                case SNOW:
                case ICE:
                    break;
                default:
                    ForestryPlugin.addBackpackItem("builder", getItem(mat));
            }
        }
    }

    @Override
    protected void doRecipeInit(ModuleManager.Module module) {
        EnumBlockMaterial.initialize();
        for (EnumBlockMaterial mat : EnumBlockMaterial.VALUES) {
            if (BlockRailcraftStairs.isEnabled(mat) && mat.getSourceBlock() != null) {
                CraftingPlugin.addShapedRecipe(BlockRailcraftStairs.getItem(mat, 4), "S  ", "SS ", "SSS", 'S', mat.getSourceItem());
                IRockCrusherRecipe recipe = RailcraftCraftingManager.rockCrusher.createNewRecipe(BlockRailcraftStairs.getItem(mat), true, false);
                recipe.addOutput(mat.getSourceItem(), 1.0f);
            }
        }

        addRockCrusherRecipe(BlockBrick.abyssal, ABYSSAL_BLOCK, ABYSSAL_BRICK, ABYSSAL_COBBLE, ABYSSAL_FITTED);
        addRockCrusherRecipe(BlockBrick.bleachedbone, BLEACHEDBONE_BLOCK, BLEACHEDBONE_BRICK, BLEACHEDBONE_COBBLE, BLEACHEDBONE_FITTED);
        addRockCrusherRecipe(BlockBrick.bloodstained, BLOODSTAINED_BLOCK, BLOODSTAINED_BRICK, BLOODSTAINED_COBBLE, BLOODSTAINED_FITTED);
        addRockCrusherRecipe(BlockBrick.frostbound, FROSTBOUND_BLOCK, FROSTBOUND_BRICK, FROSTBOUND_COBBLE, FROSTBOUND_FITTED);
        addRockCrusherRecipe(BlockBrick.infernal, INFERNAL_BLOCK, INFERNAL_BRICK, INFERNAL_COBBLE, INFERNAL_FITTED);
        addRockCrusherRecipe(BlockBrick.nether, NETHER_BLOCK, NETHER_COBBLE, NETHER_FITTED);
        addRockCrusherRecipe(BlockBrick.quarried, QUARRIED_BLOCK, QUARRIED_BRICK, QUARRIED_COBBLE, QUARRIED_FITTED);
        addRockCrusherRecipe(BlockBrick.sandy, SANDY_BLOCK, SANDY_BRICK, SANDY_COBBLE, SANDY_FITTED);
    }

    private void addRockCrusherRecipe(BlockBrick brick, EnumBlockMaterial... types) {
        if (brick == null)
            return;
        ItemStack output = brick.getItemStack(BlockBrick.BrickVariant.COBBLE, 1);
        for (EnumBlockMaterial mat : types) {
            if (!BlockRailcraftStairs.isEnabled(mat))
                continue;
            IRockCrusherRecipe recipe = RailcraftCraftingManager.rockCrusher.createNewRecipe(getItem(mat), true, false);
            recipe.addOutput(output, 1.0F);
        }
    }

}
