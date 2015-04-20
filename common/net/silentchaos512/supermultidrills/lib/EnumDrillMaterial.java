package net.silentchaos512.supermultidrills.lib;


public enum EnumDrillMaterial {
  
  // Vanilla
  IRON(250, 6.0f, 2.0f, ""),
  GOLD(32, 12.0f, 0.0f, ""),
  DIAMOND(1561, 8.0f, 3.0f, ""),
  
  // Thermal Foundation
  COPPER(175, 4.0f, 0.5f, "ingotCopper"),
  TIN(200, 4.5f, 1.0f, "ingotTin"),
  SILVER(200, 6.0f, 1.5f, "ingotSilver"),
  LEAD(150, 5.0f, 1.0f, "ingotLead"),
  FERROUS(300, 6.5f, 2.5f, "ingotNickel"),
  ELECTRUM(100, 14.0f, 0.5f, "ingotElectrum"),
  INVAR(450, 7.0f, 3.0f, "ingotInvar"),
  BRONZE(500, 6.0f, 2.0f, "ingotBronze"),
  SHINY(1700, 9.0f, 4.0f, "ingotPlatinum"),
  
  // Silent's Gems
  RUBY(1536, 8.0f, 3.0f, "gemRuby"),
  GARNET(1024, 8.0f, 3.0f, "gemGarnet"),
  TOPAZ(1024, 10.0f, 4.0f, "gemTopaz"),
  HELIODOR(768, 12.0f, 5.0f, "gemHeliodor"),
  PERIDOT(1024, 7.0f, 4.0f, "gemPeridot"),
  BERYL(1024, 8.0f, 4.0f, "gemBeryl"),
  AQUAMARINE(768, 10.0f, 3.0f, "gemAquamarine"),
  SAPPHIRE(1536, 8.0f, 3.0f, "gemSapphire"),
  IOLITE(1536, 7.0f, 2.0f, "gemIolite"),
  AMETHYST(1024, 8.0f, 3.0f, "gemAmethyst"),
  MORGANITE(1024, 10.0f, 4.0f, "gemMorganite"),
  ONYX(768, 10.0f, 6.0f, "gemOnyx"),
  
  // Misc
  STEEL(900, 7.0f, 2.0f, "ingotSteel");
  
  private final int durability;
  private final float efficiency;
  private final float damage;
  private final String material;
  
  private EnumDrillMaterial(int durability, float efficiency, float damage, String material) {

    this.durability = durability;
    this.efficiency = efficiency;
    this.damage = damage;
    this.material = material;
  }
  
  public float getEfficiency() {
    
    return efficiency;
  }
  
  public float getDamageVsEntity() {
    
    return damage;
  }
  
  public String getMaterialName() {
    
    return material;
  }
  
  public float getCostPerHardness() {
    
    // Higher durability = lower cost
    // y = 250 + (1100 / 95) - (11 / 95) * x
    return 261.5789f - 0.1158f * durability;
  }
  
  @Override
  public String toString() {
    
    String s = name();
    return s.substring(0, 1) + s.substring(1, s.length()).toLowerCase();
  }
}
