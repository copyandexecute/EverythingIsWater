package de.noriskclient.watermod.mixin;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DiskFeature;
import net.minecraft.world.gen.feature.DiskFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Random;

@Mixin(DiskFeature.class)
public class DiskFeatureMixin extends Feature<DiskFeatureConfig> {

    public DiskFeatureMixin(Codec<DiskFeatureConfig> configCodec) {
        super(configCodec);
    }

    /**
     * @author NoRisk
     */
    @Overwrite
    public boolean generate(ServerWorldAccess serverWorldAccess, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockPos blockPos, DiskFeatureConfig diskFeatureConfig) {
        if (blockPos.getY() > chunkGenerator.getSeaLevel()) {
            return false;
        }

        if (!serverWorldAccess.getFluidState(blockPos).isIn(FluidTags.WATER) && blockPos.getY() < chunkGenerator.getSeaLevel()) {
            return false;
        } else {
            int i = 0;
            int j = random.nextInt(diskFeatureConfig.radius - 2) + 2;

            for (int k = blockPos.getX() - j; k <= blockPos.getX() + j; ++k) {
                for (int l = blockPos.getZ() - j; l <= blockPos.getZ() + j; ++l) {
                    int m = k - blockPos.getX();
                    int n = l - blockPos.getZ();
                    if (m * m + n * n <= j * j) {
                        for (int o = blockPos.getY() - diskFeatureConfig.ySize; o <= blockPos.getY() + diskFeatureConfig.ySize; ++o) {
                            BlockPos blockPos2 = new BlockPos(k, o, l);
                            BlockState blockState = serverWorldAccess.getBlockState(blockPos2);

                            for (BlockState blockState2 : diskFeatureConfig.targets) {
                                if (blockState2.isOf(blockState.getBlock())) {
                                    serverWorldAccess.setBlockState(blockPos2, diskFeatureConfig.state, 2);
                                    ++i;
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            return i > 0;
        }
    }
}
