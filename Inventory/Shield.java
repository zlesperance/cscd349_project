
public class Shield extends Item
{
   private String gType;//shield
   private int def;
   
   public Shield()
   {
      super("Empty Hand", "Equipment", 0, 0);
      gType = "";
      def = 0;
   }
   
   public Shield(String n, int w, String g, int d, int id)//name, weight, gear type, def, itemID
   {
      super(n, "Equipment", w, id);
      
      this.gType = g;
      this.def = d;
   }
   
   @Override
   public String toString()
   {
      return super.toString(gType, 1);
   }
}