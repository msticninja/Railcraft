/*******************************************************************************
 * Copyright (c) CovertJaguar, 2011-2016
 * http://railcraft.info
 *
 * This code is the property of CovertJaguar
 * and may only be used with explicit written
 * permission unless otherwise specified on the
 * license page at http://railcraft.info/wiki/info:license.
 ******************************************************************************/
package mods.railcraft.common.blocks.aesthetics;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import mods.railcraft.common.blocks.aesthetics.brick.BrickTheme;
import mods.railcraft.common.blocks.aesthetics.brick.BrickVariant;
import mods.railcraft.common.blocks.aesthetics.cube.EnumCube;
import mods.railcraft.common.blocks.aesthetics.lantern.BlockLantern;
import mods.railcraft.common.blocks.aesthetics.slab.BlockRailcraftSlab;
import mods.railcraft.common.blocks.aesthetics.stairs.BlockRailcraftStairs;
import mods.railcraft.common.core.IRailcraftObjectContainer;
import mods.railcraft.common.core.IVariantEnum;
import net.minecraft.block.*;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author CovertJaguar <http://www.railcraft.info>
 */
public enum BlockMaterial implements IVariantEnum {

    STONE_BRICK("stone_brick", () -> Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.DEFAULT)),
    STONE_BRICK_CHISELED("stone_brick_chiseled", () -> Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.CHISELED)),
    STONE_BRICK_CRACKED("stone_brick_cracked", () -> Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.CRACKED)),
    STONE_BRICK_MOSSY("stone_brick_mossy", () -> Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.MOSSY)),

    BRICK("brick", Blocks.BRICK_BLOCK::getDefaultState),

    SANDSTONE("sandstone", () -> Blocks.SANDSTONE.getDefaultState().withProperty(BlockSandStone.TYPE, BlockSandStone.EnumType.DEFAULT)),
    SANDSTONE_CHISELED("sandstone_chiseled", () -> Blocks.SANDSTONE.getDefaultState().withProperty(BlockSandStone.TYPE, BlockSandStone.EnumType.CHISELED)),
    SANDSTONE_SMOOTH("sandstone_smooth", () -> Blocks.SANDSTONE.getDefaultState().withProperty(BlockSandStone.TYPE, BlockSandStone.EnumType.SMOOTH)),

    RED_SANDSTONE("red_sandstone", () -> Blocks.RED_SANDSTONE.getDefaultState().withProperty(BlockRedSandstone.TYPE, BlockRedSandstone.EnumType.DEFAULT)),
    RED_SANDSTONE_CHISELED("red_sandstone_chiseled", () -> Blocks.RED_SANDSTONE.getDefaultState().withProperty(BlockRedSandstone.TYPE, BlockRedSandstone.EnumType.CHISELED)),
    RED_SANDSTONE_SMOOTH("red_sandstone_smooth", () -> Blocks.RED_SANDSTONE.getDefaultState().withProperty(BlockRedSandstone.TYPE, BlockRedSandstone.EnumType.SMOOTH)),

    QUARTZ("quartz", () -> Blocks.QUARTZ_BLOCK.getDefaultState().withProperty(BlockQuartz.VARIANT, BlockQuartz.EnumType.DEFAULT)),
    QUARTZ_CHISELED("quartz_chiseled", () -> Blocks.QUARTZ_BLOCK.getDefaultState().withProperty(BlockQuartz.VARIANT, BlockQuartz.EnumType.CHISELED)),

    PURPUR("purpur", Blocks.PURPUR_BLOCK::getDefaultState),

    OBSIDIAN(39, "obsidian", Blocks.OBSIDIAN::getDefaultState),
    OBSIDIAN_CRUSHED("crushed_obsidian", EnumCube.CRUSHED_OBSIDIAN::getState),

    ABYSSAL_BLOCK(28, "abyssal_block", () -> BrickTheme.ABYSSAL.getState(BrickVariant.BLOCK)),
    ABYSSAL_BRICK(13, "abyssal_brick", () -> BrickTheme.ABYSSAL.getState(BrickVariant.BRICK)),
    ABYSSAL_COBBLE(36, "abyssal_cobble", () -> BrickTheme.ABYSSAL.getState(BrickVariant.COBBLE)),
    ABYSSAL_FITTED(20, "abyssal_fitted", () -> BrickTheme.ABYSSAL.getState(BrickVariant.FITTED)),

    BLEACHEDBONE_BLOCK(26, "bleached_bone_block", () -> BrickTheme.BLEACHEDBONE.getState(BrickVariant.BLOCK)),
    BLEACHEDBONE_BRICK(11, "bleached_bone_brick", () -> BrickTheme.BLEACHEDBONE.getState(BrickVariant.BRICK)),
    BLEACHEDBONE_COBBLE(34, "bleached_bone_cobble", () -> BrickTheme.BLEACHEDBONE.getState(BrickVariant.COBBLE)),
    BLEACHEDBONE_FITTED(18, "bleached_bone_fitted", () -> BrickTheme.BLEACHEDBONE.getState(BrickVariant.FITTED)),

    BLOODSTAINED_BLOCK(27, "bloodstained_block", () -> BrickTheme.BLOODSTAINED.getState(BrickVariant.BLOCK)),
    BLOODSTAINED_BRICK(12, "bloodstained_brick", () -> BrickTheme.BLOODSTAINED.getState(BrickVariant.BRICK)),
    BLOODSTAINED_COBBLE(35, "bloodstained_cobble", () -> BrickTheme.BLOODSTAINED.getState(BrickVariant.COBBLE)),
    BLOODSTAINED_FITTED(19, "bloodstained_fitted", () -> BrickTheme.BLOODSTAINED.getState(BrickVariant.FITTED)),

    FROSTBOUND_BLOCK(24, "frost_bound_block", () -> BrickTheme.FROSTBOUND.getState(BrickVariant.BLOCK)),
    FROSTBOUND_BRICK(9, "frost_bound_brick", () -> BrickTheme.FROSTBOUND.getState(BrickVariant.BRICK)),
    FROSTBOUND_COBBLE(32, "frost_bound_cobble", () -> BrickTheme.FROSTBOUND.getState(BrickVariant.COBBLE)),
    FROSTBOUND_FITTED(16, "frost_bound_fitted", () -> BrickTheme.FROSTBOUND.getState(BrickVariant.FITTED)),

    INFERNAL_BLOCK(23, "infernal_block", () -> BrickTheme.INFERNAL.getState(BrickVariant.BLOCK)),
    INFERNAL_BRICK(1, "infernal_brick", () -> BrickTheme.INFERNAL.getState(BrickVariant.BRICK)),
    INFERNAL_COBBLE(31, "infernal_cobble", () -> BrickTheme.INFERNAL.getState(BrickVariant.COBBLE)),
    INFERNAL_FITTED(15, "infernal_fitted", () -> BrickTheme.INFERNAL.getState(BrickVariant.FITTED)),

    NETHER_BLOCK(29, "nether_block", () -> BrickTheme.NETHER.getState(BrickVariant.BLOCK)),
    NETHER_BRICK("nether_brick", Blocks.NETHER_BRICK::getDefaultState),
    NETHER_COBBLE(37, "nether_cobble", () -> BrickTheme.NETHER.getState(BrickVariant.COBBLE)),
    NETHER_FITTED(21, "nether_fitted", () -> BrickTheme.NETHER.getState(BrickVariant.FITTED)),

    QUARRIED_BLOCK(25, "quarried_block", () -> BrickTheme.QUARRIED.getState(BrickVariant.BLOCK)),
    QUARRIED_BRICK(10, "quarried_brick", () -> BrickTheme.QUARRIED.getState(BrickVariant.BRICK)),
    QUARRIED_COBBLE(33, "quarried_cobble", () -> BrickTheme.QUARRIED.getState(BrickVariant.COBBLE)),
    QUARRIED_FITTED(17, "quarried_fitted", () -> BrickTheme.QUARRIED.getState(BrickVariant.FITTED)),

    SANDY_BLOCK(22, "sandy_block", () -> BrickTheme.SANDY.getState(BrickVariant.BLOCK)),
    SANDY_BRICK(0, "sandy_brick", () -> BrickTheme.SANDY.getState(BrickVariant.BRICK)),
    SANDY_COBBLE(30, "sandy_cobble", () -> BrickTheme.SANDY.getState(BrickVariant.COBBLE)),
    SANDY_FITTED(14, "sandy_fitted", () -> BrickTheme.SANDY.getState(BrickVariant.FITTED)),

    SNOW(3, "snow", Blocks.SNOW::getDefaultState),
    ICE(4, "ice", Blocks.ICE::getDefaultState),
    PACKED_ICE(5, "packed_ice", Blocks.PACKED_ICE::getDefaultState),

    IRON(6, "iron", Blocks.IRON_BLOCK::getDefaultState),
    STEEL(43, "steel", EnumCube.STEEL_BLOCK::getState),
    COPPER(40, "copper", EnumCube.COPPER_BLOCK::getState),
    TIN(41, "tin", EnumCube.TIN_BLOCK::getState),
    LEAD(42, "lead", EnumCube.LEAD_BLOCK::getState),
    GOLD(7, "gold", Blocks.GOLD_BLOCK::getDefaultState),

    DIAMOND(8, "diamond", Blocks.DIAMOND_BLOCK::getDefaultState),

    CONCRETE(2, "concrete", EnumCube.CONCRETE_BLOCK::getState),
    CREOSOTE(38, "creosote", EnumCube.CREOSOTE_BLOCK::getState),

    NO_MAT("no_mat", () -> null);
    public static final PropertyEnum<BlockMaterial> MATERIAL_PROPERTY = PropertyEnum.create("material", BlockMaterial.class);
    public static final Map<String, BlockMaterial> NAMES = new HashMap<String, BlockMaterial>();
    //    public static final BlockMaterial[] OLD_WALL1_MATS;
//    public static final BlockMaterial[] OLD_WALL2_MATS;
    public static final EnumSet<BlockMaterial> VANILLA_REFINED_MATS;
    public static final BiMap<BlockMaterial, Integer> OLD_ORDINALS;
    private static final BlockMaterial[] VALUES = values();
    private static final List<BlockMaterial> CREATIVE_LIST;
    private static boolean needsInit = true;

    static {
        VANILLA_REFINED_MATS = EnumSet.of(SANDSTONE, RED_SANDSTONE, QUARTZ, NETHER_BRICK, STONE_BRICK, BRICK, PURPUR);

//        OLD_WALL1_MATS = new BlockMaterial[]{
//                INFERNAL_BRICK,
//                SANDY_BRICK,
//                CONCRETE,
//                SNOW,
//                ICE,
//                STONE_BRICK,
//                STONE_BRICK_MOSSY,
//                STONE_BRICK_CRACKED,
//                STONE_BRICK_CHISELED,
//                NETHER_BRICK,
//                BRICK,
//                SANDSTONE,
//                SANDSTONE_CHISELED,
//                SANDSTONE_SMOOTH,
//                OBSIDIAN,
//                FROSTBOUND_BRICK};
//
//        OLD_WALL2_MATS = new BlockMaterial[]{
//                QUARTZ,
//                QUARTZ_CHISELED,
//                IRON,
//                GOLD,
//                DIAMOND,
//                ABYSSAL_BRICK,
//                QUARRIED_BRICK,
//                BLOODSTAINED_BRICK,
//                BLEACHEDBONE_BRICK};

//        WALL_SANDY_MATS = new BlockMaterial[16] {
//            SANDY_BRICK,
//                    SANDY_FITTED,
//            SANDY_BLOCK,
//                    SANDY_COBBLE,
//        } ;

        OLD_ORDINALS = HashBiMap.create();
        for (BlockMaterial mat : BlockMaterial.VALUES) {
            if (mat.oldOrdinal >= 0) {
                OLD_ORDINALS.put(mat, mat.oldOrdinal);
            }
        }

        CREATIVE_LIST = Arrays.asList(values());
    }

    public final int oldOrdinal;
    private final String name;
    private final Supplier<IBlockState> stateSupplier;
    private SoundType sound = SoundType.STONE;
    @Nullable
    private IBlockState state;
    private String oreTag;

    BlockMaterial(String name, Supplier<IBlockState> stateSupplier) {
        this(-1, name, stateSupplier);
    }

    BlockMaterial(int oldOrdinal, String name, Supplier<IBlockState> stateSupplier) {
        this.oldOrdinal = oldOrdinal;
        this.name = name;
        this.stateSupplier = stateSupplier;
    }

    public static void initialize() {
        if (!needsInit)
            return;
        needsInit = false;

        IRON.oreTag = "blockIron";
        GOLD.oreTag = "blockGold";
        DIAMOND.oreTag = "blockDiamond";

        COPPER.oreTag = "blockCopper";
        TIN.oreTag = "blockTin";
        LEAD.oreTag = "blockLead";
        STEEL.oreTag = "blockSteel";

        CONCRETE.sound = SoundType.STONE;

        CREOSOTE.sound = SoundType.WOOD;

        OBSIDIAN_CRUSHED.sound = SoundType.GROUND;

        COPPER.sound = SoundType.METAL;
        TIN.sound = SoundType.METAL;
        LEAD.sound = SoundType.METAL;
        STEEL.sound = SoundType.METAL;

        for (BlockMaterial mat : VALUES) {
            NAMES.put(mat.name(), mat);
            NAMES.put(mat.name, mat);
        }
    }

    @Deprecated
    public static BlockMaterial fromOrdinal(int id) {
        if (id < 0 || id >= VALUES.length)
            return VALUES[0];
        return VALUES[id];
    }

    @Deprecated
    public static BlockMaterial fromName(String name) {
        BlockMaterial mat = NAMES.get(name);
        if (mat != null)
            return mat;
        return getPlaceholder();
    }

    public static List<BlockMaterial> getValidMats() {
        initialize();
        return Arrays.stream(VALUES).filter(BlockMaterial::isSourceValid).collect(Collectors.toList());
    }

    public static List<BlockMaterial> getCreativeList() {
        initialize();
        return CREATIVE_LIST.stream().filter(BlockMaterial::isSourceValid).collect(Collectors.toList());
    }

    public static BlockMaterial getPlaceholder() {
        for (BlockMaterial material : VALUES) {
            if (material.isSourceValid())
                return material;
        }
        throw new RuntimeException("this should never happen");
    }

    @Nullable
    public IBlockState getState() {
        if (state == null)
            state = stateSupplier.get();
        return state;
    }

    @Nonnull
    @Override
    public String getName() {
        return name;
    }

    @Nonnull
    public String getRegistryName() {
        return "railcraft:" + name;
    }

    @Nonnull
    public String getLocalizationSuffix() {
        return name;
    }

    public String getOreTag() {
        return oreTag;
    }

    @Nonnull
    public SoundType getSound() {
        if (sound == null) {
            IBlockState state = getState();
            if (state != null) {
                sound = state.getBlock().getSoundType();
            }
        }
        return sound;
    }

    @Nullable
    public ItemStack getSourceItem() {
        IBlockState state = getState();
        if (state == null) return null;
        return new ItemStack(state.getBlock(), 1, state.getBlock().damageDropped(state));
    }

    @Nullable
    public Object getCraftingEquivalent() {
        if (oreTag != null) return oreTag;
        return getSourceItem();
    }

    public boolean isTransparent() {
        return this == ICE;
    }

    public float getBlockHardness(World world, BlockPos pos) {
        switch (this) {
            case CONCRETE:
                return EnumCube.CONCRETE_BLOCK.getHardness();
            case CREOSOTE:
                return EnumCube.CONCRETE_BLOCK.getHardness();
            case OBSIDIAN_CRUSHED:
                return EnumCube.CRUSHED_OBSIDIAN.getHardness();
            case COPPER:
                return EnumCube.COPPER_BLOCK.getHardness();
            case TIN:
                return EnumCube.TIN_BLOCK.getHardness();
            case LEAD:
                return EnumCube.LEAD_BLOCK.getHardness();
            case STEEL:
                return EnumCube.STEEL_BLOCK.getHardness();
            default:
                IBlockState state = getState();
                if (state == null)
                    return Blocks.BRICK_BLOCK.getDefaultState().getBlockHardness(world, pos);
                return state.getBlockHardness(world, pos);
        }
    }

    public float getExplosionResistance(Entity entity) {
        switch (this) {
            case CONCRETE:
                return EnumCube.CONCRETE_BLOCK.getResistance() * 3f / 5f;
            case CREOSOTE:
                return EnumCube.CONCRETE_BLOCK.getResistance() * 3f / 5f;
            case OBSIDIAN_CRUSHED:
                return EnumCube.CRUSHED_OBSIDIAN.getResistance() * 3f / 5f;
            case COPPER:
                return EnumCube.COPPER_BLOCK.getResistance() * 3f / 5f;
            case TIN:
                return EnumCube.TIN_BLOCK.getResistance() * 3f / 5f;
            case LEAD:
                return EnumCube.LEAD_BLOCK.getResistance() * 3f / 5f;
            case STEEL:
                return EnumCube.STEEL_BLOCK.getResistance() * 3f / 5f;
            default:
                IBlockState state = getState();
                if (state == null)
                    return Blocks.BRICK_BLOCK.getExplosionResistance(entity);
                try {
                    return state.getBlock().getExplosionResistance(entity);
                } catch (RuntimeException ex) {
                    return Blocks.BRICK_BLOCK.getExplosionResistance(entity);
                }
        }
    }

    public boolean isSourceValid() {
        return getState() != null;
    }

    @Override
    public boolean isValidBaseObject(Class<?> clazz) {
        return clazz == BlockRailcraftSlab.class || clazz == BlockRailcraftStairs.class || clazz == BlockLantern.class;
    }

    @Nullable
    @Override
    public Object getAlternate(IRailcraftObjectContainer container) {
        return null;
    }
}
