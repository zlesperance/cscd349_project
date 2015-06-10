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
   private Game game;
   private Party party;
   private Antagonist antagonist, antagonistTwo, antagonistThree;
   private Character[] character;
   private Random random;
   
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
            System.out.println("Fighting the monster");
            random = new Random();
            int num = random.nextInt(1);
            
            if(num == 0)
            {
               antagonist = new Goblin();
               //antagonistTwo = new Ghost();
               character[0] = antagonist;
               character[1] = antagonistTwo;
               party = new Party(character);
               game.beginEngagement(party);//methods to fight the monster
            }
            else if(num == 1)
            {
                antagonist = new Goblin();
               //antagonistTwo = new Ghost();
               //antagonistThree = new Slime();
               character[0] = antagonist;
               character[1] = antagonistTwo;
               character[2] = antagonistThree;
               party = new Party(character);
               
            }
           
            
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
     
      
      while(countDoors != ((move.getRow() * move.getCol()) * 0.2))
      {
         q = roww.nextInt(move.getRow() - 1);
         r = coll.nextInt(move.getCol() - 1);
         
              
         if(move.stateTwoDMaze()[q][r] != 1 || move.stateTwoDMaze()[q][r] != 3)
         {
            move.stateTwoDMaze()[q][r] = 1;
            addKeys(q,r);
            countDoors++; 
         }   
      }
      
      while(countMonsters != ((move.getRow() * move.getCol()) * 0.2))
      {
           qq = roww.nextInt(move.getRow() - 1);
           rr = coll.nextInt(move.getCol() - 1);
            
           int ref = move.stateTwoDMaze()[qq][rr]; 
            if(ref == 0)
            {
               move.stateTwoDMaze()[qq][rr] = 2;
               countMonsters++;
            }
            
           
      }
      
      return door;
      
   }//end of placeMonstersDoorsMethod
   public void addKeys(int qq, int rr)
   {
      BaseForDoor base = new BaseForDoor(qq, rr);
      door.add(base);
      
     
   }
   public boolean checkDoor(ArrayList door)
   {
       DoorsWithKeysMonsters k = new DoorsWithKeysMonsters();
      ArrayList<BaseForDoor> array = door;
    
     for(Object d: array)
     {
         BaseForDoor base = (BaseForDoor)d;
               
        if(move.getRowIndex() == base.getRow() && move.getColIndex() == base.getCol())
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