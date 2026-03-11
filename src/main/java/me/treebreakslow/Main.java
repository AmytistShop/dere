package me.treebreakslow;

import org.bukkit.Tag;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    private double multiplier;

    @Override
    public void onEnable() {

        saveDefaultConfig();
        multiplier = getConfig().getDouble("tree-break-multiplier", 2.0);

        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onBlockDamage(BlockDamageEvent event) {

        if (!Tag.LOGS.isTagged(event.getBlock().getType())) return;

        if (event.getInstaBreak()) return;

        // уменьшает скорость ломания
        if (multiplier > 1) {
            event.setCancelled(true);
        }
    }
}
