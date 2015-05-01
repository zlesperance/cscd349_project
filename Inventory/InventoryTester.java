
import java.util.LinkedList;
import java.util.Scanner;

public class InventoryTester
{
   public static void main(String []args)
   {
      ItemDatabasePrototype db = new ItemDatabasePrototype();
      Inventory inventory = new Inventory();
      
      int i, j;
      Scanner s = new Scanner(System.in);
      
      do
      {
         i = mainMenu(s);
         
         if(i == 1)//add an item
         {
            j = inventoryMenu(s);
            
            addInventoryItem(inventory, j, db);
         }
         else if(i == 2)//remove an item
         {
            j = inventoryMenu(s);
            
            removeInventoryItem(inventory, j, db);
         }
         else if(i == 3)//print the entire inventory
         {
            System.out.println(inventory.toString());
         }
         
      }while(i != 4);
      
      //System.out.println(a.compareTo(b));
      //System.out.println(db.getItem(0).toString());
   }
	
   //method separation
   
   public static int mainMenu(Scanner s)
   {
      
      System.out.println("\n1)Add an item to the inventory");
      System.out.println("2)Remove an item from the inventory");
      System.out.println("3)Print the entire inventory");
      System.out.println("4)Quit");
      
      return getInt(s, 4);
   }
	
   //method separation
   
   public static int inventoryMenu(Scanner s)
   {
      System.out.println("1)Use the Items tab");
      System.out.println("2)Use the Equip tab");
      System.out.println("3)Use the Etc tab");
      System.out.println("4)Use the list of equipped gears");
      System.out.println("5)Back");
     
      return getInt(s, 5);
   }
	
   //method separation
   
   public static void addInventoryItem(Inventory i, int t, ItemDatabasePrototype db)
   {
      if(t == 1)
      {
         i.addItem(i.getItems(), i.getItemsName(), db.getItem(0));
         System.out.println(i.toString(i.getItemsName(), i.getItems()));
      }
      else if(t == 2)
      {
         i.addItem(i.getEquip(), i.getEquipName(), db.getItem(1));
         System.out.println(i.toString(i.getEquipName(), i.getEquip()));
      }
      else if(t == 3)
      {
         i.addItem(i.getEtc(), i.getEtcName(), db.getItem(3));
         System.out.println(i.toString(i.getEtcName(), i.getEtc()));
      }
      else if(t == 4)
      {
         i.addItem(i.getGears(), i.getGearsName(), db.getItem(2));
         System.out.println(i.toString(i.getGearsName(), i.getGears()));
      }
   }
	
   //method separation
   
   public static void removeInventoryItem(Inventory i, int t, ItemDatabasePrototype db)
   {
      if(t == 1)
      {
         i.removeItem(i.getItems(), i.getItemsName(), db.getItem(0));
         System.out.println(i.toString(i.getItemsName(), i.getItems()));
      }
      else if(t == 2)
      {
         i.removeItem(i.getEquip(), i.getEquipName(), db.getItem(1));
         System.out.println(i.toString(i.getEquipName(), i.getEquip()));
      }
      else if(t == 3)
      {
         i.removeItem(i.getEtc(), i.getEtcName(), db.getItem(3));
         System.out.println(i.toString(i.getEtcName(), i.getEtc()));
      }
      else if(t == 4)
      {
         i.removeItem(i.getGears(), i.getGearsName(), db.getItem(2));
         System.out.println(i.toString(i.getGearsName(), i.getGears()));
      }
   }
	
   //method separation
   
   public static int getInt(Scanner s, int upperBound)
   {
      int c = 0;
      
      while(c < 1 || c > upperBound)
      {
         System.out.print("\nChoice: ");
         
         c = s.nextInt();
      }
      
      System.out.println();
      
      return c;
   }
}