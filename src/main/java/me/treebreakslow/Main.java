package me.treebreakslow;

import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Main extends JavaPlugin implements Listener {

    private final Set<UUID> cooldown = new HashSet<>();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onBlockDamage(BlockDamageEvent event) {

        if (!Tag.LOGS.isTagged(event.getBlock().getType())) return;

        UUID player = event.getPlayer().getUniqueId();

        if (!cooldown.contains(player)) {

            cooldown.add(player);

            event.setCancelled(true);

            getServer().getScheduler().runTaskLater(this, () ->
                    cooldown.remove(player), 4L);

        }
    }
}
