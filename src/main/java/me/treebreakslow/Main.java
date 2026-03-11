package me.treebreakslow;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Set;

public class Main extends JavaPlugin implements Listener {

    private final Set<Material> logs = Set.of(
            Material.OAK_LOG,
            Material.SPRUCE_LOG,
            Material.BIRCH_LOG,
            Material.JUNGLE_LOG,
            Material.ACACIA_LOG,
            Material.DARK_OAK_LOG,
            Material.MANGROVE_LOG,
            Material.CHERRY_LOG
    );

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onBlockDamage(BlockDamageEvent event) {

        Block block = event.getBlock();

        if (!logs.contains(block.getType())) return;

        event.setCancelled(true);

        block.getWorld().getScheduler().runTaskLater(this, () -> {
            block.breakNaturally(event.getPlayer().getInventory().getItemInMainHand());
        }, 10L);
    }
}
