# custom-perk-example
Extend one of the four AbstractPerk types (AbstractListenerPerk, AbstractEffectPerk, AbstractCommandPerk or AbstractTaskPerk)

!!!Important is to have no parameter in the constructor of the class!!!
```java 
import de.fabilucius.advancedperks.api.AdvancedPerksApi;
import de.fabilucius.advancedperks.data.PerkData;
import de.fabilucius.advancedperks.perks.types.AbstractListenerPerk;
import de.fabilucius.advancedperks.sympel.item.builder.types.ItemStackBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class VikingPerk extends AbstractListenerPerk {

    private final AdvancedPerksApi advancedPerksApi = AdvancedPerksApi.getInstance();

    public VikingPerk() {
        super("Viking", "&c&lViking Perk", "perk.use.viking",
                Arrays.asList("&8&oThe viking perk will make the player do more", "&8&0damage with axes."));
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getDamager();
        PerkData perkData = advancedPerksApi.getPerkData(player);
        if (perkData.isPerkActivated(this)) {
            if (event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK) &&
                    player.getInventory().getItemInMainHand().getType().name().contains("AXE")) {
                double multiplier = 1.25;
                double damage = event.getDamage() * multiplier;
                event.setDamage(damage);
            }
        }
    }

    @Override
    public ItemStack getDefaultIcon() {
        return ItemStackBuilder.fromMaterial(Material.DIAMOND_AXE)
                .setDisplayName(this.getDisplayName())
                .setDescription(this.getDescription())
                .build();
    }
}
```

After creating the perk you need to register it with the Api (At least AdvancedPerks version 2.8.4 is needed for that)

```java 
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
```

And dont forget to add AdvancedPerks as depend in the plugin.yml
