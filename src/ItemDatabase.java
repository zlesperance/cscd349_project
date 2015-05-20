
//figher, mancer, fencer, hunter
public class ItemDatabase
{
   private Item []db = new Item[50];
   private int size;
   
   public ItemDatabase()
   {
      //Items tab items
      HealingItem redPotion = new HealingItem("Red Potion", "Consumable", 'h', 2, 19, 1001);
      HealingItem bluePotion = new HealingItem("Blue Potion", "Consumable", 'm', 5, 10, 1002);
      HealingItem greenPotion = new HealingItem("Green Potion", "Consumable", 's', 3, 5, 1003);
      HealingItem teamHealingScroll = new HealingItem("Team Healing Scroll", "Consumable", 'h', 10, 20, 1004);
      HealingItem healingScroll = new HealingItem("Healing Scroll", "Consumable", 'h', 8, 25, 1005);
      UsableItem teleportScroll = new UsableItem("Teleport Scroll", 5, 1005);
      UsableItem reviveScroll = new UsableItem("Revive Scroll", 5, 1006);
      
      //Equip tab items
      //Weapons
      Weapon bronzeSword = new Weapon("Bronze Sword", 40, "Sword", 10, 2001);//physical weapon
      Weapon ironSword = new Weapon("Iron Sword", 38, "Sword", 20, 2002);//physical weapon
      Weapon steelSword = new Weapon("Steel Sword", 35, "Sword", 30, 2003);//physical weapon
      Weapon woodenStaff = new Weapon("Wooden Staff", 15, "Staff", 12, 2004);//magic weapon
      Weapon magicianStaff = new Weapon("Magician Staff", 13, "Staff", 18, 2005);//magic weapon
      Weapon wizardStaff = new Weapon("Wizard Staff", 10, "Staff", 24, 2006);//magic weapon
      Weapon longBow = new Weapon("Long Bow", 25, "Bow", 9, 2007);//physical weapon
      Weapon compositeBow = new Weapon("Composite Bow", 23, "Bow", 18, 2008);//physical weapon
      Weapon crossbow = new Weapon("Crossbow", 20, "Bow", 27, 2009);//physical weapon
      
      //Equip tab items
      //Shields
      Shield guard = new Shield("Guard", 130, "Shield",  6, 2003);//physical shield
      Shield eCoat = new Shield("Energy Coat", 0, "Shield", 4, 2004);//magic shield
      
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
      
      db[0] = redPotion;
      db[1] = bluePotion;
      db[2] = greenPotion;
      db[3] = teamHealingScroll;
      db[4] = healingScroll;
      db[5] = teleportScroll;
      db[6] = reviveScroll;
      db[7] = bronzeSword;
      db[8] = ironSword;
      db[9] = steelSword;
      db[10] = woodenStaff;
      db[11] = magicianStaff;
      db[12] = wizardStaff;
      db[13] = longBow;
      db[14] = compositeBow;
      db[15] = crossbow;
      db[16] = guard;
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
      
      this.size = 37;
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