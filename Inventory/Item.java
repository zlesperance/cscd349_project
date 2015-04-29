
public abstract class Item
{
   private String iName;
   private String iType;//might re-evaluate this
   private int iWeight;
   
   public Item()
   {
      this.iName = "";
      this.iType = "";
      this.iWeight = 0;
   }
   
   //method separation
   
   public Item(String n, String t, int w)
   {
      this.iName = n;
      this.iType = t;
      this.iWeight = w;
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
   
   public String toString(String s, int q)
   {
      return iName + "(" + q + "), a(n) " + iType + "(" + s + ") item, " + iWeight + " weight\n";
   }
   
   public boolean equals(Object o)
   {
      return this.iName.compareTo(((Item)o).iName) == 0;
   }
}