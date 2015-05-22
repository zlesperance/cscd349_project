import java.util.Scanner;
import java.util.*;

public class StartMaze implements MoveMaze
{
   private int col = 0, row = 0, numSpaces, startPosition = 0, rowIndex = 0, colIndex = 0, referenceNumber;
   private boolean valid = false;
   private int[][] twoDMaze;
   private final int ROW = 0;
   private final int COL = 0;
   private Scanner scan;
   private String startGame = " ", newMaze = " ", userDirection = " ";
   private Direction direction;
   
   
   public StartMaze()
   {

   }
   public void createMaze()
   {
        twoDMaze = new int[col][row]; 
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
      enterDimensions();
      createMaze();
      System.out.println("Before we continue, would you like to start a new maze\n");
      newMaze = scan.next();
      
    
      if(newMaze.equals("Yes") || newMaze.equals("yes"))
      {
         enterDimensions();
         createMaze();
      }
       placeMonstersDoors();
       System.out.println("Player currently at in maze: " + colIndex + "   " + rowIndex);
       twoDMaze[0][0] = 3;
       
         while(!startGame.equals("quit"))
         { 
            getDirection();
       
            getSpaces();
            traverseMaze();
            checksDoorsMonsters();
            
            System.out.println("Type quit if you want to quit else type no");
            startGame = scan.next();
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
  
   
      try{   
         if(userDirection == "up")
         {
            twoDMaze[colIndex][rowIndex] = 0;
            referenceNumber = twoDMaze[colIndex-numSpaces][rowIndex];
            twoDMaze[colIndex - numSpaces][rowIndex] = 3; 
            colIndex = colIndex - numSpaces;
            rowIndex = rowIndex; 
            System.out.println("Player currently at in maze: " + colIndex + "   " + rowIndex);  
            valid = true; 
         }
         else if(userDirection == "down")
         {
            twoDMaze[colIndex][rowIndex] = 0;
            referenceNumber = twoDMaze[colIndex+numSpaces][rowIndex];
            twoDMaze[colIndex + numSpaces][rowIndex] = 3;
            colIndex = colIndex + numSpaces;
            rowIndex = rowIndex; 
            System.out.println("Player currently at in the maze: " + colIndex + "   " + rowIndex);
            valid = true; 
         }
         else if(userDirection == "right")
         {
            twoDMaze[colIndex][rowIndex] = 0;
            referenceNumber = twoDMaze[colIndex][rowIndex + numSpaces];
            twoDMaze[colIndex][rowIndex + numSpaces] = 3;
            colIndex = colIndex;
            rowIndex = rowIndex + numSpaces;
            System.out.println("Player currently at in the maze: " + colIndex + "   " + rowIndex);
            valid = true;
         }
         else if(userDirection == "left")
         {
            twoDMaze[colIndex][rowIndex] = 0;
            referenceNumber = twoDMaze[colIndex][rowIndex-numSpaces];
            twoDMaze[colIndex][rowIndex - numSpaces] = 3;
            colIndex = colIndex;
            rowIndex = rowIndex - numSpaces;
            System.out.println("Player currently at in the maze: " + colIndex + "  " + rowIndex);
            valid = true;
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
   
 
   
   //idea: strategy
   //maybe have last two in different class reference StartMaze;
   public void checksDoorsMonsters()  //should be in a differnt class
   {
         
         
         if(referenceNumber == 0)
         {
            System.out.println("No monsters or doors");
            
         }
         else if(referenceNumber == 1)
         {
            System.out.println("Player encourted a door");
         }
         else if(referenceNumber == 2)
         {
            System.out.println("Player encountered a monster");
         }
         
   }
   
   
   public void placeMonstersDoors()
   {
      Random coll = new Random();
      Random roww = new Random();
      int countDoors = 0, countMonsters = 0;
      int q, r = 0, doors = 1, monsters = 2;
     
      
      while(countDoors != 3)
      {
         q = coll.nextInt((col - 2)) +1;
         r = roww.nextInt((row - 2)) + 1;
         if(twoDMaze[q][r] != 1)
         {
            twoDMaze[q][r] = 1;
            countDoors++; 
         }   
      }
      while(countMonsters != 3)
      {
           q = coll.nextInt((col - 2)) +1;
           r = roww.nextInt((row - 2)) + 1;
            
           int ref = twoDMaze[q][r]; 
            if(ref != 1)
            {
               twoDMaze[q][r] = 2;
               countMonsters++;
            }
            else if(ref != 2)
            {
               twoDMaze[q][r] = 2;
               countMonsters++;  
            }
      }
   }//end of placeMonstersDoorsMethod
}