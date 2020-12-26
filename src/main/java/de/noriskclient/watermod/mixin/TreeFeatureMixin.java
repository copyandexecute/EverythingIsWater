package de.noriskclient.watermod.mixin;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;
import java.util.OptionalInt;
import java.util.Random;
import java.util.Set;

@Mixin(TreeFeature.class)
public abstract class TreeFeatureMixin extends Feature<TreeFeatureConfig> {
    public TreeFeatureMixin(Codec<TreeFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Shadow
    private static boolean isDirtOrGrass(TestableWorld world, BlockPos pos) {
        return false;
    }

    @Shadow
    protected abstract int method_29963(TestableWorld testableWorld, int i, BlockPos blockPos, TreeFeatureConfig treeFeatureConfig);

    /**
     * @author mgvpri
     */
    @Overwrite()
    private boolean generate(ModifiableTestableWorld world, Random random, BlockPos pos, Set<BlockPos> logPositions, Set<BlockPos> leavesPositions, BlockBox box, TreeFeatureConfig config) {
        int i = config.trunkPlacer.getHeight(random);
        int j = config.foliagePlacer.getHeight(random, i, config);
        int k = i - j;
        int l = config.foliagePlacer.getRadius(random, k);
        BlockPos blockPos2;
        int r;
        if (!config.skipFluidCheck) {
            int m = world.getTopPosition(Heightmap.Type.OCEAN_FLOOR, pos).getY();
            r = world.getTopPosition(Heightmap.Type.WORLD_SURFACE, pos).getY();
//      if (r - m > config.maxWaterDepth) {
//        return false;
//      }

            int q;
            if (config.heightmap == Heightmap.Type.OCEAN_FLOOR) {
                q = m;
            } else if (config.heightmap == Heightmap.Type.WORLD_SURFACE) {
                q = r;
            } else {
                q = world.getTopPosition(config.heightmap, pos).getY();
            }

            blockPos2 = new BlockPos(pos.getX(), q, pos.getZ());
        } else {
            blockPos2 = pos;
        }

        if (blockPos2.getY() >= 1 && blockPos2.getY() + i + 1 <= 256) {
            if (!isDirtOrGrass(world, blockPos2.down())) {
                return false;
            } else {
                OptionalInt optionalInt = config.minimumSize.getMinClippedHeight();
                r = this.method_29963(world, i, blockPos2, config);
                if (r >= i || optionalInt.isPresent() && r >= optionalInt.getAsInt()) {
                    List<FoliagePlacer.TreeNode> list = config.trunkPlacer.generate(world, random, r, blockPos2, logPositions, box, config);
                    int finalR = r;
                    list.forEach((treeNode) -> {
                        config.foliagePlacer.generate(world, random, config, finalR, treeNode, j, l, leavesPositions, box);
                    });
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }
}
