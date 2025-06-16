package theooolone.warnonruncommand;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

import java.util.ArrayList;

@Config(name = "warn-on-run_command")
public class ModConfig implements ConfigData {
	public boolean enabled = true;
	public ArrayList<String> whitelist = new ArrayList<>();
}
