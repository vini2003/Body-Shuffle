package vini2003.xyz.bodyshuffle.mixin.common;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vini2003.xyz.bodyshuffle.common.component.BodyPartComponent;
import vini2003.xyz.bodyshuffle.registry.common.BodyShuffleComponents;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
	@Shadow
	public abstract boolean isClimbing();
	
	@Inject(at = @At("RETURN"), method = "getJumpVelocity", cancellable = true)
	public void bodyshuffle_getJumpVelocity(CallbackInfoReturnable<Float> cir) {
		if ((Object) this instanceof PlayerEntity) {
			BodyPartComponent bodyParts = BodyShuffleComponents.BODY_PARTS.get(this);
			
			if (bodyParts.hasJumpIncrease()) {
				if (!bodyParts.hasHead() && !bodyParts.hasTorso() && !bodyParts.hasLeftArm() && !bodyParts.hasRightArm()) {
					float multiplier = 1F;
					
					if (bodyParts.hasLeftLeg()) multiplier += 0.125F;
					if (bodyParts.hasRightLeg()) multiplier += 0.125F;
					
					cir.setReturnValue(cir.getReturnValueF() * multiplier);
					cir.cancel();
					
					return;
				}
			}
			
			if (bodyParts.hasJumpDecrease()) {
				if (!bodyParts.hasHead() && !bodyParts.hasTorso() && !bodyParts.hasLeftLeg() && !bodyParts.hasRightLeg()) {
					float multiplier = 1F;
					
					if (bodyParts.hasLeftArm()) multiplier -= 0.125F;
					if (bodyParts.hasRightArm()) multiplier -= 0.125F;
					
					cir.setReturnValue(cir.getReturnValueF() * multiplier);
					cir.cancel();
					
					return;
				}
			}
		}
	}
	
	@Inject(at = @At("HEAD"), method = "isClimbing", cancellable = true)
	void bodyshuffle_isClimbing(CallbackInfoReturnable<Boolean> cir) {
		if ((Object) this instanceof PlayerEntity) {
			BodyPartComponent bodyParts = BodyShuffleComponents.BODY_PARTS.get(this);
			
			if (bodyParts.hasClimbingLimitations()) {
				if (!bodyParts.hasAnyLeg() && !bodyParts.hasAnyArm()) {
					cir.setReturnValue(false);
					cir.cancel();
				}
			}
		}
	}
	
	@Inject(at = @At("RETURN"), method = "travel")
	void bodyshuffle_travel(Vec3d movementInput, CallbackInfo ci) {
		if ((Object) this instanceof PlayerEntity && isClimbing()) {
			BodyPartComponent bodyParts = BodyShuffleComponents.BODY_PARTS.get(this);
			
			if (bodyParts.hasClimbingLimitations()) {
				PlayerEntity player = (PlayerEntity) (Object) this;
				
				if (!bodyParts.hasAnyArm()) {
					player.setVelocity(player.getVelocity().getX(), player.getVelocity().getY() * 0.75F, player.getVelocity().getZ());
				}
				
				if (!bodyParts.hasAnyLeg()) {
					player.setVelocity(player.getVelocity().getX(), player.getVelocity().getY() * 0.75F, player.getVelocity().getZ());
				}
			}
		}
	}
}
