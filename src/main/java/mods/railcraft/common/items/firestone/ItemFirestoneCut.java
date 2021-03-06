/* 
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info
 * 
 * This code is the property of CovertJaguar
 * and may only be used with explicit written
 * permission unless otherwise specified on the
 * license page at http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.common.items.firestone;

import java.util.List;
import mods.railcraft.common.core.RailcraftConfig;
import mods.railcraft.common.gui.tooltips.ToolTip;
import mods.railcraft.common.plugins.forge.ItemRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 *
 * @author CovertJaguar <http://www.railcraft.info/>
 */
public class ItemFirestoneCut extends ItemFirestoneBase {

    public static Item item;

    public static void registerItem() {
        if (item == null) {
            String tag = "railcraft.firestone.cut";

            if (RailcraftConfig.isItemEnabled(tag)) {
                item = new ItemFirestoneCut().setUnlocalizedName(tag);
                ItemRegistry.registerItem(item);

                ItemRegistry.registerItemStack(tag, new ItemStack(item));
            }
        }
    }

    public static ItemStack getItem() {
        return new ItemStack(item);
    }

    public ItemFirestoneCut() {
        setMaxStackSize(1);
    }

}
