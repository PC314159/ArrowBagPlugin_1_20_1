package io.github.pc314159.arrowbagplugin_1_20_1.events;

import io.github.pc314159.arrowbagplugin_1_20_1.ArrowBagInventoryData;
import io.github.pc314159.arrowbagplugin_1_20_1.ArrowBagInventoryDataType;
import io.github.pc314159.arrowbagplugin_1_20_1.ArrowBagInventoryHolder;
import io.github.pc314159.arrowbagplugin_1_20_1.ArrowBagPlugin_1_20_1;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;


public class ArrowBagPlaceEvent implements Listener {
    private final ArrowBagPlugin_1_20_1 plugin;
    private final NamespacedKey sizekey;
    private final NamespacedKey invkey;

    public ArrowBagPlaceEvent(ArrowBagPlugin_1_20_1 plugin){
        this.plugin = plugin;
        this.sizekey = plugin.getArrowBagSizeKey();
        this.invkey = plugin.getArrowBagInvKey();
        Bukkit.getLogger().info("ArrowBagPlaceEvent registered");
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player plr = event.getPlayer();
        ItemStack item = event.getItemInHand();

        if (item.getType().equals(Material.CHEST)) {
            ItemMeta meta = item.getItemMeta();
            PersistentDataContainer itempdc = meta.getPersistentDataContainer();
            int capacity = 0;
            try {
                capacity = itempdc.get(this.sizekey, PersistentDataType.INTEGER);
            } catch (NullPointerException e){
                // not a custom chest
            }
            if (capacity > 0 && capacity % 9 == 0){
                event.setCancelled(true);
                ArrowBagInventoryData abid = null;
                try {
                     abid = itempdc.get(this.invkey, new ArrowBagInventoryDataType());
                } catch (NullPointerException e){
                    // no inv data
                }
                if (abid != null) {
                    Bukkit.getLogger().info("abid not null");
                    ArrowBagInventoryHolder abih = new ArrowBagInventoryHolder(capacity,item);
                    abih.loadData(abid.getInv());
                    plr.openInventory(abih.getInventory());
                } else {
                    Bukkit.getLogger().info("abid is null");
                }
            }
        }
    }
}
