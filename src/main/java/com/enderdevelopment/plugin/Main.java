package com.enderdevelopment.plugin;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("givecannon")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                ItemStack cannon = new ItemStack(Material.STICK);
                ItemMeta meta = cannon.getItemMeta();
                meta.setDisplayName("Cannon");
                cannon.setItemMeta(meta);
                player.getInventory().addItem(cannon);
                return true;
            }
        }
        return false;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction().toString().contains("RIGHT")) {
            Player player = event.getPlayer();
            ItemStack item = player.getInventory().getItemInMainHand();
            if (item != null && item.getType() == Material.STICK && item.hasItemMeta() && item.getItemMeta().getDisplayName().equals("Cannon")) {
                Snowball snowball = player.launchProjectile(Snowball.class);
                Vector velocity = player.getLocation().getDirection().multiply(2);
                snowball.setVelocity(velocity);
                event.setCancelled(true);
            }
        }
    }
}