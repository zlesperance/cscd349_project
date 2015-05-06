
import java.util.Random;

public class UsableItem extends Item implements RandomNumber
{
   private Random r = new Random(System.nanoTime());
   
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
   
   public int rand(int upperBound)
   {
      return r.nextInt() % upperBound;
   }
}