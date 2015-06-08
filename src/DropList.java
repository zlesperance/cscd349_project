
import java.util.LinkedList;

public class DropList
{
   private LinkedList<ItemDrops> list;
   
   public DropList()
   {
      list = new LinkedList<ItemDrops>();
   }
   
   //method separation
   
   public LinkedList getList()
   {
      return this.list;
   }
}