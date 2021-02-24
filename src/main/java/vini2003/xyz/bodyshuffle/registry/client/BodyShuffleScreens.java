package vini2003.xyz.bodyshuffle.registry.client;

import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import vini2003.xyz.bodyshuffle.client.screen.BodyPartSelectorScreen;
import vini2003.xyz.bodyshuffle.registry.common.BodyShuffleScreenHandlers;

public class BodyShuffleScreens {
	public static void initialize() {
		ScreenRegistry.register(BodyShuffleScreenHandlers.BODY_PART_SELECTOR, BodyPartSelectorScreen::new);
	}
}
