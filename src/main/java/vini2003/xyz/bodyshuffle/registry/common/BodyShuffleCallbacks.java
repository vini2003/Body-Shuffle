package vini2003.xyz.bodyshuffle.registry.common;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import vini2003.xyz.bodyshuffle.BodyShuffle;
import vini2003.xyz.bodyshuffle.common.component.BodyPartComponent;
import vini2003.xyz.bodyshuffle.common.component.TimerComponent;
import vini2003.xyz.bodyshuffle.common.screenhandler.BodyPartSelectorScreenHandler;

public class BodyShuffleCallbacks {
	public static void initialize() {
		UseBlockCallback.EVENT.register(((playerEntity, world, hand, blockHitResult) -> {
			BodyPartComponent bodyParts = BodyShuffleComponents.BODY_PARTS.get(playerEntity);
			
			Block block = world.getBlockState(blockHitResult.getBlockPos()).getBlock();
			
			if (!bodyParts.hasLeftArm() && !bodyParts.hasRightArm()) {
				return ActionResult.FAIL;
			}
			
			if (block == Blocks.CRAFTING_TABLE && (!bodyParts.hasLeftArm() || !bodyParts.hasRightArm())) {
				return ActionResult.FAIL;
			} else if ((hand == Hand.MAIN_HAND && !bodyParts.hasRightArm() && !playerEntity.getStackInHand(hand).isEmpty()) || (hand == Hand.OFF_HAND && !bodyParts.hasLeftArm())) {
				return ActionResult.FAIL;
			}
			
			return ActionResult.PASS;
		}));
		
		ServerTickEvents.END_SERVER_TICK.register((server) -> {
			server.getPlayerManager().getPlayerList().forEach(player -> {
				TimerComponent timer = BodyShuffleComponents.TIMER.get(player);
				
				if (BodyShuffle.timerEnabled && timer.hasSeconds(BodyShuffle.timerSeconds) && !(player.currentScreenHandler instanceof BodyPartSelectorScreenHandler)) {
					timer.reset();
					
					BodyPartComponent bodyParts = BodyShuffleComponents.BODY_PARTS.get(player);
					
					bodyParts.setHead(server.getWorld(World.OVERWORLD).getRandom().nextBoolean());
					bodyParts.setTorso(server.getWorld(World.OVERWORLD).getRandom().nextBoolean());
					bodyParts.setLeftArm(server.getWorld(World.OVERWORLD).getRandom().nextBoolean());
					bodyParts.setRightArm(server.getWorld(World.OVERWORLD).getRandom().nextBoolean());
					bodyParts.setLeftLeg(server.getWorld(World.OVERWORLD).getRandom().nextBoolean());
					bodyParts.setRightLeg(server.getWorld(World.OVERWORLD).getRandom().nextBoolean());
					
					BodyShuffleComponents.BODY_PARTS.sync(player);
				}
			});
		});
	}
}
