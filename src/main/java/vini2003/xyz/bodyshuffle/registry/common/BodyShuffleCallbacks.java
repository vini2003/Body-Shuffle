package vini2003.xyz.bodyshuffle.registry.common;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;
import vini2003.xyz.bodyshuffle.common.component.BodyPartComponent;
import vini2003.xyz.bodyshuffle.common.component.TimerComponent;
import vini2003.xyz.bodyshuffle.common.screenhandler.BodyPartSelectorScreenHandler;

public class BodyShuffleCallbacks {
	public static void initialize() {
		UseBlockCallback.EVENT.register(((playerEntity, world, hand, blockHitResult) -> {
			BodyPartComponent bodyParts = BodyShuffleComponents.BODY_PARTS.get(playerEntity);
			
			Block block = world.getBlockState(blockHitResult.getBlockPos()).getBlock();
			
			if (bodyParts.hasSingleHandLimitations() && block == Blocks.CRAFTING_TABLE && (!bodyParts.hasLeftArm() || !bodyParts.hasRightArm())) {
				return ActionResult.FAIL;
			} else if (bodyParts.hasNoHandLimitations() && !bodyParts.hasAnyArm()) {
				return ActionResult.FAIL;
			}
			
			return ActionResult.PASS;
		}));
		
		ServerTickEvents.END_SERVER_TICK.register((server) -> {
			server.getPlayerManager().getPlayerList().forEach(player -> {
				TimerComponent timer = BodyShuffleComponents.TIMER.get(player);
				
				if (timer.hasMinutes(5) && !(player.currentScreenHandler instanceof BodyPartSelectorScreenHandler)) {
					timer.reset();
					
					BodyPartComponent bodyParts = BodyShuffleComponents.BODY_PARTS.get(player);
					
					if (bodyParts.shouldRandomizeHead())
						bodyParts.setHead(server.getWorld(World.OVERWORLD).getRandom().nextBoolean());
					if (bodyParts.shouldRandomizeTorso())
						bodyParts.setTorso(server.getWorld(World.OVERWORLD).getRandom().nextBoolean());
					if (bodyParts.shouldRandomizeLeftArm())
						bodyParts.setLeftArm(server.getWorld(World.OVERWORLD).getRandom().nextBoolean());
					if (bodyParts.shouldRandomizeRightArm())
						bodyParts.setRightArm(server.getWorld(World.OVERWORLD).getRandom().nextBoolean());
					if (bodyParts.shouldRandomizeLeftLeg())
						bodyParts.setLeftLeg(server.getWorld(World.OVERWORLD).getRandom().nextBoolean());
					if (bodyParts.shouldRandomizeRightLeg())
						bodyParts.setRightLeg(server.getWorld(World.OVERWORLD).getRandom().nextBoolean());
					
					BodyShuffleComponents.BODY_PARTS.sync(player);
				}
			});
		});
	}
}
