package net.silentchaos512.supermultidrills.registry;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.silentchaos512.supermultidrills.util.LogHelper;

public class SRegistry {

  private final static HashMap<String, Block> blocks = new HashMap<String, Block>();
  private final static HashMap<String, Item> items = new HashMap<String, Item>();

  /**
   * Add a Block to the hash map and registers it in the GameRegistry.
   */
  public static Block registerBlock(Block block, String key) {

    return registerBlock(block, key, ItemBlock.class); // TODO: Custom item block?
  }

  /**
   * Add a Block to the hash map and registers it in the GameRegistry.
   */
  public static Block registerBlock(Block block, String key,
      Class<? extends ItemBlock> itemBlockClass) {

    blocks.put(key, block);
    GameRegistry.registerBlock(block, itemBlockClass, key);
    return block;
  }

  /**
   * Creates a new Item instance and add it to the hash map.
   */
  public static Item registerItem(Item item, String key) {

    items.put(key, item);
    GameRegistry.registerItem(item, key);
    return item;
  }

  /**
   * Calls the addRecipes and addOreDict methods of all Blocks and Items that can be cast to IAddRecipe. Should be
   * called after registering all Blocks and Items.
   */
  public static void addRecipesAndOreDictEntries() {

    IAddRecipe object;
    for (Block block : blocks.values()) {
      if (block instanceof IAddRecipe) {
        object = (IAddRecipe) block;
        object.addRecipes();
        object.addOreDict();
      }
    }
    for (Item item : items.values()) {
      if (item instanceof IAddRecipe) {
        object = (IAddRecipe) item;
        object.addRecipes();
        object.addOreDict();
      }
    }
  }

  public static String[] removeNullElements(String[] names) {

    ArrayList<String> list = new ArrayList<String>();
    for (String name : names) {
      if (name != null) {
        list.add(name);
      }
    }
    return list.toArray(new String[] {});
  }

  /**
   * Registers model variant names.
   */
  public static void clientPreInit() {

    // Blocks (IHasVariants)
    for (Block block : blocks.values()) {
      if (block instanceof IHasVariants) {
        IHasVariants var = (IHasVariants) block;
        String[] names = removeNullElements(var.getVariantNames());
        ModelBakery.addVariantName(Item.getItemFromBlock(block), names);
      }
    }

    // Items (IHasVariants)
    for (Item item : items.values()) {
      if (item instanceof IHasVariants) {
        IHasVariants var = (IHasVariants) item;
        String[] names = removeNullElements(var.getVariantNames());
        ModelBakery.addVariantName(item, names);
      }
    }
  }

  public static void clientInit() {

    // Blocks (IRegisterModels)
    for (Block block : blocks.values()) {
      if (block instanceof IRegisterModels) {
        ((IRegisterModels) block).registerModels();
      }
    }

    // Blocks (IHasVariants)
    for (Block block : blocks.values()) {
      Item item = Item.getItemFromBlock(block);
      if (block instanceof IHasVariants && !(block instanceof IRegisterModels)) {
        IHasVariants var = (IHasVariants) block;
        String[] variants = var.getVariantNames();
        int count = variants.length;
        for (int i = 0; i < count; ++i) {
          if (variants[i] != null) {
            ModelResourceLocation model = new ModelResourceLocation(variants[i], "inventory");
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, i, model);
          }
        }
      }
    }

    // Items (IRegisterModels)
    for (Item item : items.values()) {
      if (item instanceof IRegisterModels) {
        ((IRegisterModels) item).registerModels();
      }
    }

    // Items (IHasVariants)
    for (Item item : items.values()) {
      if (item instanceof IHasVariants && !(item instanceof IRegisterModels)) {
        IHasVariants var = (IHasVariants) item;
        String[] variants = var.getVariantNames();
        int count = variants.length;
        for (int i = 0; i < count; ++i) {
          if (variants[i] != null) {
            ModelResourceLocation model = new ModelResourceLocation(variants[i], "inventory");
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, i, model);
          }
        }
      }
    }
  }

  /*
   * Getter methods. Saving blocks/items to variables is preferred, but in some cases this could be useful, especially
   * in Silent's Gems with the ridiculous number of tools.
   */

  /**
   * Gets the Block registered with the given key.
   * 
   * @param key
   * @return
   */
  public static Block getBlock(String key) {

    if (!blocks.containsKey(key)) {
      LogHelper.severe("No block with key " + key + "! This is a bug!");
    }

    return blocks.get(key);
  }

  /**
   * Gets the Item registered with the given key.
   * 
   * @param key
   * @return
   */
  public static Item getItem(String key) {

    if (!items.containsKey(key)) {
      LogHelper.severe("No item with key " + key + "! This is a bug!");
    }

    return items.get(key);
  }
}
