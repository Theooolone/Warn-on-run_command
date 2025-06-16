package theooolone.warnonruncommand;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;

public class WarnOnRunCommand implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// Register the config file
		AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
	}
}