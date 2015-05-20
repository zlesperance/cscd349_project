

public class Chest
{
   private Item loot;
   private int amount;
   
   public Chest()
   {
      this.loot = null;
      this.amount = 0;
   }
   
   //method separation
   
   public Chest(HealingItem h, int i)
   {
      this.loot = h;
      this.amount = i;
   }
   
   //method separation
   
   public Chest(UsableItem u, int i)
   {
      this.loot = u;
      this.amount = i;
   }
   
   //method separation
   
   public Chest(Weapon w, int i)
   {
      this.loot = w;
      this.amount = i;
   }
   
   //method separation
   
   public Chest(Shield s, int i)
   {
      this.loot = s;
      this.amount = i;
   }
   
   //method separation
   
   public Chest(Etc e, int i)
   {
      this.loot = e;
      this.amount = i;
   }
}