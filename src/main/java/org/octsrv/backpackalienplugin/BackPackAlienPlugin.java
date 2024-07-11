package org.octsrv.backpackalienplugin;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;

public final class BackPackAlienPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getServer().getScheduler().runTaskTimer(this, () -> {
            getServer().getOnlinePlayers().forEach(p -> {
                if (playerHasAlien(p))
                    p.addPotionEffect(PotionEffectType.LEVITATION.createEffect(5, 7));
            });
        }, 0, 5);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private boolean playerHasAlien(Player p) {
        return Arrays.stream(p.getInventory().getContents())
            .anyMatch(item ->
                item != null &&
                item.getType() == Material.TOTEM_OF_UNDYING &&
                item.getItemMeta() != null &&
                (
                    item.getItemMeta().getDisplayName().equals("外星人") ||
                    item.getItemMeta().getDisplayName().equalsIgnoreCase("Alien")
                )
            );
    }
}
