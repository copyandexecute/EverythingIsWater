package de.noriskclient.watermod.mixin;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ModifiableWorld;
import net.minecraft.world.gen.feature.EndSpikeFeature;
import net.minecraft.world.gen.feature.EndSpikeFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EndSpikeFeature.class)
public abstract class EndSpikeFeatureMixin extends Feature<EndSpikeFeatureConfig> {
    public EndSpikeFeatureMixin(Codec<EndSpikeFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Redirect(method = "generateSpike", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/feature/EndSpikeFeature;setBlockState(Lnet/minecraft/world/ModifiableWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)V"))
    private void injected(EndSpikeFeature endSpikeFeature, ModifiableWorld world, BlockPos pos, BlockState state) {
        this.setBlockState(world, pos, state.equals(Blocks.AIR.getDefaultState()) ? Blocks.WATER.getDefaultState() : state);
    }
}
