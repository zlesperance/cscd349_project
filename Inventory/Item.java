
public abstract class Item
{
   private String iName;
   private String iType;//might re-evaluate this
   private int iWeight;
   private int itemID;
   private int iQuantity;
   
   public Item()
   {
      this.iName = "";
      this.iType = "";
      this.iWeight = 0;
      this.itemID = 0;
      this.iQuantity = 0;
   }
   
   //method separation
   
   public Item(String n, String t, int w, int id)
   {
      this.iName = n;
      this.iType = t;
      this.iWeight = w;
      this.itemID = id;
      this.iQuantity = 1;
   }
   
   //method separation
   
   public String getIName()
   {
      return this.iName;
   }
   
   //method separation
   
   public String getIType()
   {
      return this.iType;
   }
   
   //method separation
   
   public int getWeight()
   {
      return this.iWeight;
   }
   
   //method separation
   
   public int getID()
   {
      return this.itemID;
   }
	
	//method separation
   
   public int getQuantity()
	{
		return this.iQuantity;
	}
   
   //method separation
	
   public void setIName(String n)
   {
      this.iName = n;
   }
   
   //method separation
   
   public void setIType(String t)
   {
      this.iType = t;
   }
   
   //method separation
   
   public void setWeight(int w)
   {
      this.iWeight = w;
   }
   
   //method separation
   
   public void setID(int i)
   {
      this.itemID = i;
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
   
   public String toString(String s)
   {
      return iName + "(" + iQuantity + "), a(n) " + iType + "(" + s + ") item, " + iWeight + " weight\n";
   }
   
   public boolean equals(Object o)
   {
      return this.iName.compareTo(((Item)o).iName) == 0;
   }
   
   public int compareTo(Object o)
   {
      return this.itemID - ((Item)o).itemID;
   }
}