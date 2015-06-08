
public class ItemDrops
{
   private int itemID;
   private double dropRate;
   
   public ItemDrops(int id, double dr)
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