package io.github.pc314159.arrowbagplugin_1_20_1;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class ArrowBagInventoryData {
    private ItemStack[] inv;

    public ArrowBagInventoryData(ItemStack[] inv) {
        Bukkit.getLogger().log(Level.WARNING,"create abid");
        for (int i = 0; i < inv.length; i++){
            if (inv[i] != null) {
                Bukkit.getLogger().info(inv[i].toString());
            } else {
                Bukkit.getLogger().info("null");
            }
        }
        this.inv = inv;
    }

    public ItemStack[] getInv() {
        Bukkit.getLogger().log(Level.WARNING,"get abid");
        Bukkit.getLogger().log(Level.WARNING,"inv "+inv.length);
        for (int i = 0; i < inv.length; i++){
            if (inv[i] != null) {
                Bukkit.getLogger().info(inv[i].toString());
            } else {
                Bukkit.getLogger().info("null");
            }
        }
        return inv;
    }

    public void setInv(ItemStack[] inv) {
        this.inv = inv;
    }

//    private void writeObject(ObjectOutputStream os) throws IOException{
//        Bukkit.getLogger().info("customSerializationWrite");
//        os.writeObject(this.inv);
//    }
//
//    public void readObject(ObjectInputStream is) throws IOException, ClassNotFoundException{
//        Bukkit.getLogger().info("customSerializationRead");
//        this.inv = (ItemStack[]) is.readObject();
//    }

}
