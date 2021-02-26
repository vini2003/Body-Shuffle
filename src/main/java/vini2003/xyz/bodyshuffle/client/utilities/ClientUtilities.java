package vini2003.xyz.bodyshuffle.client.utilities;

import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvents;
import vini2003.xyz.blade.client.utilities.Instances;

public class ClientUtilities {
	public static void playButtonSound() {
		Instances.Companion.client().getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 1.0F));
	}
}
