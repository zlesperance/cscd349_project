
import java.util.Random;

public class HealingItem extends Item implements RandomNumber
{
   private char hType;//healing item
   private int healAmount;
   private Random r = new Random(System.nanoTime());
   
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
   
   public int rand(int upperBound)//may be used for ranged heal amount, IDK
   {
      return r.nextInt() % upperBound;
   }
}