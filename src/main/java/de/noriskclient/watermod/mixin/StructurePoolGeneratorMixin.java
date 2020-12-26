package de.noriskclient.watermod.mixin;

import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "net.minecraft.structure.pool.StructurePoolBasedGenerator.StructurePoolGenerator")
public class StructurePoolGeneratorMixin {

    @Redirect(method = "generatePiece", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/chunk/ChunkGenerator;getHeightOnGround(IILnet/minecraft/world/Heightmap$Type;)I"))
    private int injected(ChunkGenerator chunkGenerator, int x, int z, Heightmap.Type heightmapType) {
        return chunkGenerator.getHeightOnGround(x, z, Heightmap.Type.OCEAN_FLOOR_WG);
    }
}
