package de.noriskclient.watermod.mixin;

import net.minecraft.block.Blocks;
import net.minecraft.structure.Structure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.List;

@Mixin(Structure.class)
public class StructureMixin {

  @ModifyVariable(method = "process", at = @At(value = "STORE", ordinal = 0))
  private static Structure.StructureBlockInfo injected(Structure.StructureBlockInfo info) {
    if (info.state.isOf(Blocks.AIR) || info.state.isOf(Blocks.CAVE_AIR)) {
      return new Structure.StructureBlockInfo(info.pos, Blocks.WATER.getDefaultState(), info.tag);
    }
    return info;
  }

}
