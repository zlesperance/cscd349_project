
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
   
   //method separation
   
   public void use(Inventory i, Party p, StartMaze sm)
   {
      if(this.getID() == 3022)//key
      {
         if(sm.getDirectionReference().getDirection().equals("up"))
         {
            if((sm.getRowIndex() - 1) == 1)
            {
               i.removeItem(this);
               sm.removeDoorFlag(sm.getRowIndex() - 1, sm.getColIndex());
            }
         }
         else if(sm.getDirectionReference().getDirection().equals("down"))
         {
            if((sm.getRowIndex() + 1) == 1)
            {
               i.removeItem(this);
               sm.removeDoorFlag(sm.getRowIndex() + 1, sm.getColIndex());
            }
         }
         else if(sm.getDirectionReference().getDirection().equals("left"))
         {
            if((sm.getColIndex() - 1) == 1)
            {
               i.removeItem(this);
               sm.removeDoorFlag(sm.getRowIndex(), sm.getColIndex() - 1);
            }
         }
         else//right
         {
            if((sm.getColIndex() + 1) == 1)
            {
               i.removeItem(this);
               sm.removeDoorFlag(sm.getRowIndex(), sm.getColIndex() + 1);
            }
         }
      }
      else
      {
         throw new Exception("This item cannot be \"used\".");
      }
   }
}