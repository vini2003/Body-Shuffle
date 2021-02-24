package vini2003.xyz.bodyshuffle.registry.common;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;
import dev.onyxstudios.cca.api.v3.component.ComponentProvider;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class BodyShuffleCommands {
	private static int rightArm(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
		BodyShuffleComponents.BODY_PARTS.get(context.getSource().getPlayer()).setRightArm(BoolArgumentType.getBool(context, "rightArm"));
		BodyShuffleComponents.BODY_PARTS.syncWith(context.getSource().getPlayer(), ComponentProvider.fromEntity(context.getSource().getPlayer()));
		
		return 1;
	}
	
	private static int leftArm(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
		BodyShuffleComponents.BODY_PARTS.get(context.getSource().getPlayer()).setLeftArm(BoolArgumentType.getBool(context, "leftArm"));
		BodyShuffleComponents.BODY_PARTS.syncWith(context.getSource().getPlayer(), ComponentProvider.fromEntity(context.getSource().getPlayer()));
		
		return 1;
	}
	
	private static int rightLeg(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
		BodyShuffleComponents.BODY_PARTS.get(context.getSource().getPlayer()).setRightLeg(BoolArgumentType.getBool(context, "rightLeg"));
		BodyShuffleComponents.BODY_PARTS.syncWith(context.getSource().getPlayer(), ComponentProvider.fromEntity(context.getSource().getPlayer()));
		
		return 1;
	}
	
	private static int leftLeg(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
		BodyShuffleComponents.BODY_PARTS.get(context.getSource().getPlayer()).setLeftLeg(BoolArgumentType.getBool(context, "leftLeg"));
		BodyShuffleComponents.BODY_PARTS.syncWith(context.getSource().getPlayer(), ComponentProvider.fromEntity(context.getSource().getPlayer()));
		
		return 1;
	}
	
	private static int head(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
		BodyShuffleComponents.BODY_PARTS.get(context.getSource().getPlayer()).setHead(BoolArgumentType.getBool(context, "head"));
		BodyShuffleComponents.BODY_PARTS.syncWith(context.getSource().getPlayer(), ComponentProvider.fromEntity(context.getSource().getPlayer()));
		
		return 1;
	}
	
	private static int torso(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
		BodyShuffleComponents.BODY_PARTS.get(context.getSource().getPlayer()).setTorso(BoolArgumentType.getBool(context, "torso"));
		BodyShuffleComponents.BODY_PARTS.syncWith(context.getSource().getPlayer(), ComponentProvider.fromEntity(context.getSource().getPlayer()));
		
		return 1;
	}
	
	
	public static void initialize() {
		CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
			LiteralCommandNode<ServerCommandSource> bodyShuffleRoot = CommandManager.literal("bodyshuffle").build();
			
			LiteralCommandNode<ServerCommandSource> bodyShuffleRightArm =
					CommandManager.literal("right_arm")
							.then(CommandManager.argument("rightArm", BoolArgumentType.bool())
									.executes(BodyShuffleCommands::rightArm)
									.build()
							)
							.build();
			
			LiteralCommandNode<ServerCommandSource> bodyShuffleLeftArm =
					CommandManager.literal("left_arm")
							.then(CommandManager.argument("leftArm", BoolArgumentType.bool())
									.executes(BodyShuffleCommands::leftArm)
									.build()
							)
							.build();
			
			LiteralCommandNode<ServerCommandSource> bodyShuffleRightLeg =
					CommandManager.literal("right_leg")
							.then(CommandManager.argument("rightLeg", BoolArgumentType.bool())
									.executes(BodyShuffleCommands::rightLeg)
									.build()
							)
							.build();
			
			LiteralCommandNode<ServerCommandSource> bodyShuffleLeftLeg =
					CommandManager.literal("left_leg")
							.then(CommandManager.argument("leftLeg", BoolArgumentType.bool())
									.executes(BodyShuffleCommands::leftLeg)
									.build()
							)
							.build();
			
			LiteralCommandNode<ServerCommandSource> bodyShuffleHead =
					CommandManager.literal("head")
							.then(CommandManager.argument("head", BoolArgumentType.bool())
									.executes(BodyShuffleCommands::head)
									.build()
							)
							.build();
			
			LiteralCommandNode<ServerCommandSource> bodyShuffleTorso =
					CommandManager.literal("torso")
							.then(CommandManager.argument("torso", BoolArgumentType.bool())
									.executes(BodyShuffleCommands::torso)
									.build()
							)
							.build();
			
			bodyShuffleRoot.addChild(bodyShuffleRightArm);
			bodyShuffleRoot.addChild(bodyShuffleLeftArm);
			
			bodyShuffleRoot.addChild(bodyShuffleRightLeg);
			bodyShuffleRoot.addChild(bodyShuffleLeftLeg);
			
			bodyShuffleRoot.addChild(bodyShuffleHead);
			
			bodyShuffleRoot.addChild(bodyShuffleTorso);
			
			dispatcher.getRoot().addChild(bodyShuffleRoot);
		});
	}
}
