package io.github.pc314159.arrowbagplugin_1_20_1.events;

import io.github.pc314159.arrowbagplugin_1_20_1.ArrowBagInventoryHolder;
import io.github.pc314159.arrowbagplugin_1_20_1.ArrowBagPlugin_1_20_1;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ArrowBagInventoryClickEvent implements Listener {
    private final ArrowBagPlugin_1_20_1 plugin;
    private final NamespacedKey key;

    public ArrowBagInventoryClickEvent(ArrowBagPlugin_1_20_1 plugin){
        this.plugin = plugin;
        this.key = plugin.getArrowBagInvKey();
        Bukkit.getLogger().info("ArrowBagInventoryClickEvent registered");
    }

    public boolean isPlacing(InventoryAction ia) {
        return (ia.equals(InventoryAction.PLACE_ALL) ||
                ia.equals(InventoryAction.PLACE_ONE) ||
                ia.equals(InventoryAction.PLACE_SOME) ||
                ia.equals(InventoryAction.SWAP_WITH_CURSOR));
    }

    public boolean isArrow(ItemStack item) {
        return (item != null &&
                item.getType().equals(Material.ARROW) ||
                item.getType().equals(Material.SPECTRAL_ARROW) ||
                item.getType().equals(Material.TIPPED_ARROW));
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getClickedInventory() != null && event.getClickedInventory().getHolder() instanceof ArrowBagInventoryHolder){
            if (isPlacing(event.getAction()) && !isArrow(event.getCursor())){
//                Bukkit.getLogger().info("isplacing true");
                event.setCancelled(true);
            } else if (event.getClick().equals(ClickType.NUMBER_KEY)){
                int slot = event.getSlot();
                int hotbarslot = event.getHotbarButton();
                if (slot < event.getInventory().getSize() && !isArrow(event.getWhoClicked().getInventory().getItem(hotbarslot))){
//                    Bukkit.getLogger().info("move by number");
                    event.setCancelled(true);
                }
            }
        } else if (event.getInventory().getHolder() instanceof ArrowBagInventoryHolder) {
//            Bukkit.getLogger().info("notclick but inventory is abi");
            if (event.getAction().equals(InventoryAction.MOVE_TO_OTHER_INVENTORY) && !isArrow(event.getCurrentItem())) {
//                Bukkit.getLogger().info("moveto cancelled");
                event.setCancelled(true);
            }
        }
    }
}
