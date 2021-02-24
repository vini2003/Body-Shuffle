package vini2003.xyz.bodyshuffle.mixin.client;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vini2003.xyz.bodyshuffle.client.utilities.BodyShuffleClientUtilities;
import vini2003.xyz.bodyshuffle.common.component.BodyPartComponent;
import vini2003.xyz.bodyshuffle.registry.client.BodyShuffleShaders;
import vini2003.xyz.bodyshuffle.registry.common.BodyShuffleComponents;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {
	@Inject(at = @At("RETURN"), method = "setModelPose(Lnet/minecraft/client/network/AbstractClientPlayerEntity;)V")
	void bodyshuffle_setModelPose(AbstractClientPlayerEntity abstractClientPlayerEntity, CallbackInfo ci) {
		BodyPartComponent bodyParts = BodyShuffleComponents.BODY_PARTS.get(BodyShuffleClientUtilities.getPlayer());
		
		PlayerEntityModel model = ((PlayerEntityRenderer) (Object) this).getModel();
		
		model.head.visible = bodyParts.hasHead();
		model.helmet.visible = bodyParts.hasHead();
		
		model.jacket.visible = bodyParts.hasTorso();
		model.torso.visible = bodyParts.hasTorso();
		
		model.rightArm.visible = bodyParts.hasRightArm();
		model.rightSleeve.visible = bodyParts.hasRightArm();
		
		model.leftArm.visible = bodyParts.hasLeftArm();
		model.leftSleeve.visible = bodyParts.hasLeftArm();
		
		model.rightLeg.visible = bodyParts.hasRightLeg();
		model.rightPantLeg.visible = bodyParts.hasRightLeg();
		
		model.leftLeg.visible = bodyParts.hasLeftLeg();
		model.leftPantLeg.visible = bodyParts.hasLeftLeg();
		
		BodyShuffleShaders.enableBlur = !bodyParts.hasHead() && bodyParts.hasBlur();
	}
}
