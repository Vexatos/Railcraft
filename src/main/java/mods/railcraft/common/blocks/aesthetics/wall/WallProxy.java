/* 
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info
 * 
 * This code is the property of CovertJaguar
 * and may only be used with explicit written
 * permission unless otherwise specified on the
 * license page at http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.common.blocks.aesthetics.wall;

/**
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public interface WallProxy {

    public WallInfo[] values();

    WallInfo fromMeta(int meta);
}
