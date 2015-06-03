import java.util.*;

public class DoorsWithKeysMonsters implements DoorsWithKeysMonstersI,Iterable
{
   private class DoorsWithKeysMonstersIterator implements Iterator
   {
      private int cur;
      private ArrayList d;
      
      public DoorsWithKeysMonstersIterator(ArrayList array)
      {
           this.d = array;
           this.cur = 0;  
      }
      public void remove()
      {
      
      }
      public Object next()
      {
         if(hasNext())
         {
            Object data = d.get(cur);
            return data;
         }  
         return new NoSuchElementException();
      }
      public boolean hasNext()
      {
         return this.cur < this.d.size();
      }
   }//end of private class
   
   private ArrayList door;
   private StartMaze move;
   private int countDoors = 0, countMonsters = 0, q = 0, r = 0;
   private boolean isLocked = true, lockedDoor;
   

   public DoorsWithKeysMonsters()
   {
      door = new ArrayList();
      move = new StartMaze();
   }
   
   public void checkMonstersDoors()  //should be in a differnt class
   {
             
         if(move.getReferenceNumber() == 0)
         {
            System.out.println("No monsters or doors");
         
         }
         else if(move.getReferenceNumber() == 1)
         {
            System.out.println("Player encourted a door");
                 
          
         }
         else if(move.getReferenceNumber() == 2)
         {
            System.out.println("Player encountered a monster");
         }
         
   }
   public Iterator iterator()
   {
      return new DoorsWithKeysMonstersIterator(door);
   }
   public ArrayList placeMonstersDoors()
   {
        
      
      Random coll = new Random();
      Random roww = new Random();
      int countMonsters = 0;
      q = 0;
      r = 0;
      int doors = 1,monsters = 2, qq = 0, rr = 0;
     
      
      while(countDoors != 3)
      {
         q = coll.nextInt((move.getCol() - 2)) +1;
         r = roww.nextInt((move.getRow() - 2)) + 1;
         
              
         if(move.stateTwoDMaze()[q][r] != 1)
         {
            move.stateTwoDMaze()[q][r] = 1;
            addKeys(q,r,isLocked);
            countDoors++; 
         }   
      }
      /*
      while(countMonsters != 3)
      {
           qq = coll.nextInt((move.getCol() - 2)) +1;
           rr = roww.nextInt((move.getRow() - 2)) + 1;
            
           int ref = move.stateTwoDMaze()[q][r]; 
            if(ref == 0)
            {
               move.stateTwoDMaze()[q][r] = 2;
               countMonsters++;
            }
           
      }
      */
      return door;
      
   }//end of placeMonstersDoorsMethod
   public void addKeys(int q, int r, boolean locked)
   {
      BaseForDoor base = new BaseForDoor(q, r, locked);
      door.add(base);
      
     
   }
   public boolean checkDoor(ArrayList door)
   {
       DoorsWithKeysMonsters k = new DoorsWithKeysMonsters();
      ArrayList<BaseForDoor> array = door;
    
     for(Object d: array)
     {
         BaseForDoor base = (BaseForDoor)d;
               
        if(move.getColIndex() == base.getCol() && move.getRowIndex() == base.getRow() && base.getLocked() == true)
        {
            System.out.println("Door locked");
            lockedDoor = true;
        }
        else
        {
           lockedDoor = false;      
        }                    
         
     }
     return lockedDoor;
  }
  public boolean doorLocked()
  {
      return lockedDoor;
  }
  public ArrayList getArrayList()
  {
      return door;
  }
}