package de.noriskclient.watermod.mixin;

import net.minecraft.block.Blocks;
import net.minecraft.entity.boss.dragon.EnderDragonFight;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.feature.EndPortalFeature;
import net.minecraft.world.gen.feature.FeatureConfig;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin(EnderDragonFight.class)
public class EnderDragonFightMixin {

  @Shadow private BlockPos exitPortalLocation;

  @Shadow @Final private ServerWorld world;

  /**
   * @author mgvpri
   */
  @Overwrite()
  private void generateEndPortal(boolean previouslyKilled) {
    EndPortalFeature endPortalFeature = new EndPortalFeature(previouslyKilled);
    if (this.exitPortalLocation == null) {
      for(this.exitPortalLocation = this.world.getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EndPortalFeature.ORIGIN).down();
          (this.world.getBlockState(this.exitPortalLocation).isOf(Blocks.BEDROCK) || this.world.getBlockState(this.exitPortalLocation).isOf(Blocks.WATER));
          this.exitPortalLocation = this.exitPortalLocation.down()) {
      }
    }

    endPortalFeature.configure(FeatureConfig.DEFAULT).generate(this.world, this.world.getStructureAccessor(), this.world.getChunkManager().getChunkGenerator(), new Random(), this.exitPortalLocation);
  }

}
