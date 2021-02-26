package vini2003.xyz.bodyshuffle;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import vini2003.xyz.bodyshuffle.registry.common.*;

public class BodyShuffle implements ModInitializer {
	public static final String ID = "bodyshuffle";
	
	public static boolean timerEnabled = true;
	
	public static long timerSeconds = 300L;
	
	public static Identifier identifier(String path) {
		return new Identifier(ID, path);
	}
	
	@Override
	public void onInitialize() {
		BodyShuffleCommands.initialize();
		BodyShuffleCallbacks.initialize();
		BodyShuffleComponents.initialize();
		BodyShuffleScreenHandlers.initialize();
		BodyShuffleNetworking.initialize();
	}
}
