/* 
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info
 * 
 * This code is the property of CovertJaguar
 * and may only be used with explicit written
 * permission unless otherwise specified on the
 * license page at http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.common.gui.containers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import mods.railcraft.common.blocks.machine.gamma.TileLiquidLoader;
import mods.railcraft.common.gui.slots.SlotLiquidContainerFilled;
import mods.railcraft.common.gui.slots.SlotMinecartFilter;
import mods.railcraft.common.gui.slots.SlotOutput;
import mods.railcraft.common.gui.widgets.FluidGaugeWidget;

public class ContainerLiquidLoader extends RailcraftContainer {

    private TileLiquidLoader tile;
    private Slot input;
    private Slot output;

    public ContainerLiquidLoader(InventoryPlayer inventoryplayer, TileLiquidLoader tile) {
        super(tile);
        this.tile = tile;

        addWidget(new FluidGaugeWidget(tile.getTankManager().get(0), 17, 23, 176, 0, 16, 47));

        addSlot(new SlotMinecartFilter(tile.getCartFilters(), 0, 62, 39));
        addSlot(new SlotMinecartFilter(tile.getCartFilters(), 1, 80, 39));
        addSlot(input = new SlotLiquidContainerFilled(tile, 0, 134, 21));
        addSlot(output = new SlotOutput(tile, 1, 134, 56));
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 9; k++) {
                addSlot(new Slot(inventoryplayer, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
            }

        }

        for (int j = 0; j < 9; j++) {
            addSlot(new Slot(inventoryplayer, j, 8 + j * 18, 142));
        }
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        tile.getTankManager().updateGuiData(this, crafters, 0);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data) {
        tile.getTankManager().processGuiUpdate(id, data);
    }
}
