

public class UsableItem extends Item
{
   public UsableItem()
   {
      super("", "Consumable", 0, 0);
   }
   
   //method separation
   
   public UsableItem(String n, int w, int id)//name, weight, itemID
   {
      super(n, "Consumable", w, id);
   }
   
   //method separation
   
   @Override
   public String toString()
   {
      return super.toString("");
   }
   
   //method separation
   
   public void use(Party p)
   {
      if(this.getID() == 1006)
      {
         //teleport
      }
      else //if(this.getID() == 1007)
      {
         Character c = p.selectCharacter(new DeadCharacterTester());
         
         c.healDamage(c.getBaseHP());
      }
   }
}