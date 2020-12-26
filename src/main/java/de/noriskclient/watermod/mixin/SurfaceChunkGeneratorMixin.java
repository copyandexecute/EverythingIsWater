package de.noriskclient.watermod.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.StructuresConfig;
import net.minecraft.world.gen.chunk.SurfaceChunkGenerator;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import static net.minecraft.world.gen.surfacebuilder.SurfaceBuilder.AIR;

@Mixin(SurfaceChunkGenerator.class)
public abstract class SurfaceChunkGeneratorMixin extends ChunkGenerator {
  @Shadow
  @Final
  protected BlockState defaultBlock;

  @Shadow
  @Final
  protected BlockState defaultFluid;

  public SurfaceChunkGeneratorMixin(BiomeSource biomeSource, StructuresConfig structuresConfig) {
    super(biomeSource, structuresConfig);
  }

  public SurfaceChunkGeneratorMixin(BiomeSource biomeSource, BiomeSource biomeSource2, StructuresConfig structuresConfig, long l) {
    super(biomeSource, biomeSource2, structuresConfig, l);
  }


  /**
   * @reason awdaw
   * @author NoRisk
   */
  @Overwrite()
  public BlockState getBlockState(double density, int y) {
    BlockState blockState3;
    if (density > 0.0D) {
      blockState3 = this.defaultBlock;
    } else if (y < this.getSeaLevel() + 100) {
      blockState3 = Blocks.WATER.getDefaultState();
    } else {
      blockState3 = AIR;
    }

    return blockState3;
  }

}
