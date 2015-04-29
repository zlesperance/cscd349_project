
public class Weapon extends Item
{
   private String gType;//ex: sword, knife, staff 
   private int atk;
   
   public Weapon()
   {
      super("Empty Hand", "Equipment", 0);
      gType = "";
      atk = 0;
   }
   
   public Weapon(String n, int w, String g, int a)//name, weight, gear type, atk
   {
      super(n, "Equipment", w);
      
      this.gType = g;
      this.atk = a;
   }
   
   @Override
   public String toString()
   {
      return super.toString(gType, 1);
   }
}