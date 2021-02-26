package vini2003.xyz.bodyshuffle.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vini2003.xyz.bodyshuffle.common.component.BodyPartComponent;
import vini2003.xyz.bodyshuffle.registry.common.BodyShuffleComponents;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
	@Shadow
	@Nullable
	public ClientPlayerEntity player;
	
	@Inject(at = @At("HEAD"), method = "doAttack", cancellable = true)
	void bodyshuffle_doAttack(CallbackInfo ci) {
		BodyPartComponent bodyParts = BodyShuffleComponents.BODY_PARTS.get(player);
		
		if (!bodyParts.hasRightArm()) {
			ci.cancel();
		}
	}
}
