package de.noriskclient.watermod;

import de.noriskclient.watermod.feature.StoneSpiralFeature;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

public class WaterMod implements ModInitializer {
	private static final Feature<DefaultFeatureConfig> STONE_SPIRAL = new StoneSpiralFeature(DefaultFeatureConfig.CODEC);
	public static final ConfiguredFeature<?, ?> STONE_SPIRAL_CONFIGURED = STONE_SPIRAL.configure(FeatureConfig.DEFAULT)
			.createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(5)));

	@Override
	public void onInitialize() {

	}

}
