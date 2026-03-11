package me.treebreakslow;

import org.bukkit.Tag;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakSpeedEvent;
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
    public void onBreakSpeed(BlockBreakSpeedEvent event) {

        if (!Tag.LOGS.isTagged(event.getBlock().getType())) return;

        float speed = event.getNewSpeed();

        event.setNewSpeed((float)(speed / multiplier));
    }
}
