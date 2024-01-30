package io.github.pc314159.arrowbagplugin_1_20_1.events;

import io.github.pc314159.arrowbagplugin_1_20_1.ArrowBagInventoryHolder;
import io.github.pc314159.arrowbagplugin_1_20_1.ArrowBagPlugin_1_20_1;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Set;


public class ArrowBagInventoryDragEvent implements Listener {
    private final ArrowBagPlugin_1_20_1 plugin;
    private final NamespacedKey key;

    public ArrowBagInventoryDragEvent(ArrowBagPlugin_1_20_1 plugin){
        this.plugin = plugin;
        this.key = plugin.getArrowBagInvKey();
        Bukkit.getLogger().info("ArrowBagInventoryDragEvent registered");
    }

    public boolean isArrow(ItemStack item) {
        return (item.getType().equals(Material.ARROW) ||
                item.getType().equals(Material.SPECTRAL_ARROW) ||
                item.getType().equals(Material.TIPPED_ARROW));
    }

    @EventHandler
    public void onDrag(InventoryDragEvent event) {
        if (event.getInventory().getHolder() instanceof ArrowBagInventoryHolder){
            if (!isArrow(event.getOldCursor())) {
                Set<Integer> slots = event.getRawSlots();
                boolean inChest = false;
                for (Integer i: slots){
                    if ((int) i < event.getInventory().getSize()){
                        inChest = true;
                        break;
                    }
                }
                if (inChest){
//                    Bukkit.getLogger().info("drag cancel");
                    event.setCancelled(true);
                }
            }
        }
    }
}
