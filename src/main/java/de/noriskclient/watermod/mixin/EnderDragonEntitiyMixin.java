package de.noriskclient.watermod.mixin;

import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(EnderDragonEntity.class)
public class EnderDragonEntitiyMixin {

    @ModifyArg(method = "getNearestPathNodeIndex()I", at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(II)I"), index = 1)
    private int injected(int x) {
        return 100;
    }
}
