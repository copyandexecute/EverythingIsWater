package de.noriskclient.watermod.mixin;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.carver.Carver;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Biome.class)
public class BiomeMixin {

  @ModifyArg(method = "configureCarver", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/carver/ConfiguredCarver;<init>(Lnet/minecraft/world/gen/carver/Carver;Lnet/minecraft/world/gen/carver/CarverConfig;)V"), index = 0)
  private static Carver injected(Carver c) {
    return Carver.UNDERWATER_CAVE;
  }

}
