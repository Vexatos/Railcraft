/* 
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info
 * 
 * This code is the property of CovertJaguar
 * and may only be used with explicit written
 * permission unless otherwise specified on the
 * license page at http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.common.blocks.aesthetics.lamp;

import net.minecraft.block.*;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import mods.railcraft.client.particles.ParticleHelper;
import mods.railcraft.common.plugins.forge.CreativePlugin;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class BlockStoneLantern extends Block {

    public static boolean useCandleIcon = false;
    private static final float SELECT = 2 * 0.0625f;
    static BlockStoneLantern block;

    public static BlockStoneLantern getBlock() {
        return block;
    }

    private final int renderId;
    public IIcon candleIcon;

    protected BlockStoneLantern(int renderId) {
        super(Material.redstoneLight);
        this.renderId = renderId;
        this.setStepSound(Block.soundTypeStone);
        setCreativeTab(CreativePlugin.RAILCRAFT_TAB);
        setHardness(5);
        setResistance(15);
//        useNeighborBrightness[id] = false;
        opaque = false;
        lightOpacity = 0;
        setLightLevel(0.9375F);
        setHarvestLevel("pickaxe", 0);
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        return new ItemStack(this, 1, meta);
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (EnumStoneLantern lamp : EnumStoneLantern.creativeList) {
            if (lamp.isEnabled())
                list.add(lamp.getItem());
        }
    }

    @Override
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
        return AxisAlignedBB.getBoundingBox(x + SELECT, y + 2 * 0.0625f, z + SELECT, x + 1 - SELECT, y + 1.0F - 1 * 0.0625f, z + 1 - SELECT);
    }

    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random par5Random) {
        double dx = x + 0.5F;
        double dy = y + 0.65F;
        double dz = z + 0.5F;

        world.spawnParticle("smoke", dx, dy, dz, 0.0D, 0.0D, 0.0D);
        world.spawnParticle("flame", dx, dy, dz, 0.0D, 0.0D, 0.0D);
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean isBlockNormalCube() {
        return false;
    }

    @Override
    public int damageDropped(int meta) {
        return meta;
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random random) {
        return 1;
    }

    @Override
    public int getRenderType() {
        return renderId;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {
        if (useCandleIcon)
            return candleIcon;
        return EnumStoneLantern.fromOrdinal(meta).getTexture(side);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer) {
        return ParticleHelper.addHitEffects(worldObj, block, target, effectRenderer, null);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean addDestroyEffects(World worldObj, int x, int y, int z, int meta, EffectRenderer effectRenderer) {
        return ParticleHelper.addDestroyEffects(worldObj, block, x, y, z, meta, effectRenderer, null);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        candleIcon = iconRegister.registerIcon("railcraft:stonelamp.candle");
    }

}
