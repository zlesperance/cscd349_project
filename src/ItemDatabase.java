

public class ItemDatabase
{
   private Item []db = new Item[40];
   private int size;
   
   public ItemDatabase()
   {
      //Items tab items
      HealingItem redPotion = new HealingItem("Red Potion", "Consumable", 'h', 2, 19, 1001);
      HealingItem bluePotion = new HealingItem("Blue Potion", "Consumable", 'm', 5, 10, 1002);
      HealingItem greenPotion = new HealingItem("Green Potion", "Consumable", 's', 3, 5, 1003);
      HealingItem teamHealScroll = new HealingItem("Team Heal Scroll", "Consumable", 'h', 10, 20, 1004);
      UsableItem teleportScroll = new UsableItem("Teleport Scroll", 5, 1005);
      UsableItem reviveScroll = new UsableItem("Revive Scroll", 5, 1006);
      
      //Equip tab items
      Weapon knife = new Weapon("Knife[0]", 40, "Dagger", 17, 2001);//physical weapon
      Weapon arcWand = new Weapon("Arc Wand[0]", 45, "Staff", 60, 2002);//magic weapon
      Shield guard = new Shield("Guard[0]", 130, "Shield",  6, 2003);//physical shield
      Shield eCoat = new Shield("Energy Coat[0]", 0, "Shield", 4, 2004);//magic shield
      
      //Etc tab items
      Etc jellopy = new Etc("Jellopy", 1, 3001);
      Etc pGemstone = new Etc("Purple Gemstone", 1, 3002);
      Etc eyeOfNewt = new Etc("Eye of Newt", 1, 3003);
      Etc clawOfRat = new Etc("Claw of Rat", 1, 3004);
      Etc bloodOfSloth = new Etc("Blood of Sloth", 1, 3005);
      Etc barbedWire = new Etc("Barbed Wire", 1, 3006);
      Etc shrunkenHead = new Etc("Shrunken Head", 1, 3007);
      Etc chalkStick = new Etc("Chalk Stick", 1, 3008);
      Etc wingOfDragonfly = new Etc("Wing of Dragonfly", 1, 3009);
      Etc fluff = new Etc("Fluff", 1, 3010);
      Etc dustParticle = new Etc("Dust Particle", 1, 3011);
      Etc toothOfBat = new Etc("Tooth of Bat", 1, 3012);
      Etc tentacle = new Etc("Tentacle", 1, 3013);
      Etc ectoplasm = new Etc("Ectoplasm", 1, 3014);
      Etc decayedToenail = new Etc("Decayed Toenail", 1, 3015);
      Etc shrodingerBox = new Etc("Shrodinger Box", 1, 3016);
      Etc stickyMucus = new Etc("Sticky Mucus", 1, 3017);
      
      db[0] = redPotion;
      db[1] = bluePotion;
      db[2] = greenPotion;
      db[3] = teamHealScroll;
      db[4] = teleportScroll;
      db[5] = reviveScroll;
      db[6] = knife;
      db[7] = arcWand;
      db[8] = guard;
      db[9] = eCoat;
      db[10] = jellopy;
      db[11] = pGemstone;
      db[12] = eyeOfNewt;
      db[13] = clawOfRat;
      db[14] = bloodOfSloth;
      db[15] = barbedWire;
      db[16] = shrunkenHead;
      db[17] = chalkStick;
      db[18] = wingOfDragonfly;
      db[19] = fluff;
      db[20] = dustParticle;
      db[21] = toothOfBat;
      db[22] = tentacle;
      db[23] = ectoplasm;
      db[24] = decayedToenail;
      db[25] = shrodingerBox;
      db[26] = stickyMucus;
      
      this.size = 27;
   }
   
   public Item getItem(int i)
   {
      return this.db[i];
   }
}