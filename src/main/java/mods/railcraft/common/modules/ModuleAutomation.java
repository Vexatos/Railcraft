/* 
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info
 * 
 * This code is the property of CovertJaguar
 * and may only be used with explicit written
 * permission unless otherwise specified on the
 * license page at http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.common.modules;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import mods.railcraft.common.plugins.forge.ItemRegistry;
import mods.railcraft.common.blocks.aesthetics.brick.BlockBrick;
import mods.railcraft.common.blocks.aesthetics.cube.BlockCube;
import mods.railcraft.common.blocks.detector.BlockDetector;
import mods.railcraft.common.blocks.detector.EnumDetector;
import mods.railcraft.common.blocks.machine.alpha.EnumMachineAlpha;
import mods.railcraft.common.blocks.machine.alpha.TamingInteractHandler;
import mods.railcraft.common.blocks.machine.gamma.EnumMachineGamma;
import mods.railcraft.common.carts.EnumCart;
import mods.railcraft.common.carts.ItemBoreHeadDiamond;
import mods.railcraft.common.carts.ItemBoreHeadIron;
import mods.railcraft.common.carts.ItemBoreHeadSteel;
import mods.railcraft.common.core.RailcraftConfig;
import mods.railcraft.common.items.ItemPlate.EnumPlate;
import mods.railcraft.common.items.RailcraftItem;
import mods.railcraft.common.modules.orehandlers.BoreOreHandler;
import mods.railcraft.common.plugins.forge.CraftingPlugin;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

public class ModuleAutomation extends RailcraftModule {

    @Override
    public void preInit() {
        MinecraftForge.EVENT_BUS.register(new BoreOreHandler());
    }

    @Override
    public void initFirst() {
        BlockDetector.registerBlock();
        BlockCube.registerBlock();

        Block blockDetector = BlockDetector.getBlock();

        if (blockDetector != null) {
            CraftingPlugin.addShapedRecipe(new ItemStack(blockDetector, 1, EnumDetector.ITEM.ordinal()), new Object[]{
                "XXX",
                "XPX",
                "XXX",
                'X', "plankWood",
                'P', Blocks.stone_pressure_plate,});
            CraftingPlugin.addShapedRecipe(new ItemStack(blockDetector, 1, EnumDetector.ANY.ordinal()), new Object[]{
                "XXX",
                "XPX",
                "XXX",
                'X', Blocks.stone,
                'P', Blocks.stone_pressure_plate,});
            CraftingPlugin.addShapedRecipe(new ItemStack(blockDetector, 1, EnumDetector.EMPTY.ordinal()), new Object[]{
                "XXX",
                "XPX",
                "XXX",
                'X', new ItemStack(Blocks.stonebrick, 1, 0),
                'P', Blocks.stone_pressure_plate,});
            CraftingPlugin.addShapedRecipe(new ItemStack(blockDetector, 1, EnumDetector.MOB.ordinal()), new Object[]{
                "XXX",
                "XPX",
                "XXX",
                'X', new ItemStack(Blocks.stonebrick, 1, 1),
                'P', Blocks.stone_pressure_plate,});
            CraftingPlugin.addShapedRecipe(new ItemStack(blockDetector, 1, EnumDetector.MOB.ordinal()), new Object[]{
                "XXX",
                "XPX",
                "XXX",
                'X', Blocks.mossy_cobblestone,
                'P', Blocks.stone_pressure_plate,});
            CraftingPlugin.addShapedRecipe(new ItemStack(blockDetector, 1, EnumDetector.POWERED.ordinal()), new Object[]{
                "XXX",
                "XPX",
                "XXX",
                'X', Blocks.cobblestone,
                'P', Blocks.stone_pressure_plate,});
            CraftingPlugin.addShapedRecipe(new ItemStack(blockDetector, 1, EnumDetector.PLAYER.ordinal()), new Object[]{
                "XXX",
                "XPX",
                "XXX",
                'X', new ItemStack(Blocks.stone_slab, 1, 0),
                'P', Blocks.stone_pressure_plate,});
            CraftingPlugin.addShapedRecipe(new ItemStack(blockDetector, 1, EnumDetector.EXPLOSIVE.ordinal()), new Object[]{
                "XXX",
                "XPX",
                "XXX",
                'X', "slabWood",
                'P', Blocks.stone_pressure_plate,});
            CraftingPlugin.addShapedRecipe(new ItemStack(blockDetector, 1, EnumDetector.ANIMAL.ordinal()), new Object[]{
                "XXX",
                "XPX",
                "XXX",
                'X', new ItemStack(Blocks.log, 1, 0),
                'P', Blocks.stone_pressure_plate,});
            CraftingPlugin.addShapedRecipe(new ItemStack(blockDetector, 1, EnumDetector.AGE.ordinal()), new Object[]{
                "XXX",
                "XPX",
                "XXX",
                'X', new ItemStack(Blocks.log, 1, 1),
                'P', Blocks.stone_pressure_plate,});
            CraftingPlugin.addShapedRecipe(new ItemStack(blockDetector, 1, EnumDetector.ADVANCED.ordinal()), new Object[]{
                "XXX",
                "XPX",
                "XXX",
                'X', "ingotSteel",
                'P', Blocks.stone_pressure_plate,});
            CraftingPlugin.addShapedRecipe(new ItemStack(blockDetector, 1, EnumDetector.TANK.ordinal()), new Object[]{
                "XXX",
                "XPX",
                "XXX",
                'X', Items.brick,
                'P', Blocks.stone_pressure_plate,});
            CraftingPlugin.addShapedRecipe(new ItemStack(blockDetector, 1, EnumDetector.SHEEP.ordinal()), new Object[]{
                "XXX",
                "XPX",
                "XXX",
                'X', Blocks.wool,
                'P', Blocks.stone_pressure_plate,});
            CraftingPlugin.addShapedRecipe(new ItemStack(blockDetector, 1, EnumDetector.VILLAGER.ordinal()),
                    "XXX",
                    "XPX",
                    "XXX",
                    'X', Items.leather,
                    'P', Blocks.stone_pressure_plate);
        }

        EnumMachineGamma gamma = EnumMachineGamma.DISPENSER_CART;
        if (gamma.register())
            CraftingPlugin.addShapedRecipe(gamma.getItem(),
                    "ML",
                    'M', Items.minecart,
                    'L', Blocks.dispenser);

        EnumMachineAlpha alpha = EnumMachineAlpha.FEED_STATION;
        if (alpha.register()) {
            ItemStack stack = alpha.getItem();
            CraftingPlugin.addShapedRecipe(stack, new Object[]{
                "PCP",
                "CSC",
                "PCP",
                'P', "plankWood",
                'S', ModuleManager.isModuleLoaded(ModuleManager.Module.FACTORY) ? RailcraftItem.plate.getRecipeObject(EnumPlate.STEEL) : new ItemStack(Blocks.iron_block),
                'C', new ItemStack(Items.golden_carrot)});

            MinecraftForge.EVENT_BUS.register(new TamingInteractHandler());
        }

        alpha = EnumMachineAlpha.TRADE_STATION;
        if (alpha.register()) {
            ItemStack stack = alpha.getItem();
            CraftingPlugin.addShapedRecipe(stack,
                    "SGS",
                    "EDE",
                    "SGS",
                    'D', new ItemStack(Blocks.dispenser),
                    'G', new ItemStack(Blocks.glass_pane),
                    'E', Items.emerald,
                    'S', ModuleManager.isModuleLoaded(ModuleManager.Module.FACTORY) ? RailcraftItem.plate.getRecipeObject(EnumPlate.STEEL) : new ItemStack(Blocks.iron_block));
        }

        // Define Bore
        EnumCart cart = EnumCart.BORE;
        if (cart.setup()) {
            CraftingPlugin.addShapedRecipe(cart.getCartItem(),
                    "ICI",
                    "FCF",
                    " S ",
                    'I', "blockSteel",
                    'S', Items.chest_minecart,
                    'F', Blocks.furnace,
                    'C', Items.minecart);

            String tag = "tool.bore.head.diamond";
            if (RailcraftConfig.isItemEnabled(tag)) {
                Item item = new ItemBoreHeadDiamond();
                ItemRegistry.registerItem(item);
                CraftingPlugin.addShapedRecipe(new ItemStack(item),
                        "III",
                        "IDI",
                        "III",
                        'I', "ingotSteel",
                        'D', Blocks.diamond_block);
                ItemRegistry.registerItemStack(tag, new ItemStack(item));
            }

            tag = "tool.bore.head.steel";
            if (RailcraftConfig.isItemEnabled(tag)) {
                Item item = new ItemBoreHeadSteel();
                ItemRegistry.registerItem(item);
                CraftingPlugin.addShapedRecipe(new ItemStack(item),
                        "III",
                        "IDI",
                        "III",
                        'I', "ingotSteel",
                        'D', "blockSteel");
                ItemRegistry.registerItemStack(tag, new ItemStack(item));
            }

            tag = "tool.bore.head.iron";
            if (RailcraftConfig.isItemEnabled(tag)) {
                Item item = new ItemBoreHeadIron();
                ItemRegistry.registerItem(item);
                CraftingPlugin.addShapedRecipe(new ItemStack(item),
                        "III",
                        "IDI",
                        "III",
                        'I', "ingotSteel",
                        'D', Blocks.iron_block);
                ItemRegistry.registerItemStack(tag, new ItemStack(item));
            }
        }

        // Define Track Relayer Cart
        cart = EnumCart.TRACK_RELAYER;
        if (cart.setup())
            CraftingPlugin.addShapedRecipe(cart.getCartItem(),
                    "YLY",
                    "RSR",
                    "DMD",
                    'L', new ItemStack(Blocks.redstone_lamp),
                    'Y', "dyeYellow",
                    'R', new ItemStack(Items.blaze_rod),
                    'D', new ItemStack(Items.diamond_pickaxe),
                    'S', "blockSteel",
                    'M', new ItemStack(Items.minecart));

        // Define Undercutter Cart
        cart = EnumCart.UNDERCUTTER;
        if (cart.setup())
            CraftingPlugin.addShapedRecipe(cart.getCartItem(),
                    "YLY",
                    "RSR",
                    "DMD",
                    'L', new ItemStack(Blocks.redstone_lamp),
                    'Y', "dyeYellow",
                    'R', new ItemStack(Blocks.piston),
                    'D', new ItemStack(Items.diamond_shovel),
                    'S', "blockSteel",
                    'M', new ItemStack(Items.minecart));
    }

    @Override
    public void initSecond() {
        Block blockDetector = BlockDetector.getBlock();

        if (blockDetector != null)
            if (BlockBrick.infernal != null)
                CraftingPlugin.addShapedRecipe(new ItemStack(blockDetector, 1, EnumDetector.LOCOMOTIVE.ordinal()),
                        "XXX",
                        "XPX",
                        "XXX",
                        'X', new ItemStack(BlockBrick.infernal, 1, 0),
                        'P', Blocks.stone_pressure_plate);
    }

}
