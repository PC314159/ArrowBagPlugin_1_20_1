package io.github.pc314159.arrowbagplugin_1_20_1;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.*;
import java.util.logging.Level;

public class ArrowBagInventoryDataType implements PersistentDataType<byte[], ArrowBagInventoryData> {
    @Override
    public Class getPrimitiveType() {
        return byte[].class;
    }

    @Override
    public Class getComplexType() {
        return ArrowBagInventoryData.class;
    }

    @Override
    public byte[] toPrimitive(ArrowBagInventoryData complex, PersistentDataAdapterContext context) {
        try{
            Bukkit.getLogger().info("toPrim");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BukkitObjectOutputStream boos = new BukkitObjectOutputStream(baos);
            boos.writeObject(complex.getInv());
            boos.close();
            byte[] serializedObject = baos.toByteArray();
            Bukkit.getLogger().info("serializedObject " + serializedObject.toString());

            ByteArrayInputStream bais = new ByteArrayInputStream(serializedObject);
            BukkitObjectInputStream bois = new BukkitObjectInputStream(bais);
            ItemStack[] content = (ItemStack[]) bois.readObject();
            ArrowBagInventoryData newItem = new ArrowBagInventoryData(content);
            newItem.getInv();
            Bukkit.getLogger().info("finished saving");

            return serializedObject;
        } catch (IOException | ClassNotFoundException ex){
            Bukkit.getLogger().log(Level.WARNING, ex.toString());
        }
        return new byte[0];
    }

    @Override
    public ArrowBagInventoryData fromPrimitive(byte[] primitive, PersistentDataAdapterContext context) {
        try{
            Bukkit.getLogger().info("fromPrim");
            Bukkit.getLogger().info("ABID"+primitive.toString());
            ByteArrayInputStream bais = new ByteArrayInputStream(primitive);
            BukkitObjectInputStream bois = new BukkitObjectInputStream(bais);
            ItemStack[] content = (ItemStack[]) bois.readObject();
            ArrowBagInventoryData newItem = new ArrowBagInventoryData(content);
            return newItem;
        } catch (IOException | ClassNotFoundException ex){
            Bukkit.getLogger().log(Level.WARNING, ex.toString());
        }
        return null;
    }
}
