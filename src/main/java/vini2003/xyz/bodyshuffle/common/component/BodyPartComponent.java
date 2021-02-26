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
	}
	
	@Override
	public void writeToNbt(CompoundTag compoundTag) {
		compoundTag.putBoolean("LeftArm", leftArm);
		compoundTag.putBoolean("RightArm", rightArm);
		compoundTag.putBoolean("LeftLeg", leftLeg);
		compoundTag.putBoolean("RightLeg", rightLeg);
		compoundTag.putBoolean("Head", head);
		compoundTag.putBoolean("Torso", torso);
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
