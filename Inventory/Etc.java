
public class Etc extends Item
{
	private int iQuantity;
	
   public Etc()
   {
      super("", "Etc", 0);
		this.iQuantity = 0;
   }
   
   //method separation
   
   public Etc(String n, int w)//name, weight
   {
      super(n, "Etc", w);
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
      return super.toString("", 0);
   }
}