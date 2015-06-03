import java.util.Scanner;
import java.util.*;

public class StartMaze implements MoveMaze
{
   private static int col = 0, row = 0, numSpaces, startPosition = 0, rowIndex = 0, colIndex = 0, referenceNumber;
   private boolean valid = false, gameFinished = false;
   private static int[][] twoDMaze;
   private ArrayList doorArray;
   private Scanner scan;
   private String startGame = " ", newMaze = " ", userDirection = " ";
   private Direction direction;
   private DoorsWithKeysMonstersI key;
   
   public StartMaze()
   {
      
   }
   public void createMaze()
   {
        twoDMaze = new int[col][row];
        twoDMaze[col - 1][row - 1] = 4; 
   }
   public void finishGame()
   {
         if(twoDMaze[colIndex][rowIndex] == 4)
         {
            System.out.println("You have reached the exit ");
            gameFinished = true;
         }
   }
   public void enterDimensions()
   {
            scan = new Scanner(System.in);
      System.out.println("Enter the # of columns for maze\n");
      col = scan.nextInt();
      
       System.out.println("Enter the # of rows");
      row = scan.nextInt();
      
      while(col <= 0 || row <= 0)
      {
         System.out.println("Invalid maze, enter again");
         enterDimensions();
         createMaze();
       
      }
   } 
   public void startGame()
   {
      key = new DoorsWithKeysMonsters();
      doorArray = new ArrayList();
      enterDimensions();
      createMaze();
      System.out.println("Before we continue, would you like to start a new maze\n");
      newMaze = scan.next();
      
    
      if(newMaze.equals("Yes") || newMaze.equals("yes"))
      {
         enterDimensions();
         createMaze();
      }

       System.out.println("Player currently at in maze: " + colIndex + " " + rowIndex);
       twoDMaze[0][0] = 3;
       key.placeMonstersDoors();
       doorArray = key.getArrayList(); 
         while(gameFinished != true)
         { 
            getDirection();
            getSpaces();
            traverseMaze();
            key.checkMonstersDoors();
            finishGame();  
          
         }
      
   }
   public String getDirection()
   {
      
      direction = new MoveDirection();
      scan = new Scanner(System.in);
      System.out.println("Which direction would you like to go up, down, right, or left");
      String userEnterDirection = scan.nextLine();
     
      userDirection = direction.getDirection(userEnterDirection);
            
      System.out.println("User entered " + userDirection);
      
      return userDirection;
      
   }   
   public int getSpaces()
   {
      
      direction = new MoveDirection();
      System.out.println("Number of spaces will in the range from 1 to 6");
      numSpaces = direction.getSpaces(userDirection, col, row);
      int colFormula = ((col - 1) - colIndex);
      int rowFormula = ((row - 1) - rowIndex);
      String UED = userDirection;
      
      while(numSpaces > colFormula && UED.equals("up") || numSpaces > colFormula && UED.equals("down"))  
      {
         numSpaces = direction.getSpaces(userDirection, col, row);
      }
      while(numSpaces > rowFormula && UED.equals("left") || numSpaces > rowFormula && UED.equals("right"))  
      {
         numSpaces = direction.getSpaces(userDirection, col, row);
      }

      System.out.println("Number of spaces : " + numSpaces);
      return numSpaces;
   }
 
   
   
 
   
   public void traverseMaze()
   {
      final int playerOne = 3;
      key = new DoorsWithKeysMonsters();
   
      try{   
         if(userDirection == "up")
         {
            twoDMaze[colIndex][rowIndex] = 0;
            referenceNumber = twoDMaze[colIndex-numSpaces][rowIndex];
            colIndex = colIndex - numSpaces;
            rowIndex = rowIndex;
            
               if(key.checkDoor(doorArray) == true)
               {
                    
               }
               else
               {
                     twoDMaze[colIndex][rowIndex] = 3; 
                     System.out.println("Player currently at in maze: " + colIndex + "   " + rowIndex);  
                     valid = true; 
               }
         }
         else if(userDirection == "down")
         {
              
               twoDMaze[colIndex][rowIndex] = 0;
               referenceNumber = twoDMaze[colIndex+numSpaces][rowIndex];
               colIndex = colIndex + numSpaces;
               rowIndex = rowIndex;
               if(key.checkDoor(doorArray) == true)
               {
                  twoDMaze[colIndex - numSpaces][rowIndex] = 3;  
               }
               else
               {
                    twoDMaze[colIndex][rowIndex] = 3; 
                     System.out.println("Player currently at in maze: " + colIndex + "   " + rowIndex);  
                     valid = true; 
               }
                     
           
         }
         else if(userDirection == "right")
         {
            twoDMaze[colIndex][rowIndex] = 0;
            referenceNumber = twoDMaze[colIndex][rowIndex + numSpaces];
            colIndex = colIndex;
            rowIndex = rowIndex + numSpaces;
            if(key.checkDoor(doorArray) == true)
            {
                    
            }
            else
            {
                    twoDMaze[colIndex][rowIndex] = 3; 
                     System.out.println("Player currently at in maze: " + colIndex + "   " + rowIndex);  
                     valid = true; 
            }

         
         }
         else if(userDirection == "left")
         {
            twoDMaze[colIndex][rowIndex] = 0;
            referenceNumber = twoDMaze[colIndex][rowIndex-numSpaces];
            colIndex = colIndex;
            rowIndex = rowIndex - numSpaces;
            if(key.checkDoor(doorArray) == true)
            {
                    
            }
            else
            {
                    twoDMaze[colIndex][rowIndex] = 3; 
                     System.out.println("Player currently at in maze: " + colIndex + "   " + rowIndex);  
                     valid = true; 
            }
         }
      }
      catch(ArrayIndexOutOfBoundsException a)
      {
         while(valid == false)
         {
            System.out.println("Direction invalid: enter another direction");
            getDirection();
            traverseMaze();
         }
      }
        
   }
   public int getReferenceNumber() {
      return referenceNumber;   
   }
   public int getCol(){
      return col;
   }
   public int getRow(){
      return row;
   }
   public int[][] stateTwoDMaze()
   {
      return twoDMaze;
   }
   public int getColIndex()
   {
      return colIndex;
   }
   public int getRowIndex()
   {
      return rowIndex;
   }
   
}