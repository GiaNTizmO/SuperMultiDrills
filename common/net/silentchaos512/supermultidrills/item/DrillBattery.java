package net.silentchaos512.supermultidrills.item;

import java.util.List;

import cofh.api.energy.IEnergyContainerItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.silentchaos512.lib.item.ItemSL;
import net.silentchaos512.lib.util.LocalizationHelper;
import net.silentchaos512.lib.util.StackHelper;
import net.silentchaos512.supermultidrills.SuperMultiDrills;
import net.silentchaos512.supermultidrills.configuration.Config;
import net.silentchaos512.supermultidrills.lib.Names;

public class DrillBattery extends ItemSL implements IEnergyContainerItem {

  public static final int SUB_TYPE_COUNT = 6;
  public static final int CREATIVE_ID = SUB_TYPE_COUNT - 1;
  public static final int CREATIVE_MAX_ENERGY = 1;
  public static final String NBT_BASE = "Battery";
  public static final String NBT_ENERGY = "Energy";

  public DrillBattery() {

    super(SUB_TYPE_COUNT, SuperMultiDrills.MOD_ID, Names.DRILL_BATTERY);
  }

  @Override
  public void addRecipes() {

    boolean addVanilla = true;

    if (SuperMultiDrills.instance.foundFunOres) {
      addRecipesFunOres();
      addVanilla = false;
    }
    if (SuperMultiDrills.instance.foundEnderIO) {
      addRecipesEnderIO();
      addVanilla = false;
    }
    if (SuperMultiDrills.instance.foundMekanism) {
      addRecipesMekanism();
      addVanilla = false;
    }

    if (addVanilla) {
      addRecipesVanilla();
    }
  }

  public ItemStack getBattery(int tier) {

    ItemStack result = new ItemStack(this, 1, tier);
    return result;
  }

  private void addRecipesFunOres() {

    String line1 = "iwi";
    String line2 = "rcr";

    // Tater
    GameRegistry.addRecipe(new ShapedOreRecipe(getBattery(0), line1, line2, line1, 'i', "ingotIron",
        'r', "dustRedstone", 'w', "ingotCopper", 'c', Items.POTATO));
    // Battery 1
    GameRegistry.addRecipe(new ShapedOreRecipe(getBattery(1), line1, line2, line1, 'i', "plateIron",
        'r', "ingotRedstoneAlloy", 'w', "plateCopper", 'c', "dustRedstone")); // TODO: Sulfur?
    // Battery 2
    GameRegistry.addRecipe(new ShapedOreRecipe(getBattery(2), line1, line2, line1, 'i', "plateIron",
        'r', "ingotRedstoneAlloy", 'w', "plateGold", 'c', "blockRedstone"));
    // Battery 3
    GameRegistry.addRecipe(new ShapedOreRecipe(getBattery(3), line1, line2, line1, 'i', "plateIron",
        'r', "ingotRedstoneAlloy", 'w', "plateElectrum", 'c', Items.ENDER_EYE));
    // Battery 4
    GameRegistry.addRecipe(new ShapedOreRecipe(getBattery(4), line1, line2, line1, 'i', "plateIron",
        'r', "ingotRedstoneAlloy", 'w', "platePlatinum", 'c', "ingotPrismarinium"));
  }

  /**
   * Ender IO recipes
   */
  private void addRecipesEnderIO() {

    Item itemCapacitor = (Item) Item.getByNameOrId("EnderIO:itemBasicCapacitor");
    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(this, 1, 0), "iri", "iyi", "iri", 'i',
        "ingotIron", 'r', "dustRedstone", 'y', Items.POTATO));
    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(this, 1, 1), "iri", "xrx", "iri", 'i',
        "ingotIron", 'r', "dustRedstone", 'x', "ingotConductiveIron"));
    GameRegistry.addRecipe(
        new ShapedOreRecipe(new ItemStack(this, 1, 2), "iri", "xyx", "iri", 'i', "ingotIron", 'r',
            "dustRedstone", 'x', "ingotElectricalSteel", 'y', new ItemStack(itemCapacitor, 1, 0)));
    GameRegistry.addRecipe(
        new ShapedOreRecipe(new ItemStack(this, 1, 3), "iri", "xyx", "iri", 'i', "ingotIron", 'r',
            "dustRedstone", 'x', "ingotEnergeticAlloy", 'y', new ItemStack(itemCapacitor, 1, 1)));
    GameRegistry.addRecipe(
        new ShapedOreRecipe(new ItemStack(this, 1, 4), "iri", "xyx", "iri", 'i', "ingotIron", 'r',
            "dustRedstone", 'x', "ingotVibrantAlloy", 'y', new ItemStack(itemCapacitor, 1, 2)));
  }

  /**
   * Mekanism recipes
   */
  private void addRecipesMekanism() {

    String line1 = "iwi";
    String line2 = "rcr";

    // Tater
    GameRegistry.addRecipe(new ShapedOreRecipe(getBattery(0), line1, line2, line1, 'i', "ingotIron",
        'r', "dustRedstone", 'w', "ingotCopper", 'c', Items.POTATO));
    // Battery 1
    GameRegistry.addRecipe(new ShapedOreRecipe(getBattery(1), line1, line2, line1, 'i', "ingotIron",
        'r', "dustRedstone", 'w', "battery", 'c', "ingotCopper"));
    // Battery 2
    GameRegistry.addRecipe(new ShapedOreRecipe(getBattery(2), line1, line2, line1, 'i', "ingotIron",
        'r', "alloyAdvanced", 'w', "battery", 'c', getBattery(1)));
    // Battery 3
    GameRegistry.addRecipe(new ShapedOreRecipe(getBattery(3), line1, line2, line1, 'i', "ingotIron",
        'r', "alloyElite", 'w', "battery", 'c', getBattery(2)));
    // Battery 4
    GameRegistry.addRecipe(new ShapedOreRecipe(getBattery(4), line1, line2, line1, 'i', "ingotIron",
        'r', "alloyUltimate", 'w', "battery", 'c', getBattery(3)));
  }

  /**
   * Vanilla fallback recipes
   */
  private void addRecipesVanilla() {

    GameRegistry.addRecipe(new ShapedOreRecipe(getBattery(0), " x ", "xyx", "zxz", 'x', Blocks.DIRT,
        'y', Items.POTATO, 'z', "dustRedstone"));
    GameRegistry.addRecipe(new ShapedOreRecipe(getBattery(1), " x ", "xyx", "zxz", 'x', "ingotIron",
        'y', "ingotGold", 'z', "dustRedstone"));
    GameRegistry.addRecipe(new ShapedOreRecipe(getBattery(2), " x ", "xyx", "zxz", 'x',
        "nuggetGold", 'y', "dustGlowstone", 'z', "dustRedstone"));
    GameRegistry.addRecipe(new ShapedOreRecipe(getBattery(3), " x ", "xyx", "zxz", 'x', "ingotGold",
        'y', Items.ENDER_PEARL, 'z', "dustRedstone"));
    GameRegistry.addRecipe(new ShapedOreRecipe(getBattery(4), " x ", "xyx", "zxz", 'x',
        "gemDiamond", 'y', Items.NETHER_STAR, 'z', "dustRedstone"));
  }

  @Override
  public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean advanced) {

    LocalizationHelper loc = SuperMultiDrills.localizationHelper;

    // Energy stored
    int energy = this.getEnergyStored(stack);
    int energyMax = this.getMaxEnergyStored(stack);

    if (stack.getItemDamage() == 0) {
      list.add(TextFormatting.GOLD + loc.getItemSubText(Names.DRILL_BATTERY, "Potato"));
    }

    String amount;
    if (stack.getItemDamage() == CREATIVE_ID) {
      list.add(TextFormatting.DARK_PURPLE + loc.getMiscText("CreativeOnly"));
      amount = loc.getMiscText("Infinite");
    } else {
      amount = String.format("%,d / %,d RF", energy, energyMax);
    }

    String str = TextFormatting.YELLOW + loc.getItemSubText(Names.DRILL, "Energy") + " " + amount;
    list.add(str);
  }

  @Override
  public EnumRarity getRarity(ItemStack stack) {

    switch (stack.getItemDamage()) {
      case 5:
        return EnumRarity.EPIC;
      case 4:
        return EnumRarity.RARE;
      default:
        return super.getRarity(stack);
    }
  }

  @Override
  public void clGetSubItems(Item item, CreativeTabs tab, List<ItemStack> list) {

    ItemStack battery;
    for (int i = 0; i < SUB_TYPE_COUNT - 1; ++i) {
      battery = new ItemStack(this, 1, i);
      list.add(battery); // Empty
      battery = new ItemStack(this, 1, i);
      setTag(battery, NBT_ENERGY, getMaxEnergyStored(battery));
      list.add(battery); // Full
    }
    // Creative battery should come charged.
    battery = new ItemStack(this, 1, CREATIVE_ID);
    setTag(battery, NBT_ENERGY, CREATIVE_MAX_ENERGY);
    list.add(battery);
  }

  @Override
  public boolean showDurabilityBar(ItemStack stack) {

    return true;
  }

  @Override
  public double getDurabilityForDisplay(ItemStack stack) {

    int energy = this.getEnergyStored(stack);
    int energyMax = this.getMaxEnergyStored(stack);
    return (double) (energyMax - energy) / (double) energyMax;
  }

  public int getEnergyCapacity(ItemStack stack) {

    switch (stack.getItemDamage()) {
      case CREATIVE_ID:
        return CREATIVE_MAX_ENERGY;
      case 4:
        return Config.battery4MaxCharge;
      case 3:
        return Config.battery3MaxCharge;
      case 2:
        return Config.battery2MaxCharge;
      case 1:
        return Config.battery1MaxCharge;
      default:
        return Config.battery0MaxCharge;
    }
  }

  public int getMaxEnergyExtracted(ItemStack container) {

    return Integer.MAX_VALUE;
  }

  public int getMaxEnergyReceived(ItemStack container) {

    return Math.max(this.getMaxEnergyStored(container) / 200, 1);
  }

  @Override
  public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {

    int energy = getEnergyStored(container);
    int energyReceived = Math.min(getMaxEnergyStored(container) - energy,
        Math.min(this.getMaxEnergyReceived(container), maxReceive));

    if (!simulate) {
      energy += energyReceived;
      this.setTag(container, NBT_ENERGY, energy);
    }
    return energyReceived;
  }

  @Override
  public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {

    if (container.getItemDamage() == CREATIVE_ID || !container.hasTagCompound()
        || !this.hasTag(container, NBT_ENERGY)) {
      return 0;
    }
    int energy = getEnergyStored(container);
    int energyExtracted = Math.min(energy,
        Math.min(this.getMaxEnergyExtracted(container), maxExtract));

    if (!simulate) {
      energy -= energyExtracted;
      this.setTag(container, NBT_ENERGY, energy);
    }
    return energyExtracted;
  }

  @Override
  public int getEnergyStored(ItemStack container) {

    return this.getTag(container, NBT_ENERGY);
  }

  @Override
  public int getMaxEnergyStored(ItemStack container) {

    return this.getEnergyCapacity(container);
  }

  public void createTagCompoundIfNeeded(ItemStack stack) {

    if (!stack.hasTagCompound()) {
      stack.setTagCompound(new NBTTagCompound());
    }
    if (!stack.getTagCompound().hasKey(NBT_BASE)) {
      stack.getTagCompound().setTag(NBT_BASE, new NBTTagCompound());
    }
  }

  public boolean hasTag(ItemStack stack, String key) {

    if (!stack.hasTagCompound() || !stack.getTagCompound().hasKey(NBT_BASE)) {
      return false;
    }
    return ((NBTTagCompound) stack.getTagCompound().getTag(NBT_BASE)).hasKey(key);
  }

  public int getTag(ItemStack stack, String key) {

    if (StackHelper.isEmpty(stack) || !stack.hasTagCompound()) {
      return 0;
    }

    NBTTagCompound tags = (NBTTagCompound) stack.getTagCompound().getTag(NBT_BASE);
    if (tags.hasKey(key)) {
      return tags.getInteger(key);
    } else {
      return 0;
    }
  }

  public void setTag(ItemStack stack, String key, int value) {

    if (StackHelper.isEmpty(stack)) {
      return;
    }
    this.createTagCompoundIfNeeded(stack);

    NBTTagCompound tags = (NBTTagCompound) stack.getTagCompound().getTag(NBT_BASE);
    tags.setInteger(key, value);
  }
}
