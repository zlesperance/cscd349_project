
public class ItemDrop
{
   private int itemID;
   private double dropRate;
   
   public ItemDrop(int id, double dr)
   {
      this.itemID = id;
      this.dropRate = dr;
   }
   
   //method separation
   
   public int getItemID()
   {
      return this.itemID;
   }
   
   //method separation
   
   public double getDropRate()
   {
      return this.dropRate;
   }
}