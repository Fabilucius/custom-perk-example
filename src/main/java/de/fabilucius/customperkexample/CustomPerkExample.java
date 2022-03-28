package de.fabilucius.customperkexample;

import de.fabilucius.advancedperks.api.AdvancedPerksApi;
import de.fabilucius.customperkexample.perk.VikingPerk;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomPerkExample extends JavaPlugin {

    private final AdvancedPerksApi advancedPerksApi = AdvancedPerksApi.getInstance();

    @Override
    public void onEnable() {
        advancedPerksApi.registerPerk(VikingPerk.class);
    }
}
