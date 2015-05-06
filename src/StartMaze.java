import java.util.Scanner;
import java.util.*;

public class StartMaze implements MoveMaze
{
   private int col = 0, row = 0, numSpaces, startPosition = 0, rowIndex = 0, colIndex = 0, referenceNumber;
   private int[][] twoDMaze;
   private final int ROW = 0;
   private final int COL = 0;
   private Scanner scan;
   private String startGame = " ", newMaze = " ", userDirection = " ";
   private Direction direction;
   
   
   public StartMaze()
   {

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
      //account for invalid commands
      System.out.println("User entered " + userDirection);
      
      return userDirection;
      
   }   
   public int getSpaces()
   {
      
      direction = new MoveDirection();
      System.out.println("Number of spaces will in the range from 1 to 6");
      numSpaces = direction.getSpaces(userDirection, col, row);
      //check for numSpaces don't go outside of bounds of array
      System.out.println("Number of spaces : " + numSpaces);
      return numSpaces;
   }
   
   public void createMaze()
   {
        twoDMaze = new int[col][row]; 
   }
   
   public void traverseMaze()
   {
      final int playerOne = 3;
      int i = 0, j = 0;
     
      
      if(startPosition <= 0)
         twoDMaze[0][0] = playerOne;
         startPosition++;
      
      if(userDirection == "up")
      {
         twoDMaze[colIndex][rowIndex] = 0;
         referenceNumber = twoDMaze[colIndex-numSpaces][rowIndex];
         twoDMaze[colIndex - numSpaces][rowIndex] = 3; 
         colIndex = colIndex - numSpaces;
         rowIndex = rowIndex;    
      }
      else if(userDirection == "down")
      {
         twoDMaze[colIndex][rowIndex] = 0;
         referenceNumber = twoDMaze[colIndex+numSpaces][rowIndex];
         twoDMaze[colIndex + numSpaces][rowIndex] = 3;
         colIndex = colIndex + numSpaces;
         rowIndex = rowIndex;  
      }
      else if(userDirection == "right")
      {
         twoDMaze[colIndex][rowIndex] = 0;
         referenceNumber = twoDMaze[colIndex][rowIndex + numSpaces];
         twoDMaze[colIndex][rowIndex + numSpaces] = 3;
         colIndex = colIndex;
         rowIndex = rowIndex + numSpaces;
      }
      else if(userDirection == "left")
      {
         twoDMaze[colIndex][rowIndex] = 0;
         referenceNumber = twoDMaze[colIndex][rowIndex-numSpaces];
         twoDMaze[colIndex][rowIndex - numSpaces] = 3;
         colIndex = colIndex;
         rowIndex = rowIndex - numSpaces;
      }
    
        
   }
   
   public void enterDimensions()
   {
            scan = new Scanner(System.in);
      System.out.println("Enter the # of columns for maze\n");
      col = scan.nextInt();
      
      //account for negative col number
      
      System.out.println("Enter the # of rows");
      row = scan.nextInt();
      
      //account for row number
   }
   
   public void checksDoorsMonsters()
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
         twoDMaze[q][r] = 1;
         countDoors++; 
      }
      while(countMonsters != 3)
      {
           q = coll.nextInt((col - 2)) +1;
            r = roww.nextInt((row - 2)) + 1;
            if(twoDMaze[q][r] != 1)
            {
               twoDMaze[q][r] = 2;
            }
            else
            {
                 q = coll.nextInt((col - 2)) +1;
                 r = roww.nextInt((row - 2)) + 1;
                 twoDMaze[q][r] = 2;
            }
          
            countMonsters++;
      }
   }//end of placeMonstersDoorsMethod
}