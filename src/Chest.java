

public class Chest
{
   private Item loot;
   
   public Chest()
   {
      this.loot = null;
   }
   
   //method separation
   
   public Chest(HealingItem h)
   {
      this.loot = h;
   }
   
   //method separation
   
   public Chest(UsableItem u)
   {
      this.loot = u;
   }
   
   //method separation
   
   public Chest(Weapon w)
   {
      this.loot = w;
   }
   
   //method separation
   
   public Chest(Shield s)
   {
      this.loot = s;
   }
   
   //method separation
   
   public Chest(Etc e)
   {
      this.loot = e;
   }
   
   //method separation
   
   public HealingItem getHealingItem()
   {
      return (HealingItem)(this.loot);
   }
   
   //method separation
   
   public UsableItem getUsableItem()
   {
      return (UsableItem)(this.loot);
   }
   
   //method separation
   
   public Weapon getWeapon()
   {
      return (Weapon)(this.loot);
   }
   
   //method separation
   
   public Shield getShield()
   {
      return (Shield)(this.loot);
   }
   
   //method separation
   
   public Etc getEtc()
   {
      return (Etc)(this.loot);
   }
}