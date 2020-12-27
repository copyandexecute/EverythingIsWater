package de.noriskclient.watermod.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.structure.StructurePiece;
import net.minecraft.util.math.BlockBox;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(StructurePiece.class)
public class StructurePieceMixin {

    @ModifyVariable(method = "fillWithOutline(Lnet/minecraft/world/WorldAccess;Lnet/minecraft/util/math/BlockBox;IIIIIILnet/minecraft/block/BlockState;Lnet/minecraft/block/BlockState;Z)V", at = @At("HEAD"), ordinal = 0)
    private BlockState injected(BlockState blockState) {
        return blockState.equals(Blocks.AIR.getDefaultState()) || blockState.equals(Blocks.CAVE_AIR.getDefaultState()) ? Blocks.WATER.getDefaultState() : blockState;
    }

    @ModifyVariable(method = "fillWithOutline(Lnet/minecraft/world/WorldAccess;Lnet/minecraft/util/math/BlockBox;IIIIIILnet/minecraft/block/BlockState;Lnet/minecraft/block/BlockState;Z)V", at = @At("HEAD"), ordinal = 1)
    private BlockState injected2(BlockState blockState) {
        return blockState.equals(Blocks.AIR.getDefaultState()) || blockState.equals(Blocks.CAVE_AIR.getDefaultState()) ? Blocks.WATER.getDefaultState() : blockState;
    }

    @ModifyVariable(method = "addBlock", at = @At("HEAD"), ordinal = 0)
    private BlockState modifyaddBlock( BlockState block) {
        return block.getMaterial().equals(Material.AIR) ? Blocks.WATER.getDefaultState() : block;
    }
}
