package vini2003.xyz.bodyshuffle.common.component;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.entity.PlayerComponent;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import vini2003.xyz.bodyshuffle.common.miscellaneous.BodyPart;

public class BodyPartComponent implements PlayerComponent<BodyPartComponent>, AutoSyncedComponent {
	private boolean leftArm = true;
	private boolean rightArm = true;
	private boolean leftLeg = true;
	private boolean rightLeg = true;
	private boolean head = true;
	private boolean torso = true;
	
	private boolean blur = true;
	
	private boolean randomizeLeftArm = true;
	private boolean randomizeRightArm = true;
	private boolean randomizeLeftLeg = true;
	private boolean randomizeRightLeg = true;
	private boolean randomizeHead = true;
	private boolean randomizeTorso = true;
	
	private boolean speedIncrease = true;
	private boolean speedDecrease = true;
	
	private boolean jumpIncrease = true;
	private boolean jumpDecrease = true;
	
	private boolean singleHandLimitations = true;
	private boolean noHandLimitations = true;
	
	private boolean climbingLimitations = true;
	
	private final PlayerEntity player;
	
	public BodyPartComponent(PlayerEntity player) {
		this.player = player;
	}
	
	@Override
	public void readFromNbt(CompoundTag compoundTag) {
		setLeftArm(compoundTag.getBoolean("LeftArm"));
		setRightArm(compoundTag.getBoolean("RightArm"));
		setLeftLeg(compoundTag.getBoolean("LeftLeg"));
		setRightLeg(compoundTag.getBoolean("RightLeg"));
		setHead(compoundTag.getBoolean("Head"));
		setTorso(compoundTag.getBoolean("Torso"));
		
		setBlur(compoundTag.getBoolean("Blur"));
		
		setRandomizeLeftArm(compoundTag.getBoolean("RandomizeLeftArm"));
		setRandomizeRightArm(compoundTag.getBoolean("RandomizeRightArm"));
		setRandomizeLeftLeg(compoundTag.getBoolean("RandomizeLeftLeg"));
		setRandomizeRightLeg(compoundTag.getBoolean("RandomizeRightLeg"));
		setRandomizeHead(compoundTag.getBoolean("RandomizeHead"));
		setRandomizeTorso(compoundTag.getBoolean("RandomizeTorso"));
		
		setSpeedIncrease(compoundTag.getBoolean("SpeedIncrease"));
		setSpeedDecrease(compoundTag.getBoolean("SpeedDecrease"));
		
		setJumpIncrease(compoundTag.getBoolean("JumpIncrease"));
		setJumpDecrease(compoundTag.getBoolean("JumpDecrease"));
		
		setSingleHandLimitations(compoundTag.getBoolean("SingleHandLimitations"));
		setNoHandLimitations(compoundTag.getBoolean("NoHandLimitations"));
		
		setClimbingLimitations(compoundTag.getBoolean("ClimbingLimitations"));
	}
	
	@Override
	public void writeToNbt(CompoundTag compoundTag) {
		compoundTag.putBoolean("LeftArm", leftArm);
		compoundTag.putBoolean("RightArm", rightArm);
		compoundTag.putBoolean("LeftLeg", leftLeg);
		compoundTag.putBoolean("RightLeg", rightLeg);
		compoundTag.putBoolean("Head", head);
		compoundTag.putBoolean("Torso", torso);
		
		compoundTag.putBoolean("Blur", blur);
		
		compoundTag.putBoolean("RandomizeLeftArm", randomizeLeftArm);
		compoundTag.putBoolean("RandomizeRightArm", randomizeRightArm);
		compoundTag.putBoolean("RandomizeLeftLeg", randomizeLeftLeg);
		compoundTag.putBoolean("RandomizeRightLeg", randomizeRightLeg);
		compoundTag.putBoolean("RandomizeHead", randomizeHead);
		compoundTag.putBoolean("RandomizeTorso", randomizeTorso);
		
		compoundTag.putBoolean("SpeedIncrease", speedIncrease);
		compoundTag.putBoolean("SpeedDecrease", speedDecrease);
		
		compoundTag.putBoolean("JumpIncrease", jumpIncrease);
		compoundTag.putBoolean("JumpDecrease", jumpDecrease);
		
		compoundTag.putBoolean("SingleHandLimitations", singleHandLimitations);
		compoundTag.putBoolean("NoHandLimitations", noHandLimitations);
		
		compoundTag.putBoolean("ClimbingLimitations", climbingLimitations);
	}
	
	public void updateBoundingBox() {
		player.calculateDimensions();
		player.updatePosition(player.getX(), player.getY(), player.getZ());
	}
	
	public boolean hasLeftArm() {
		return leftArm;
	}
	
	public void setLeftArm(boolean leftArm) {
		this.leftArm = leftArm;
		updateBoundingBox();
	}
	
	public boolean hasRightArm() {
		return rightArm;
	}
	
	public void setRightArm(boolean rightArm) {
		this.rightArm = rightArm;
		updateBoundingBox();
	}
	
	public boolean hasAnyArm() {
		return hasLeftArm() || hasRightArm();
	}
	
	public boolean hasLeftLeg() {
		return leftLeg;
	}
	
	public void setLeftLeg(boolean leftLeg) {
		this.leftLeg = leftLeg;
		updateBoundingBox();
	}
	
	public boolean hasRightLeg() {
		return rightLeg;
	}
	
	public void setRightLeg(boolean rightLeg) {
		this.rightLeg = rightLeg;
		updateBoundingBox();
	}
	
	public boolean hasAnyLeg() {
		return hasLeftLeg() || hasRightLeg();
	}
	
	public boolean hasHead() {
		return head;
	}
	
	public void setHead(boolean head) {
		this.head = head;
		updateBoundingBox();
	}
	
	public boolean hasTorso() {
		return torso;
	}
	
	public void setTorso(boolean torso) {
		this.torso = torso;
		updateBoundingBox();
	}
	
	public boolean hasBlur() {
		return blur;
	}
	
	public void setBlur(boolean blur) {
		this.blur = blur;
	}
	
	public boolean shouldRandomizeLeftArm() {
		return randomizeLeftArm;
	}
	
	public void setRandomizeLeftArm(boolean randomizeLeftArm) {
		this.randomizeLeftArm = randomizeLeftArm;
	}
	
	public boolean shouldRandomizeRightArm() {
		return randomizeRightArm;
	}
	
	public void setRandomizeRightArm(boolean randomizeRightArm) {
		this.randomizeRightArm = randomizeRightArm;
	}
	
	public boolean shouldRandomizeLeftLeg() {
		return randomizeLeftLeg;
	}
	
	public void setRandomizeLeftLeg(boolean randomizeLeftLeg) {
		this.randomizeLeftLeg = randomizeLeftLeg;
	}
	
	public boolean shouldRandomizeRightLeg() {
		return randomizeRightLeg;
	}
	
	public void setRandomizeRightLeg(boolean randomizeRightLeg) {
		this.randomizeRightLeg = randomizeRightLeg;
	}
	
	public boolean shouldRandomizeHead() {
		return randomizeHead;
	}
	
	public void setRandomizeHead(boolean randomizeHead) {
		this.randomizeHead = randomizeHead;
	}
	
	public boolean shouldRandomizeTorso() {
		return randomizeTorso;
	}
	
	public void setRandomizeTorso(boolean randomizeTorso) {
		this.randomizeTorso = randomizeTorso;
	}
	
	public boolean hasSpeedIncrease() {
		return speedIncrease;
	}
	
	public void setSpeedIncrease(boolean speedIncrease) {
		this.speedIncrease = speedIncrease;
	}
	
	public boolean hasSpeedDecrease() {
		return speedDecrease;
	}
	
	public void setSpeedDecrease(boolean speedDecrease) {
		this.speedDecrease = speedDecrease;
	}
	
	public boolean hasJumpIncrease() {
		return jumpIncrease;
	}
	
	public void setJumpIncrease(boolean jumpIncrease) {
		this.jumpIncrease = jumpIncrease;
	}
	
	public boolean hasJumpDecrease() {
		return jumpDecrease;
	}
	
	public void setJumpDecrease(boolean jumpDecrease) {
		this.jumpDecrease = jumpDecrease;
	}
	
	public boolean hasSingleHandLimitations() {
		return singleHandLimitations;
	}
	
	public void setSingleHandLimitations(boolean singleHandLimitations) {
		this.singleHandLimitations = singleHandLimitations;
	}
	
	public boolean hasNoHandLimitations() {
		return noHandLimitations;
	}
	
	public void setNoHandLimitations(boolean noHandLimitations) {
		this.noHandLimitations = noHandLimitations;
	}
	
	public boolean hasClimbingLimitations() {
		return climbingLimitations;
	}
	
	public void setClimbingLimitations(boolean climbingLimitations) {
		this.climbingLimitations = climbingLimitations;
	}
	
	public float getHeight(EntityDimensions dimensions) {
		float newHeight = dimensions.height;
		
		float headHeight = 0.25F * dimensions.height;
		float armHeight = 0.375F * dimensions.height;
		float legHeight = 0.368F * dimensions.height;
		
		if (!hasHead()) {
			newHeight -= headHeight;
		}
		
		if (!hasAnyArm() && !hasTorso() && !hasAnyLeg()) {
			newHeight -= armHeight;
		} else if (!hasHead() && !hasAnyArm() && !hasTorso() && hasAnyLeg()) {
			newHeight -= armHeight;
		}
		
		if (!hasAnyLeg()) {
			newHeight -= legHeight;
		}
		
		return Math.max(0.125F, newHeight);
	}
	
	public void toggle(BodyPart part, boolean enabled) {
		switch (part) {
			case HEAD: {
				head = enabled;
				break;
			}
			
			case TORSO: {
				torso = enabled;
				break;
			}
			
			case LEFT_ARM: {
				leftArm = enabled;
				break;
			}
			
			case RIGHT_ARM: {
				rightArm = enabled;
				break;
			}
			
			case LEFT_LEG: {
				leftLeg = enabled;
				break;
			}
			
			case RIGHT_LEG: {
				rightLeg = enabled;
				break;
			}
		}
	}
}
