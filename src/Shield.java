
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
   
   //method separation
   
   public Shield(String n, int w, String g, int d, int id)//name, weight, gear type, def, itemID
   {
      super(n, "Equipment", w, id);
      
      this.gType = g;
      this.def = d;
   }
   
   //method separation
   
   public String getGType()
   {
      return this.gType;
   }
   
   //method separation
   
   public int getDef()
   {
      return this.def;
   }
   
   //method separation
   
   @Override
   public String toString()
   {
      return super.toString(gType);
   }
   
   //method separation
   
   public void use(Inventory i, Party p, StartMaze sm) throws Exception
   {
      throw new Exception("This item cannot be \"used\".");
   }
}