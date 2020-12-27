package de.noriskclient.watermod.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilder.NetherForestSurfaceBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(NetherForestSurfaceBuilder.class)
public class NetherForestSurfaceBuilderMixin {

  @Redirect(method = "generate", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isAir()Z"))
  private boolean injected(BlockState blockState) {
    return blockState.isOf(Blocks.WATER);
  }

}
