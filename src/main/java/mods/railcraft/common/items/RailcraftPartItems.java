/* 
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info
 * 
 * This code is the property of CovertJaguar
 * and may only be used with explicit written
 * permission unless otherwise specified on the
 * license page at http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.common.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import mods.railcraft.common.plugins.forge.ItemRegistry;
import mods.railcraft.common.blocks.machine.alpha.EnumMachineAlpha;
import mods.railcraft.common.core.RailcraftConfig;
import mods.railcraft.common.plugins.forge.CraftingPlugin;

/**
 * 
 * @author CovertJaguar <http://www.railcraft.info/>
 * @deprecated Items are being migrated to the RailcraftItem enum
 */
@Deprecated
public class RailcraftPartItems {

    public static Item itemTurbineBlade;
    public static Item itemTurbineDisk;
    public static Item itemTurbineRotor;

    public static void registerItem(String tag, ItemStack stack) {
        ItemRegistry.registerItemStack(tag, stack);
    }

    public static ItemStack getTurbineBlade() {
        return getTurbineBlade(1);
    }

    public static ItemStack getTurbineBlade(int qty) {
        Item item = itemTurbineBlade;
        if (item != null)
            return new ItemStack(item, qty);

        if (!EnumMachineAlpha.TURBINE.isAvaliable())
            return null;

        String tag = "railcraft.part.turbine.blade";
        if (!RailcraftConfig.isItemEnabled(tag))
            return ItemIngot.getIngot(ItemIngot.EnumIngot.STEEL, qty);

        item = new ItemRailcraft().setUnlocalizedName(tag);
        ItemRegistry.registerItem(item);
        ItemStack stack = new ItemStack(item);

        registerItem(tag, stack);

        CraftingPlugin.addShapedRecipe(stack, new Object[]{
            "I",
            "I",
            "I",
            'I', "ingotSteel"
        });

        itemTurbineBlade = item;

        return new ItemStack(item, qty);
    }

    public static ItemStack getTurbineDisk() {
        return getTurbineDisk(1);
    }

    public static ItemStack getTurbineDisk(int qty) {
        Item item = itemTurbineDisk;
        if (item != null)
            return new ItemStack(item, qty);

        if (!EnumMachineAlpha.TURBINE.isAvaliable())
            return null;

        String tag = "railcraft.part.turbine.disk";

        if (!RailcraftConfig.isItemEnabled(tag))
            return null;

        item = new ItemRailcraft().setUnlocalizedName(tag);
        ItemRegistry.registerItem(item);
        ItemStack stack = new ItemStack(item);

        registerItem(tag, stack);

        ItemStack blade = getTurbineBlade();
        if (blade != null)
            CraftingPlugin.addShapedRecipe(stack,
                    "BBB",
                    "BAB",
                    "BBB",
                    'A', "blockSteel",
                    'B', blade);

        itemTurbineDisk = item;

        return new ItemStack(item, qty);
    }

    public static ItemStack getTurbineRotor() {
        return getTurbineRotor(1);
    }

    public static ItemStack getTurbineRotor(int qty) {
        Item item = itemTurbineRotor;
        if (item != null)
            return new ItemStack(item, qty);

        if (!EnumMachineAlpha.TURBINE.isAvaliable())
            return null;

        String tag = "railcraft.part.turbine.rotor";

        if (!RailcraftConfig.isItemEnabled(tag))
            return null;

        item = new ItemRailcraft().setUnlocalizedName(tag);
        item.setMaxDamage(30000).setMaxStackSize(1);
        ItemRegistry.registerItem(item);
        ItemStack stack = new ItemStack(item);

        registerItem(tag, stack);

        ItemStack disk = getTurbineDisk();
        if (disk != null)
            CraftingPlugin.addShapedRecipe(stack,
                    "DDD",
                    'D', disk);

        itemTurbineRotor = item;

        return new ItemStack(item, qty);
    }

}