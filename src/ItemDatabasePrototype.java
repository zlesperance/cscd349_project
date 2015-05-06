

public class ItemDatabasePrototype
{
   private Item []db = new Item[20];
   private int size;
   
   public ItemDatabasePrototype()
   {
      //Items tab items
      HealingItem redPotion = new HealingItem("Red Potion", "Consumable", 'h', 2, 19, 1001);
      
      //Equip tab items
      Weapon knife = new Weapon("Knife[0]", 40, "Dagger", 17, 2001);
      Shield guard = new Shield("Guard[0]", 130, "Shield",  6, 2003);
      
      //Etc tab items
      Etc jellopy = new Etc("Jellopy", 1, 3001);
      
      db[0] = redPotion;
      db[1] = knife;
      db[2] = guard;
      db[3] = jellopy;
      
      this.size = 4;
   }
   
   public Item getItem(int i)
   {
      return this.db[i];
   }
}