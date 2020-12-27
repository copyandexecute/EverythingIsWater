package de.noriskclient.watermod.mixin;

import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.pool.StructurePoolRegistry;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Random;

@Mixin(StructurePoolBasedGenerator.class)
public class StructurePoolBasedGeneratorMixin {

    @Shadow @Final public static StructurePoolRegistry REGISTRY;

    @Redirect(method = "addPieces", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/chunk/ChunkGenerator;getHeightOnGround(IILnet/minecraft/world/Heightmap$Type;)I"))
    private static int injected(ChunkGenerator chunkGenerator, int x, int z, Heightmap.Type heightmapType) {
        return chunkGenerator.getHeightOnGround(x, z, Heightmap.Type.OCEAN_FLOOR);

    }

    @Inject(method = "addPieces", at = @At(value = "INVOKE",target = "Ljava/util/List;add(Ljava/lang/Object;)Z",shift = At.Shift.AFTER))
    private static void injected2(Identifier startPoolId, int size, StructurePoolBasedGenerator.PieceFactory pieceFactory, ChunkGenerator chunkGenerator, StructureManager structureManager, BlockPos blockPos, List<? super PoolStructurePiece> list, Random random, boolean bl, boolean bl2, CallbackInfo ci) {
        BlockRotation blockRotation = BlockRotation.random(random);
        StructurePool structurePool = REGISTRY.get(startPoolId);
        StructurePoolElement structurePoolElement = structurePool.getRandomElement(random);
        PoolStructurePiece poolStructurePiece = pieceFactory.create(structureManager, structurePoolElement, blockPos, structurePoolElement.getGroundLevelDelta(), blockRotation, structurePoolElement.getBoundingBox(structureManager, blockPos, blockRotation));
        System.out.println(size);
    }

   /* @ModifyVariable(method = "addPieces", at = @At("HEAD"), ordinal = 0)
    private static boolean modifyBool(boolean bl1) {
        return false;
    } */
}
