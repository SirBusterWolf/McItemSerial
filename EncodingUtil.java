package pl.dragonhard.core.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

public class EncodingUtil
{
  public static String toBase64(Inventory inventory)
  {
    try
    {
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
      
      dataOutput.writeInt(inventory.getSize());
      for (int i = 0; i < inventory.getSize(); i++) {
        dataOutput.writeObject(inventory.getItem(i));
      }
      dataOutput.close();
      return Base64Coder.encodeLines(outputStream.toByteArray());
    }
    catch (Exception e)
    {
      throw new IllegalStateException("Unable to save item stacks.", e);
    }
  }
  
  public static Inventory fromBase64(String data)
    throws IOException
  {
    try
    {
      ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
      BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
      Inventory inventory = Bukkit.getServer().createInventory(null, dataInput.readInt());
      for (int i = 0; i < inventory.getSize(); i++) {
        inventory.setItem(i, (ItemStack)dataInput.readObject());
      }
      dataInput.close();
      return inventory;
    }
    catch (ClassNotFoundException e)
    {
      throw new IOException("Unable to decode class type.", e);
    }
  }
  
  public static String itemStackArrayToBase64(ItemStack[] items)
    throws IllegalStateException
  {
    try
    {
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
      
      dataOutput.writeInt(items.length);
      for (int i = 0; i < items.length; i++) {
        dataOutput.writeObject(items[i]);
      }
      dataOutput.close();
      return Base64Coder.encodeLines(outputStream.toByteArray());
    }
    catch (Exception e)
    {
      throw new IllegalStateException("Unable to save item stacks.", e);
    }
  }
  
  public static ItemStack[] itemStackArrayFromBase64(String data)
    throws IOException
  {
    try
    {
      ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
      BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
      ItemStack[] items = new ItemStack[dataInput.readInt()];
      for (int i = 0; i < items.length; i++) {
        items[i] = ((ItemStack)dataInput.readObject());
      }
      dataInput.close();
      return items;
    }
    catch (ClassNotFoundException e)
    {
      throw new IOException("Unable to decode class type.", e);
    }
  }
  
  public static String potionEffectsCollectionToBase64(Collection<PotionEffect> potionEffect)
    throws IllegalStateException
  {
    try
    {
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
      
      dataOutput.writeInt(potionEffect.toArray().length);
      for (int i = 0; i < potionEffect.toArray().length; i++) {
        dataOutput.writeObject(potionEffect.toArray()[i]);
      }
      dataOutput.close();
      return Base64Coder.encodeLines(outputStream.toByteArray());
    }
    catch (Exception e)
    {
      throw new IllegalStateException("Unable to save potion effect.", e);
    }
  }
  
  public static Collection<PotionEffect> potionEffectsCollectionFromBase64(String data)
    throws IOException
  {
    try
    {
      ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
      BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
      
      PotionEffect[] potionEffects = new PotionEffect[dataInput.readInt()];
	Collection<PotionEffect> effects = new ArrayList<PotionEffect>();
      for (int i = 0; i < potionEffects.length; i++) {
        effects.add((PotionEffect)dataInput.readObject());
      }
      dataInput.close();
      return effects;
    }
    catch (ClassNotFoundException e)
    {
      throw new IOException("Unable to decode class type.", e);
    }
  }
  
  public static String itemtoBase64(ItemStack items)
  {
    try
    {
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
      dataOutput.writeObject(items);
      dataOutput.close();
      return Base64Coder.encodeLines(outputStream.toByteArray());
    }
    catch (Exception e)
    {
      throw new IllegalStateException("Unable to save item stacks.", e);
    }
  }
  public static ItemStack itemfromBase64(String data)
		    throws IOException
		  {
		    try
		    {
		    	ItemStack item;
		      ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
		      BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
		      item=(ItemStack)dataInput.readObject();
		      dataInput.close();
		      return item;
		    }
		    catch (ClassNotFoundException e)
		    {
		      throw new IOException("Unable to decode class type.", e);
		    }
		  }
}