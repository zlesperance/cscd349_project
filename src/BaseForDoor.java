public class BaseForDoor
{
   private int row;
   private int col;
   private boolean isLocked = true;
   
   public BaseForDoor(int col, int row, boolean isLocked)
   {
      this.col = col;
      this.row = row;
      this.isLocked = isLocked;
   }
   public int getRow()
   {  
      return row;
   }  
   public int getCol()
   {
      return col;
   }
   public boolean getLocked()
   {
      return isLocked;
   }
}