package de.noriskclient.watermod.mixin;

import org.spongepowered.asm.mixin.Mixin;

@Mixin(targets = "net.minecraft.structure.pool.StructurePoolBasedGenerator.StructurePoolGenerator")
public class StructurePoolGeneratorMixin {

  /*@Redirect(method = "generatePiece", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/chunk/ChunkGenerator;getHeightOnGround(IILnet/minecraft/world/Heightmap$Type;)I"))
    private int injected(ChunkGenerator chunkGenerator, int x, int z, Heightmap.Type heightmapType) {
        System.out.println("Hallo");
        return chunkGenerator.getHeightOnGround(x, z, Heightmap.Type.OCEAN_FLOOR_WG);
    } */
}
