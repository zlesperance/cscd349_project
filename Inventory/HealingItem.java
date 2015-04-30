
public class HealingItem extends Item
{
   private String hType;//healing item
   private int healAmountHP;
   private int healAmountSP;
	private int iQuantity;
   
   public HealingItem()
   {
      super("", "Consumable", 0, 0);
      this.hType = "";
      this.healAmountHP = 0;
      this.healAmountSP = 0;
		this.iQuantity = 0;
   }
   
   //method separation
   
   public HealingItem(String n, String c, int w, int hp, int sp, int id)//name, weight, heal amount(hp), heal amount(sp), itemID
   {
      super(n, "Consumable", w, id);
      this.hType = "Healing";
      this.healAmountHP = hp;
      this.healAmountSP = sp;
		this.iQuantity = 1;
   }
   
   //method separation
   
   public int getHealAmountHP()
   {
      return this.healAmountHP;
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
      return super.toString(hType, iQuantity);
   }
}