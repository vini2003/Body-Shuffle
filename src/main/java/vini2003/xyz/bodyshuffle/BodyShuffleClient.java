package vini2003.xyz.bodyshuffle;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import vini2003.xyz.bodyshuffle.registry.client.BodyShuffleKeybinds;
import vini2003.xyz.bodyshuffle.registry.client.BodyShuffleScreens;
import vini2003.xyz.bodyshuffle.registry.client.BodyShuffleShaders;

@Environment(EnvType.CLIENT)
public class BodyShuffleClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BodyShuffleScreens.initialize();
		BodyShuffleKeybinds.initialize();
		BodyShuffleShaders.initialize();
	}
}
