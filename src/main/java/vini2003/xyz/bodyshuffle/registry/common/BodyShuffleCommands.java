package vini2003.xyz.bodyshuffle.registry.common;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.LongArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;
import dev.onyxstudios.cca.api.v3.component.ComponentProvider;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import vini2003.xyz.bodyshuffle.BodyShuffle;

public class BodyShuffleCommands {
	private static int timer(CommandContext<ServerCommandSource> context) {
		String argument = StringArgumentType.getString(context, "timer");
		
		String type = argument.substring(argument.length() - 1);
		
		long amount = Long.parseLong(argument.substring(0, argument.length() - 1));
		
		switch (type) {
			case "s":
				BodyShuffle.timerSeconds = amount;
				break;
			case "m":
				BodyShuffle.timerSeconds = amount * 60L;
				break;
			case "h":
				BodyShuffle.timerSeconds = amount * 60L * 60L;
				break;
		}
		
		return 1;
	}
	
	private static int pause(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
		BodyShuffle.timerEnabled = false;
		
		context.getSource().getPlayer().sendMessage(new TranslatableText("message.bodyshuffle.pause").formatted(Formatting.GOLD), true);
		
		return 1;
	}
	
	private static int resume(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
		BodyShuffle.timerEnabled = true;
		
		context.getSource().getPlayer().sendMessage(new TranslatableText("message.bodyshuffle.resume").formatted(Formatting.GOLD), true);
		
		return 1;
	}
	
	public static void initialize() {
		CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
			LiteralCommandNode<ServerCommandSource> bodyShuffleRoot = CommandManager.literal("bodyshuffle").build();
			
			LiteralCommandNode<ServerCommandSource> bodyShuffleTimer = CommandManager.literal("timer").then(
					CommandManager.argument("timer", StringArgumentType.string())
							.executes(BodyShuffleCommands::timer)
							.build()
			).build();
			
			LiteralCommandNode<ServerCommandSource> bodyShufflePause = CommandManager.literal("pause").executes(BodyShuffleCommands::pause).build();
			
			LiteralCommandNode<ServerCommandSource> bodyShuffleResume = CommandManager.literal("resume").executes(BodyShuffleCommands::resume).build();
			
			bodyShuffleRoot.addChild(bodyShuffleTimer);
			
			bodyShuffleRoot.addChild(bodyShufflePause);
			bodyShuffleRoot.addChild(bodyShuffleResume);
			
			dispatcher.getRoot().addChild(bodyShuffleRoot);
		});
	}
}
