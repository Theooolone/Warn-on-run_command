package theooolone.warnonruncommand.mixin;

import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.*;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import theooolone.warnonruncommand.ModConfig;

import java.util.ArrayList;

@Mixin(Style.class)
public class StyleMixin {
	@Inject(at = @At("RETURN"), method = "getClickEvent", cancellable = true)
	// When a click event is read
	private void getClickEvent(CallbackInfoReturnable<ClickEvent> cir) {

		ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

		if (!config.enabled) {return;}

		// Test if the click event is a RunCommand click event
		if (cir.getReturnValue() instanceof ClickEvent.RunCommand runCommand) {

			String command = runCommand.command();

			if (isWhitelistedCommand(command, config.whitelist)) {return;}

			MinecraftClient client = MinecraftClient.getInstance();

			// Add a warning with a SuggestCommand style in case the player wanted to run that command
			client.inGameHud.getChatHud().addMessage(
					Text.translatable("warnonruncommand.warn").setStyle(Style.EMPTY.withColor(Formatting.RED))
							.append(Text.literal(command).setStyle(Style.EMPTY
									.withColor(Formatting.YELLOW)
									.withHoverEvent(new HoverEvent.ShowText(Text.translatable("warnonruncommand.tooltip")))
									.withClickEvent(new ClickEvent.SuggestCommand(command))
							))
			);

			// Return no click event, so click event logic isn't handled.
			cir.setReturnValue(null);
		}
	}

	@Unique
	private boolean isWhitelistedCommand(String string, ArrayList<String> whitelist) {
		if (string.startsWith("/")) {
			string = string.substring(1);
		}
		return whitelist.contains(string.split(" ")[0]);
	}
}