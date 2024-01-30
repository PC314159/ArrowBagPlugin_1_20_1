package io.github.pc314159.arrowbagplugin_1_20_1.events;

import io.github.pc314159.arrowbagplugin_1_20_1.ArrowBagInventoryData;
import io.github.pc314159.arrowbagplugin_1_20_1.ArrowBagInventoryDataType;
import io.github.pc314159.arrowbagplugin_1_20_1.ArrowBagInventoryHolder;
import io.github.pc314159.arrowbagplugin_1_20_1.ArrowBagPlugin_1_20_1;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;


public class ArrowBagInventoryCloseEvent implements Listener {
    private final ArrowBagPlugin_1_20_1 plugin;
    private final NamespacedKey sizekey;
    private final NamespacedKey invkey;

    public ArrowBagInventoryCloseEvent(ArrowBagPlugin_1_20_1 plugin){
        this.plugin = plugin;
        this.sizekey = plugin.getArrowBagSizeKey();
        this.invkey = plugin.getArrowBagInvKey();
        Bukkit.getLogger().info("ArrowBagInventoryCloseEvent registered");
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        if (event.getInventory().getHolder() instanceof ArrowBagInventoryHolder){
            ArrowBagInventoryHolder abih = (ArrowBagInventoryHolder) event.getInventory().getHolder();
            ItemStack item = abih.getItem();
            ItemMeta meta = item.getItemMeta();
            PersistentDataContainer pdc = meta.getPersistentDataContainer();
            ItemStack[] contents = abih.getInventory().getContents();
            Bukkit.getLogger().info("closingWith");
            for (ItemStack content: contents){
                Bukkit.getLogger().info("content "+content);
            }
            pdc.set(this.invkey, new ArrowBagInventoryDataType(), new ArrowBagInventoryData(abih.getInventory().getContents()));
            item.setItemMeta(meta);
        }
    }
}
