package vini2003.xyz.bodyshuffle.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vini2003.xyz.bodyshuffle.common.component.BodyPartComponent;
import vini2003.xyz.bodyshuffle.registry.common.BodyShuffleComponents;

@Mixin(ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionManagerMixin {
	@Inject(at = @At("HEAD"), method = "isCurrentlyBreaking", cancellable = true)
	void bodyshuffle_isCurrentlyBreaking(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
		BodyPartComponent bodyParts = BodyShuffleComponents.BODY_PARTS.get(MinecraftClient.getInstance().player);
		
		if (!bodyParts.hasRightArm()) {
			cir.cancel();
		}
	}
}
