package io.github.pc314159.arrowbagplugin_1_20_1;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class ArrowBagInventoryHolder implements InventoryHolder {
    private final Inventory inv;
    private final ItemStack item;

    public ArrowBagInventoryHolder(int size, ItemStack item) {
        this.inv = Bukkit.createInventory(this, size, "Arrow Bag"); //54 max size
        this.item = item;
    }

    public void loadData(ItemStack[] items) {
        inv.setStorageContents(items);
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }

    public ItemStack getItem() {
        return item;
    }
}