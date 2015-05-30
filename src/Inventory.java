
import java.util.LinkedList;

public class Inventory
{
   private LinkedList<Item> items;
   private LinkedList<Item> equip;
   private LinkedList<Item> etc;
   private LinkedList<Item> gears;
   
   private final String inventoryItems = "Items";
   private final String inventoryEquip = "Equip";
   private final String inventoryEtc = "Etc";
   private final String inventoryGears = "Gears";
   
   public Inventory()
   {
      this.items = new LinkedList<Item>();
      this.equip = new LinkedList<Item>();
      this.etc = new LinkedList<Item>();
      this.gears = new LinkedList<Item>();
		
		//initialize the gears list with an empty weapon and shield, no atk or def bonuses
		gears.addFirst(new Weapon());
		gears.addLast(new Shield());
   }
   
   //method separation
   
   public LinkedList<Item> getItems()
   {
      return this.items;
   }
   
   //method separation
   
   public LinkedList<Item> getEquip()
   {
      return this.equip;
   }
   
   //method separation
   
   public LinkedList<Item> getEtc()
   {
      return this.etc;
   }
   
   //method separation
   
   public LinkedList<Item> getGears()
   {
      return this.gears;
   }
   
   //method separation
   
   public String getItemsName()
   {
      return this.inventoryItems;
   }
   
   //method separation
   
   public String getEquipName()
   {
      return this.inventoryEquip;
   }
   
   //method separation
   
   public String getEtcName()
   {
      return this.inventoryEtc;
   }
   
   //method separation
   
   public String getGearsName()
   {
      return this.inventoryGears;
   }
	
   //method separation
   
   public void addItem(HealingItem hItem)
   {
      addItem(items, hItem);
   }
   
   //method separation
   
   public void addItem(UsableItem uItem)
   {
      addItem(items, uItem);
   }
   
   //method separation
   
   public void addItem(Shield sItem)
   {
      equip.add(sItem);
   }
   
   //method separation
   
   public void addItem(Weapon wItem)
   {
		equip.add(wItem);
   }
   
   //method separation
   
   public void addItem(Etc eItem)
   {
      addItem(etc, eItem);
   }
   
   //method separation
   
   public void addGear(Weapon wItem)
   {
      gears.removeFirst();
		gears.addFirst(wItem);
   }
   
   //method separation
   
   public void addGear(Shield sItem)
   {
      gears.removeLast();
		gears.addLast(sItem);
   }
   
   //method separation
   
   public void removeEquip(Weapon w)
   {
      equip.remove(w);
   }
   
   //method separation
   
   public void removeEquip(Shield s)
   {
      equip.remove(s);
   }
   
   //method separation
   
   public void removeItem(UsableItem u)
   {
      removeItem(items, u);
   }
   
   //method separation
   
   public void removeItem(HealingItem h)
   {
      removeItem(items, h);
   }
   
   //method separation
   
   public void removeItem(Etc e)
   {
      removeItem(etc, e);
   }
   
   //method separation
   
   public void addItem(LinkedList list, Item i)
   {
      if(findFirstInstance(list, i) > -1)
		{
			i.incrementQuantity();
		}
		else
		{
			list.add(i);
		}
   }
   
   //method separation
   
   public void removeItem(LinkedList list, Item i)
   {
      if(findFirstInstance(list, i) > -1 && i.getQuantity() > 1)
		{
         i.decrementQuantity();
		}
		else
		{
			list.remove(i);
		}
   }
   
   //method separation
   
   public String toString()
   {
      return "Inventory: \n\n" + "\nItems: \n" + items.toString() + "\nEquip: \n" + equip.toString()
               + "\nEtc: \n" + etc.toString() + "\nGears: \n" + gears.toString();
   }
   
	//method separation
	
   public String toString(String listName, LinkedList list)
   {
      return listName + ": \n" + list.toString();
   }
	
	//method separation
	
	//finds the first occurrence of an item within the list which has a name matching o.iName
	public int findFirstInstance(LinkedList list, Object o)
	{
		int i = 0;
		Object arr[] = list.toArray();
		
		while(i < arr.length)
		{
			if(((Item)(arr[i])).equals(o))
			{
				return i;
			}
		}
		
		return -1;
	}
}