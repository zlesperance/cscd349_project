
import java.util.LinkedList;
import java.util.Scanner;

public class InventoryTester
{
   static HealingItem a = new HealingItem("Apple", "Consumable", 2, 19, 0);
   static Weapon b = new Weapon("Knife[0]", 40, "Dagger", 17);
   static Shield c = new Shield("Guard[0]", 130, "Shield",  6);
   static Etc d = new Etc("Jellopy", 1);
   
   public static void main(String []args)
   {
      Inventory inventory = new Inventory();
      
      int i, j;
      Scanner s = new Scanner(System.in);
      
      do
      {
         i = mainMenu(s);
         
         if(i == 1)//add an item
         {
            j = inventoryMenu(s);
            
            addInventoryItem(inventory, j);
         }
         else if(i == 2)//remove an item
         {
            j = inventoryMenu(s);
            
            removeInventoryItem(inventory, j);
         }
         else if(i == 3)//print the entire inventory
         {
            System.out.println(inventory.toString());
         }
         
      }while(i != 4);
      
      
      
      /*
      inventory.addItem(inventory.getItems(), inventory.getItemsName(), a);
      inventory.addItem(inventory.getEquip(), inventory.getEquipName(), b);
      inventory.addItem(inventory.getEquip(), inventory.getEquipName(), c);
      inventory.addItem(inventory.getEtc(), inventory.getEtcName(), d);
      
      //System.out.println(inventory.toString());
      
      inventory.addItem(inventory.getGears(), inventory.getGearsName(), b);
      inventory.addItem(inventory.getGears(), inventory.getGearsName(), c);
      
      //System.out.println(inventory.toString(inventory.getGearsName(), inventory.getGears()));
      
      inventory.removeItem(inventory.getGears(), inventory.getGearsName(), c);
      
      //System.out.println(inventory.toString(inventory.getGearsName(), inventory.getGears()));
      
      inventory.addItem(inventory.getItems(), inventory.getItemsName(), a);
      
      System.out.println(inventory.toString());
      
      inventory.removeItem(inventory.getItems(), inventory.getItemsName(), a);
      
      System.out.println(inventory.toString());*/
   }
	
   //method separation
   
   public static int mainMenu(Scanner s)
   {
      
      System.out.println("1)Add an item to the inventory");
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
     
      return getInt(s, 4);
   }
	
   //method separation
   
   public static void addInventoryItem(Inventory i, int t)
   {
      if(t == 1)
      {
         i.addItem(i.getItems(), i.getItemsName(), a);
         System.out.println(i.toString(i.getItemsName(), i.getItems()));
      }
      else if(t == 2)
      {
         i.addItem(i.getEquip(), i.getEquipName(), b);
         System.out.println(i.toString(i.getEquipName(), i.getEquip()));
      }
      else if(t == 3)
      {
         i.addItem(i.getEtc(), i.getEtcName(), d);
         System.out.println(i.toString(i.getEtcName(), i.getEtc()));
      }
      else
      {
         i.addItem(i.getGears(), i.getGearsName(), c);
         System.out.println(i.toString(i.getGearsName(), i.getGears()));
      }
   }
	
   //method separation
   
   public static void removeInventoryItem(Inventory i, int t)
   {
      if(t == 1)
      {
         i.removeItem(i.getItems(), i.getItemsName(), a);
         System.out.println(i.toString(i.getItemsName(), i.getItems()));
      }
      else if(t == 2)
      {
         i.removeItem(i.getEquip(), i.getEquipName(), b);
         System.out.println(i.toString(i.getEquipName(), i.getEquip()));
      }
      else if(t == 3)
      {
         i.removeItem(i.getEtc(), i.getEtcName(), d);
         System.out.println(i.toString(i.getEtcName(), i.getEtc()));
      }
      else
      {
         i.removeItem(i.getGears(), i.getGearsName(), c);
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