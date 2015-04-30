
public class Etc extends Item
{
	private int iQuantity;
	
   public Etc()
   {
      super("", "Etc", 0, 0);
		this.iQuantity = 0;
   }
   
   //method separation
   
   public Etc(String n, int w, int id)//name, weight, itemID
   {
      super(n, "Etc", w, id);
		this.iQuantity = 1;
   }
   
	//method separation
	
	public int getQuantity()
	{
		return this.iQuantity;
	}
	
   //method separation
   
	public void incrementQuantity()
	{
		this.iQuantity ++;
	}
	
	//method separation
	
	public void decrementQuantity()
	{
		this.iQuantity --;
	}
	
	//method separation
	
   @Override
   public String toString()
   {
      return super.toString("", iQuantity);
   }
}