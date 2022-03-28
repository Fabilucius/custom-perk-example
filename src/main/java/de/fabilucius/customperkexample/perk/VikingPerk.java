package de.fabilucius.customperkexample.perk;

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
