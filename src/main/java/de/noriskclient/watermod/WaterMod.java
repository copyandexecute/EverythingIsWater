package de.noriskclient.watermod;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;

public class WaterMod implements ModInitializer {
	
	@Override
	public void onInitialize() {
		new Thread(() -> {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			MinecraftClient.getInstance().options.gamma = 10.0;
		}).start();
	}

}
