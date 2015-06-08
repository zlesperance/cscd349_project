
//figher, mancer, fencer, hunter
public class ItemDatabase
{
   private Item []db = new Item[50];
   private int size;
   
   public ItemDatabase()
   {
      //Items tab items
      HealingItem healthPotion = new HealingItem("Health Potion", "Consumable", 'h', 2, 19, 1001);
      HealingItem staminaPotion = new HealingItem("Stamina Potion", "Consumable", 's', 3, 5, 1003);
      HealingItem teamHealingScroll = new HealingItem("Team Healing Scroll", "Consumable", 'h', 10, 20, 1004);
      HealingItem healingScroll = new HealingItem("Healing Scroll", "Consumable", 'h', 8, 25, 1005);
      UsableItem teleportScroll = new UsableItem("Teleport Scroll", 5, 1006);
      UsableItem reviveScroll = new UsableItem("Revive Scroll", 5, 1007);
      
      //Equip tab items
      //Weapons
      Weapon bronzeSword = new Weapon("Bronze Sword", 40, "Sword", 10, 2001);//physical weapon
      Weapon ironSword = new Weapon("Iron Sword", 38, "Sword", 20, 2002);//physical weapon
      Weapon steelSword = new Weapon("Steel Sword", 35, "Sword", 30, 2003);//physical weapon
      Weapon woodenStaff = new Weapon("Wooden Staff", 15, "Staff", 9, 2004);//magic weapon
      Weapon magicianStaff = new Weapon("Magician Staff", 13, "Staff", 18, 2005);//magic weapon
      Weapon wizardStaff = new Weapon("Wizard Staff", 10, "Staff", 27, 2006);//magic weapon
      Weapon longBow = new Weapon("Long Bow", 25, "Bow", 12, 2007);//physical weapon
      Weapon compositeBow = new Weapon("Composite Bow", 23, "Bow", 18, 2008);//physical weapon
      Weapon crossbow = new Weapon("Crossbow", 20, "Bow", 24, 2009);//physical weapon
      
      //Equip tab items
      //Shields
      Shield guard = new Shield("Guard", 30, "Shield",  6, 2010);//physical shield
      Shield buckler = new Shield("Buckler", 40, "Shield", 10, 2011);//physical shield
      Shield eCoat = new Shield("Energy Coat", 0, "Shield", 4, 2012);//magic shield
      
      //Etc tab items
      Etc jellopy = new Etc("Jellopy", 1, 3001);
      Etc pGemstone = new Etc("Purple Gemstone", 1, 3002);//maybe ammunition
      Etc eyeOfNewt = new Etc("Eye of Newt", 1, 3003);
      Etc clawOfRat = new Etc("Claw of Rat", 1, 3004);
      Etc bloodOfSloth = new Etc("Blood of Sloth", 1, 3005);
      Etc barbedWire = new Etc("Barbed Wire", 1, 3006);
      Etc shrunkenHead = new Etc("Shrunken Head", 1, 3007);
      Etc chalkStick = new Etc("Chalk Stick", 1, 3008);//maybe catalyst
      Etc wingOfDragonfly = new Etc("Wing of Dragonfly", 1, 3009);
      Etc fluff = new Etc("Fluff", 1, 3010);
      Etc dustParticle = new Etc("Dust Particle", 1, 3011);
      Etc toothOfBat = new Etc("Tooth of Bat", 1, 3012);
      Etc tentacle = new Etc("Tentacle", 1, 3013);
      Etc ectoplasm = new Etc("Ectoplasm", 1, 3014);
      Etc decayedToenail = new Etc("Decayed Toenail", 1, 3015);
      Etc shrodingerBox = new Etc("Shrodinger Box", 1, 3016);
      Etc stickyMucus = new Etc("Sticky Mucus", 1, 3017);
      Etc arrow = new Etc("Arrow", 1, 3018);//ammunition
      Etc bolt = new Etc("Bolt", 1, 3019);//ammunition
      Etc yBackbone = new Etc("Yellow Backbone", 1, 3020);
      Etc bVial = new Etc("Blood Vial", 1, 3021);
      Etc key = new Etc("Key", 1, 3022);
      
      db[0] = healthPotion;
      db[1] = staminaPotion;
      db[2] = teamHealingScroll;
      db[3] = healingScroll;
      db[4] = teleportScroll;
      db[5] = reviveScroll;
      db[6] = bronzeSword;
      db[7] = ironSword;
      db[8] = steelSword;
      db[9] = woodenStaff;
      db[10] = magicianStaff;
      db[11] = wizardStaff;
      db[12] = longBow;
      db[13] = compositeBow;
      db[14] = crossbow;
      db[15] = guard;
      db[16] = buckler;
      db[17] = eCoat;
      db[18] = jellopy;
      db[19] = pGemstone;
      db[20] = eyeOfNewt;
      db[21] = clawOfRat;
      db[22] = bloodOfSloth;
      db[23] = barbedWire;
      db[24] = shrunkenHead;
      db[25] = chalkStick;
      db[26] = wingOfDragonfly;
      db[27] = fluff;
      db[28] = dustParticle;
      db[29] = toothOfBat;
      db[30] = tentacle;
      db[31] = ectoplasm;
      db[32] = decayedToenail;
      db[33] = shrodingerBox;
      db[34] = stickyMucus;
      db[35] = arrow;
      db[36] = bolt;
      db[37] = yBackbone;
      db[38] = bVial;
      db[39] = key;
      
      this.size = 40;
   }
   
   //method separation
   
   public UsableItem getUsableItem(int i)
   {
      return (UsableItem)(this.db[i]);
   }
   
   //method separation
   
   public HealingItem getHealingItem(int i)
   {
      return (HealingItem)(this.db[i]);
   }
   
   //method separation
   
   public Weapon getWeapon(int i)
   {
      return (Weapon)(this.db[i]);
   }
   
   //method separation
   
   public Shield getShield(int i)
   {
      return (Shield)(this.db[i]);
   }
   
   //method separation
   
   public Etc getEtc(int i)
   {
      return (Etc)(this.db[i]);
   }
}