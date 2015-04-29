
public class Shield extends Item
{
   private String gType;//shield
   private int def;
   
   public Shield()
   {
      super("Empty Hand", "Equipment", 0);
      gType = "";
      def = 0;
   }
   
   public Shield(String n, int w, String g, int d)//name, weight, gear type, def
   {
      super(n, "Equipment", w);
      
      this.gType = g;
      this.def = d;
   }
   
   @Override
   public String toString()
   {
      return super.toString(gType, 1);
   }
}