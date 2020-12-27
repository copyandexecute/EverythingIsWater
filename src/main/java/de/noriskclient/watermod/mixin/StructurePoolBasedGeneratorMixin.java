package de.noriskclient.watermod.mixin;

import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(StructurePoolBasedGenerator.class)
public class StructurePoolBasedGeneratorMixin {

    @Redirect(method = "addPieces", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/chunk/ChunkGenerator;getHeightOnGround(IILnet/minecraft/world/Heightmap$Type;)I"))
    private static int injected(ChunkGenerator chunkGenerator, int x, int z, Heightmap.Type heightmapType) {
        return chunkGenerator.getHeightOnGround(x, z, Heightmap.Type.OCEAN_FLOOR);
    }

   /* @ModifyVariable(method = "addPieces", at = @At("HEAD"), ordinal = 0)
    private static boolean modifyBool(boolean bl1) {
        return false;
    } */
}
