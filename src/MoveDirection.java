import java.lang.*;
import java.util.Random;

public class MoveDirection implements Direction
{
   private String v;
   
   public MoveDirection()
   {
   
   }
   public String getDirection(String direction)
   {
      v = direction;
      
      if(v.equals("up"))
      {
         v = "up"; 
      }
      else if(v.equals("down"))
      {
         v = "down";
      }
      else if(v.equals("right"))
      {
         v = "right";
      }
      else if(v.equals("left"))
      {
         v = "left";
      }
      else
      {
         v = "";
      }
      return v;
   }
   public int getSpaces(String v, int row, int col)
   {
      Random rand = new Random();
      int randomNumber = 0;
      
      if(v.equals("up") || v.equals("down"))
      {
         randomNumber = rand.nextInt((row-2)) + 1;
      }
      else if(v.equals("right") || v.equals("left"))
      {
         randomNumber = rand.nextInt((col-2)) + 1;
      }
      //System.out.println("Number: " + randomNumber);
      return randomNumber;
   }

}