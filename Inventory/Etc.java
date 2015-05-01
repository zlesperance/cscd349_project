
public class Etc extends Item
{
   public Etc()
   {
      super("", "Etc", 0, 0);
   }
   
   //method separation
   
   public Etc(String n, int w, int id)//name, weight, itemID
   {
      super(n, "Etc", w, id);
   }
	
	//method separation
	
   @Override
   public String toString()
   {
      return super.toString("");
   }
}