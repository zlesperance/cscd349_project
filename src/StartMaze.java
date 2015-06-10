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
        twoDMaze = new int[row][col];
        twoDMaze[row - 1][col - 1] = 4; 
   }
   public void finishGame()
   {
         if(twoDMaze[rowIndex][colIndex] == 4)
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
      
      while(row <= 0 || col <= 0)
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
            inventoryMaze();
            traverseMaze();
            key.checkMonstersDoors();
            finishGame();  
          
         }
      
   }
   public void inventoryMaze()
   {
      System.out.println("Before you move would you like to change your inventory");
      String askUserInventory = scan.nextLine();
      
      
      
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
      numSpaces = direction.getSpaces(userDirection, row, col);
      int rowFormula = ((row - 1) - rowIndex);
      int colFormula = ((col - 1) - colIndex);
      String UED = userDirection;
      
      while(numSpaces > rowFormula && UED.equals("up") || numSpaces > rowFormula && UED.equals("down"))  
      {
         numSpaces = direction.getSpaces(userDirection, row, col);
      }
      while(numSpaces > colFormula && UED.equals("left") || numSpaces > colFormula && UED.equals("right"))  
      {
         numSpaces = direction.getSpaces(userDirection, row, col);
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
            twoDMaze[rowIndex][colIndex] = 0;
            referenceNumber = twoDMaze[rowIndex-numSpaces][colIndex];
            rowIndex = rowIndex - numSpaces;
            colIndex = colIndex;
            
               if(key.checkDoor(doorArray) == true)
               {
                    System.out.println("Door is locked");
               }
               else if(key.checkDoor(doorArray) != true)
               {
                     twoDMaze[rowIndex][colIndex] = 3; 
                     System.out.println("Player currently at in maze: " + rowIndex + "   " + colIndex);  
                     valid = true; 
               }
         }
         else if(userDirection == "down")
         {
              
               twoDMaze[rowIndex][colIndex] = 0;
               referenceNumber = twoDMaze[rowIndex+numSpaces][colIndex];
               rowIndex = rowIndex + numSpaces;
               colIndex = colIndex;
               
               if(key.checkDoor(doorArray) == true)
               {
                  System.out.println("Door is locked");    
               }
               else if(key.checkDoor(doorArray) != true)
               {
                    twoDMaze[rowIndex][colIndex] = 3; 
                     System.out.println("Player currently at in maze: " + rowIndex + "   " + colIndex);  
                     valid = true; 
               }
                     
           
         }
         else if(userDirection == "right")
         {
            twoDMaze[rowIndex][colIndex] = 0;
            referenceNumber = twoDMaze[rowIndex][colIndex + numSpaces];
            rowIndex = rowIndex;
            colIndex = colIndex + numSpaces;
            if(key.checkDoor(doorArray) == true)
            {
                System.out.println("Door is locked");    
            }
            else if(key.checkDoor(doorArray) != true)
            {
                    twoDMaze[rowIndex][colIndex] = 3; 
                     System.out.println("Player currently at in maze: " + rowIndex + "   " + colIndex);  
                     valid = true; 
            }

         
         }
         else if(userDirection == "left")
         {
            twoDMaze[rowIndex][colIndex] = 0;
            referenceNumber = twoDMaze[rowIndex][colIndex-numSpaces];
            rowIndex = rowIndex;
            colIndex = colIndex - numSpaces;
            if(key.checkDoor(doorArray) == true)
            {
                 System.out.println("Door is locked");   
            }
            else if(key.checkDoor(doorArray) != true)
            {
                    twoDMaze[rowIndex][colIndex] = 3; 
                     System.out.println("Player currently at in maze: " + rowIndex + "   " + colIndex);  
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