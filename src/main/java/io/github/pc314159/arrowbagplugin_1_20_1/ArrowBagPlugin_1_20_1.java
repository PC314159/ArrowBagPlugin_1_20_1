package io.github.pc314159.arrowbagplugin_1_20_1;

import io.github.pc314159.arrowbagplugin_1_20_1.events.ArrowBagInventoryClickEvent;
import io.github.pc314159.arrowbagplugin_1_20_1.events.ArrowBagInventoryCloseEvent;
import io.github.pc314159.arrowbagplugin_1_20_1.events.ArrowBagInventoryDragEvent;
import io.github.pc314159.arrowbagplugin_1_20_1.events.ArrowBagPlaceEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class ArrowBagPlugin_1_20_1 extends JavaPlugin {
    private final NamespacedKey arrowBagSizeKey = new NamespacedKey(this, "ArrowBagSizeKey");
    private final NamespacedKey arrowBagInvKey = new NamespacedKey(this, "ArrowBagInvKey");

    public NamespacedKey getArrowBagSizeKey() {
        return arrowBagSizeKey;
    }
    public NamespacedKey getArrowBagInvKey() {
        return arrowBagInvKey;
    }
    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("ArrowBagPlugin Enabled");
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new ArrowBagPlaceEvent(this), this);
        pm.registerEvents(new ArrowBagInventoryClickEvent(this), this);
        pm.registerEvents(new ArrowBagInventoryCloseEvent(this), this);
        pm.registerEvents(new ArrowBagInventoryDragEvent(this), this);
        Bukkit.addRecipe(getArrowBagRecipe());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("ArrowBagPlugin Disabled");
    }
    public ShapedRecipe getArrowBagRecipe() {
        ItemStack item = new ItemStack(Material.WHITE_SHULKER_BOX);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + "Arrow Bag");

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "Capacity 9");
        if (meta.hasLore()){
            for (String l: meta.getLore()){
                lore.add(l);
            }
        }
        meta.setLore(lore);

        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        pdc.set(arrowBagSizeKey, PersistentDataType.INTEGER,Integer.valueOf(9));
        ArrowBagInventoryDataType abidt = new ArrowBagInventoryDataType();
        ArrowBagInventoryData abid = new ArrowBagInventoryData(new ItemStack[9]);
        pdc.set(arrowBagInvKey, abidt, abid);

        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "ArrowBag");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" S ", "W W", " W ");
        recipe.setIngredient('S', Material.STRING);
        recipe.setIngredient('W', Material.LEATHER);
        return recipe;
    }
}
