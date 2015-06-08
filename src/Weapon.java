
public class Weapon extends Item
{
   private String gType;//ex: sword, knife, staff 
   private int atk;
   
   public Weapon()
   {
      super("Empty Hand", "Equipment", 0, 0);
      this.gType = "";
      this.atk = 0;
   }
   
   //method separation
   
   public Weapon(String n, int w, String g, int a, int id)//name, weight, gear type, atk, itemID
   {
      super(n, "Equipment", w, id);
      
      this.gType = g;
      this.atk = a;
   }
   
   //method separation
   
   public String getGType()
   {
      return this.gType;
   }
	
	//method separation
	
	public int getAtk()
	{
		return this.atk;
	}
   
   //method separation
   
   @Override
   public String toString()
   {
      return super.toString(gType);
   }
   
   //method separation
   
   public void use(Party<Character> p)
   {
      throw new Exception("This item cannot be \"used\".");
   }
}