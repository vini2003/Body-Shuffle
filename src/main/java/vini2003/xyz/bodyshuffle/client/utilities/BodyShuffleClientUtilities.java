package vini2003.xyz.bodyshuffle.client.utilities;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvents;
import vini2003.xyz.blade.client.utilities.Instances;

public class BodyShuffleClientUtilities {
	public static PlayerEntity getPlayer() {
		return MinecraftClient.getInstance().player;
	}
	
	public static void playButtonSound() {
		Instances.Companion.client().getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 1.0F));
	}
}
