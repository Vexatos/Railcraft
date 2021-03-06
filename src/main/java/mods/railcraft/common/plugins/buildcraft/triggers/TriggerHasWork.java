package mods.railcraft.common.plugins.buildcraft.triggers;

import buildcraft.api.gates.ITriggerParameter;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

/**
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public class TriggerHasWork extends Trigger {

    @Override
    public boolean isTriggerActive(ForgeDirection side, TileEntity tile, ITriggerParameter parameter) {
        if (tile instanceof IHasWork) {
            return ((IHasWork) tile).hasWork();
        }
        return false;
    }
}
