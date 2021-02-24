package vini2003.xyz.bodyshuffle.mixin.common;

import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vini2003.xyz.bodyshuffle.common.component.BodyPartComponent;
import vini2003.xyz.bodyshuffle.registry.common.BodyShuffleComponents;

import java.util.Map;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
	@Shadow
	public abstract EntityDimensions getDimensions(EntityPose pose);
	
	@Shadow
	@Final
	private static Map<EntityPose, EntityDimensions> POSE_DIMENSIONS;
	
	@Shadow
	@Final
	public static EntityDimensions STANDING_DIMENSIONS;
	
	@Inject(at = @At("RETURN"), method = "getDimensions", cancellable = true)
	void bodyshuffle_getDimensions(EntityPose pose, CallbackInfoReturnable<EntityDimensions> cir) {
		if (pose == EntityPose.STANDING || pose == EntityPose.CROUCHING) {
			EntityDimensions dimensions = POSE_DIMENSIONS.get(pose);
			
			BodyPartComponent bodyParts = BodyShuffleComponents.BODY_PARTS.get(this);
			
			cir.setReturnValue(EntityDimensions.changing(dimensions.width, bodyParts.getHeight(dimensions)));
			cir.cancel();
		}
	}
	
	@Inject(at = @At("RETURN"), method = "getActiveEyeHeight", cancellable = true)
	void bodyshuffle_getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions, CallbackInfoReturnable<Float> cir) {
		if (pose == EntityPose.STANDING || pose == EntityPose.CROUCHING) {
			cir.setReturnValue(dimensions.height * 0.85F);
			cir.cancel();
		}
	}
	
	@Inject(at = @At("RETURN"), method = "getMovementSpeed", cancellable = true)
	void bodyshuffle_getMovementSpeed(CallbackInfoReturnable<Float> cir) {
		if ((Object) this instanceof PlayerEntity) {
			BodyPartComponent bodyParts = BodyShuffleComponents.BODY_PARTS.get(this);
			
			if (bodyParts.hasSpeedIncrease()) {
				if (!bodyParts.hasHead() && !bodyParts.hasTorso() && !bodyParts.hasLeftArm() && !bodyParts.hasRightArm()) {
					float multiplier = 1F;
					
					if (bodyParts.hasLeftLeg()) multiplier += 0.125;
					if (bodyParts.hasRightLeg()) multiplier += 0.125F;
					
					cir.setReturnValue(cir.getReturnValueF() * multiplier);
					cir.cancel();
					
					return;
				}
			}
			
			if (bodyParts.hasSpeedDecrease()) {
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
	
	@Inject(at = @At("HEAD"), method = "isBlockBreakingRestricted", cancellable = true)
	void bodyshuffle_isBlockBreakingRestricted(World world, BlockPos pos, GameMode gameMode, CallbackInfoReturnable<Boolean> cir) {
		BodyPartComponent bodyParts = BodyShuffleComponents.BODY_PARTS.get(this);
		
		if (!bodyParts.hasRightArm()) {
			cir.setReturnValue(true);
			cir.cancel();
		}
	}
}
