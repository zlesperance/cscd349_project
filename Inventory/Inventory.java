
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
	
	//these are used to keep track of how many UNIQUE items are in each inventory tab
	private int itemsCount;
	private int equipCount;
	private int etcCount;
   
   public Inventory()
   {
      this.items = new LinkedList<Item>();
      this.equip = new LinkedList<Item>();
      this.etc = new LinkedList<Item>();
      this.gears = new LinkedList<Item>();
		
		//initialize the gears list with an empty weapon and shield, no atk or def bonuses
		gears.addFirst(new Weapon());
		gears.addLast(new Shield());
		
		this.itemsCount = 0;
		this.equipCount = 0;
		this.etcCount = 0;
   }
   
   //method separation
   
   public LinkedList getItems()
   {
      return this.items;
   }
   
   //method separation
   
   public LinkedList getEquip()
   {
      return this.equip;
   }
   
   //method separation
   
   public LinkedList getEtc()
   {
      return this.etc;
   }
   
   //method separation
   
   public LinkedList getGears()
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
   
	public void incrementItemsCount()
	{
		this.itemsCount ++;
	}
	
   //method separation
	
	public void decrementItemsCount()
	{
		this.itemsCount --;
	}
	
   //method separation
	
	public void incrementEquipCount()
	{
		this.equipCount ++;
	}
	
   //method separation
	
	public void decrementEquipCount()
	{
		this.equipCount --;
	}
	
   //method separation
	
	public void incrementEtcCount()
	{
		this.etcCount ++;
	}
	
   //method separation
	
	public void decrementEtcCount()
	{
		this.etcCount --;
	}
	
   //method separation
   
   public void addItem(LinkedList list, String listName, Object o)
   {
		//Add a gear to the Equip tab.
		if(listName.compareTo("Equip") == 0)
		{
      	list.add(o);
			this.equipCount ++;
		}
		//Add a gear to the list of equipped gears, provided  a hand(L/R) is not already occupied.
		//The Gears list is currently limited to two, a Weapon in list[0], and a Shield in list[1].
		else if(listName.compareTo("Gears") == 0)
		{
			if(o instanceof Weapon)
			{
				list.removeFirst();
				list.addFirst(o);
			}
			else if(o instanceof Shield)
			{
				list.removeLast();
				list.addLast(o);
			}
		}
		//Add an item to the Items tab.
		else if(listName.compareTo("Items") == 0)
		{
			if(findFirstInstance(list, o) > -1)
			{
				((HealingItem)(list.get(findFirstInstance(list, o)))).incrementQuantity();
			}
			else
			{
				list.add(o);
				incrementItemsCount();
			}
		}
		//Add an item to the Etc tab.
		else if(listName.compareTo("Etc") == 0)
		{
			if(findFirstInstance(list, o) > -1)
			{
				((Etc)(list.get(findFirstInstance(list, o)))).incrementQuantity();
			}
			else
			{
				list.add(o);
				incrementEtcCount();
			}
		}
   }
   
   //method separation
   
   public void removeItem(LinkedList list, String listName, Object o)
   {
		//Remove a gear from the Equip tab.
		if(listName.compareTo("Equip") == 0)
		{
			list.remove(o);
		}
		//Remove a gear from the list of equipped gears and replace it with an empty one
		else if(listName.compareTo("Gears") == 0)
		{
      	if(o instanceof Weapon)
			{
				list.removeFirst();
				list.addFirst(new Weapon());
			}
			else if(o instanceof Shield)
			{
				list.removeLast();
				list.addLast(new Shield());
			}
		}
		//Remove a gear from the Items tab.
		else if(listName.compareTo("Items") == 0)
		{
			if(findFirstInstance(list, o) > -1 && ((HealingItem)(list.get(findFirstInstance(list, o)))).getQuantity() > 1)
			{
				((HealingItem)(list.get(findFirstInstance(list, o)))).decrementQuantity();
			}
			else
			{
				list.remove(o);
				decrementItemsCount();
			}
		}
		//Remove an item from the Etc tab.
		else if(listName.compareTo("Etc") == 0)
		{
			if(findFirstInstance(list, o) > -1 && ((Etc)(list.get(findFirstInstance(list, o)))).getQuantity() > 1)
			{
				((Etc)(list.get(findFirstInstance(list, o)))).decrementQuantity();
			}
			else
			{
				list.remove(o);
				decrementEtcCount();
			}
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