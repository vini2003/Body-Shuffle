package vini2003.xyz.bodyshuffle.registry.common;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;
import vini2003.xyz.bodyshuffle.BodyShuffle;

public class BodyShuffleNetworking {
	public static final Identifier OPENED_BODY_PARTY_SELECTOR = BodyShuffle.identifier("body_part_selector");
	
	public static void initialize() {
		ServerPlayNetworking.registerGlobalReceiver(OPENED_BODY_PARTY_SELECTOR, ((minecraftServer, serverPlayerEntity, serverPlayNetworkHandler, packetByteBuf, packetSender) -> {
			serverPlayerEntity.openHandledScreen(BodyShuffleScreenHandlers.BODY_PART_SELECTOR_FACTORY);
		}));
	}
}
