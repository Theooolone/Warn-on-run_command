package theooolone.warnonruncommand;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WarnOnRunCommand implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// Register the config file
		AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
	}
}