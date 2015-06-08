

public class HealingItem extends Item
{
   private char hType;//healing item
   private int healAmount;
   
   public HealingItem()
   {
      super("", "Consumable", 0, 0);
      this.hType = 'h';
      this.healAmount = 0;
   }
   
   //method separation
   
   public HealingItem(String n, String c, char h, int w, int p, int id)//name, item type, heal type, weight, heal amount, itemID
   {
      super(n, "Consumable", w, id);
      this.hType = h;
      this.healAmount = p;
   }
   
   //method separation
   
   public int getHealAmount()
   {
      return this.healAmount;
   }
   
   //method separation
   
   @Override
   public String toString()
   {
      return super.toString(hType + "");
   }
   
   //method separation
   
   public void use(Party<Character> p)
   {
      if(this.getID() == 1004)//many target
      {
         for(Character c : p)
         {
            c.healDamage(10);
         }
      }
      else if (this.getID() == 1001)//single target
      {
         Character c = p.selectCharacter(new LivingCharacterTester());
         
         c.healDamage(10);
      }
   }
   
   //method separation
   

}