package vini2003.xyz.bodyshuffle.registry.client;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.network.PacketByteBuf;
import org.lwjgl.glfw.GLFW;
import vini2003.xyz.bodyshuffle.registry.common.BodyShuffleNetworking;

public class BodyShuffleKeybinds {
	public static final KeyBinding keyBodyPartSelectorScreen = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.bodyshuffle.open_body_part_selector", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_N, "category.bodyshuffle.bodyshuffle"));
	
	public static void initialize() {
		ClientTickEvents.END_CLIENT_TICK.register((client) -> {
			if (keyBodyPartSelectorScreen.wasPressed()) {
				ClientPlayNetworking.send(BodyShuffleNetworking.OPENED_BODY_PARTY_SELECTOR, new PacketByteBuf(Unpooled.buffer()));
			}
		});
	}
}
