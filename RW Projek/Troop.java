import java.awt.*;
public class Troop
{
  private int troopSize,form,arraySize, enemyMoveDelay;
  private double enemySize;
  private Enemy[] troop; //The  array holding all the enemies in a troop
  private int currentDirection = 1;
  private static int delayMovementMax = 5, delayMovementCount = 0; //The enemies were moving once every refresh beat, and that was too fast. So now they move only once every *delayMovementMax* refreshes. 
  //*delayMovementCount* holds how many times it has waited
  
  public Troop(int troopSize, double enemySize, Color troopColor, int enemyMoveDelay, int form)
  {
    Enemy.setEnemySize(enemySize); //Assuming all the enemies in a troop are the same colour, and size, there are static variables in Enemy that control this
    Enemy.setEnemyColor(troopColor);
    this.enemyMoveDelay = enemyMoveDelay;
    this.troopSize = troopSize;
    this.enemySize = enemySize;
    if(form == 1) //Forming different troop shapes - TO DO //I was thinking we can maybe make other types of troops that are in different forms, but we can do that later
    {
      arraySize = troopSize*troopSize;
      troop = new Enemy[arraySize];
    }
  }   
  public boolean drawTroop() //I made this to return a boolean value. IF the value is false, it means was nothing was drawn, and thus all the enemies were killed, or the game has just begun and the enemies have not been made yet
  {
    boolean output = false;
    for(int i = 0; i < arraySize; i++)
    {
      if(troop[i] != null)
      {
          troop[i].drawEnemy(); 
          output = true;
      }
    }
    return output;
  }
  public boolean moveTroop() //The plan is to make this a boolean method, so that returning false means it has reached the bottom
  {
    boolean touchFloorHero = false; //Have not implemented yet, but used to see if troop touching the floor or hero
    if(delayMovementCount++ == delayMovementMax) // GT the two variables above. 
    {
      delayMovementCount = 0; //If it moves, reset the counter
      boolean againstLeftSide = (troop[getLeftMostEnemy()].getXPos() - enemySize) <= GameScreen.getMinX(); //GT getLeftMostEnemy method
      boolean againstRightSide = (troop[getRightMostEnemy()].getXPos() + enemySize) >= GameScreen.getMaxX(); //GT getRightMostEnemy method
      //With these above two boolean statements, if the distance between the left most enemy and the left side of the screen is less than the radius of the enemy. it is at the left edge of the screen (true)
      if(againstLeftSide)
        currentDirection = 1; //Go right
      else if(againstRightSide)
        currentDirection = -1; //Go left
      if(againstLeftSide || againstRightSide) //If it is against the edge of the screen
      {
        for(int i = 0; i < arraySize; i++)
        {
          if(troop[i] != null) //If there is an enemy in that position
            troop[i].setYPos(troop[i].getYPos() - Math.abs(currentDirection*2*enemySize)); //Move all the enemies down
          if(troop[i] != null && troop[i].touchHeroFloor())
            touchFloorHero = true;
        }
        while(((troop[getLeftMostEnemy()].getXPos() - enemySize) <= GameScreen.getMinX()) || ((troop[getRightMostEnemy()].getXPos() + enemySize) >= GameScreen.getMaxX())) //Same boolean statements as above
        {
          //Sometimes, when the troop is moved, there may be an overlap with an enemy and the screen edge
          //When this happens, it gets caught in a loop, and it will never move away from the edge (See above if statement)
          // So we make a while loop  so that the entire troop moves until it is away from the edge
          for(int i = 0; i < arraySize; i++)
          {
            if(troop[i] != null)
              troop[i].setXPos(troop[i].getXPos() + currentDirection);
            if(troop[i] != null && troop[i].touchHeroFloor())
            touchFloorHero = true;
          }
        }
      }
      else //If it is not against the edge of the screen, just carry on in the current direction
      {
        for(int i = 0; i < arraySize; i++)
        {
          if(troop[i] != null)
            troop[i].setXPos(troop[i].getXPos() + currentDirection);
          if(troop[i] != null && troop[i].touchHeroFloor())
            touchFloorHero = true;
        }
      }
    }
    return touchFloorHero;
  }
  public void setSquareTroop(double beginX) //One type of troop shape
  { 
    //Basically just draws all the enemies in the troop array, organising them in a square
    double x, y; 
    double startY = GameScreen.getMaxY();
    int count = 0;
    for(int i = 0; i < troopSize; i++)
    {
      y = (startY) + (i * 2 * enemySize);
      for(int j = 0; j < troopSize; j++)
      {
        x = beginX + (j * 2 * enemySize);
        troop[count] = new Enemy(x,y);
        count++;
      }
    }
  }
  
  public int getRightMostEnemy() //Same as getLeftMostEnemy method, just with the right side
  {
    int output = -1;
    int hold = troopSize -1 ;
    for(int i = hold; i < arraySize; i = i + troopSize)
    {
      if(troop[i] != null) //If there is an object here, it is the right most object
      {
        return i;
      }
      else if((i + troopSize) >= arraySize) //If there is not another row, go back one column and check again
      {
        i = hold - 1 - troopSize;
      }
    }
    return output; //If return -1, there are no more objects in array
  }
  
  public int getLeftMostEnemy()
  {
    //This method is used to find the furthest left object in the troop. Then this object's coordinates can be used to test if it is next to the edge of the screen
    int output = -1;
    int hold = 0;
    for(int i = hold; i < arraySize; i = i + troopSize)
    {
      if(troop[i] != null) //If there is an object here, it is the right most object
      {
        return i;
      }
      else if((i + troopSize) >= arraySize) //If there is not another row, go back one column and check again
      {
        i = hold + 1 - troopSize;
      }
    }
    return output; //If return -1, there are no more objects in array
  }
  
  public void killEnemy(int enemyNum)
  {
    troop[enemyNum] = null;
  }
  
  public Enemy[] getTroop()
  {
    return troop;
  }
}