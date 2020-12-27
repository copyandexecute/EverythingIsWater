package de.noriskclient.watermod.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Block.class)
public class BlockMixin {

    @Shadow
    private BlockState defaultState;

    /**
     * @reason cuz
     * @author NoRisk
     */
    @Overwrite
    public final BlockState getDefaultState() {
        if (this.defaultState != null) {
            if ("Block{minecraft:cave_air}".equalsIgnoreCase(this.defaultState.toString())) {
                return Blocks.WATER.getDefaultState();
            }
            return this.defaultState;
        }
        return defaultState;
    }
}
