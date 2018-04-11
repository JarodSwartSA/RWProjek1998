import java.awt.*;
public class Troop
{
  private int troopSize,form,arraySize, enemyMoveDelay;
  private double enemySize;
  private Enemy[] troop;
  private int currentDirection = 1;
  private static int delayMovementMax = 5, delayMovementCount = 0;
  
  public Troop(int troopSize, double enemySize, Color troopColor, int enemyMoveDelay, int form)
  {
    Enemy.setEnemySize(enemySize);
    Enemy.setEnemyColor(troopColor);
    this.enemyMoveDelay = enemyMoveDelay;
    this.troopSize = troopSize;
    this.enemySize = enemySize;
    if(form == 1) //Forming different troop shapes - TO DO
    {
      arraySize = troopSize*troopSize;
      troop = new Enemy[arraySize];
    }
  }
  
  public static void main (String [] args)
  {
    StdDraw.setXscale(-20, 20);
    StdDraw.setYscale(-20,20);
    Troop t = new Troop(6, 1.0, StdDraw.RED, 2,1);
    t.setSquareTroop(0);
    t.drawTroop();
    while(true)
    {
      t.moveTroop();
      StdDraw.clear();
      t.drawTroop();
      StdDraw.pause(100);
    }
  }
   
  public boolean drawTroop()
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
  public void setSquareTroop(double beginX)
  {
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
  
  public int getRightMostEnemy()
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
  
  public boolean moveTroop()
  {
    boolean touchFloorHero = false;
    if(delayMovementCount++ == delayMovementMax)
    {
      delayMovementCount = 0;
      boolean againstLeftSide = (troop[getLeftMostEnemy()].getXPos() - enemySize) <= GameScreen.getMinX();
      boolean againstRightSide = (troop[getRightMostEnemy()].getXPos() + enemySize) >= GameScreen.getMaxX();
      if(againstLeftSide)
        currentDirection = 1;
      else if(againstRightSide)
        currentDirection = -1;
      if(againstLeftSide || againstRightSide)
      {
        for(int i = 0; i < arraySize; i++)
        {
          if(troop[i] != null)
            troop[i].setYPos(troop[i].getYPos() - Math.abs(currentDirection*2*enemySize));
         // if(troop[i].touchHeroFloor())
          //  touchFloorHero = true;
        }
        while(((troop[getLeftMostEnemy()].getXPos() - enemySize) <= GameScreen.getMinX()) || ((troop[getRightMostEnemy()].getXPos() + enemySize) >= GameScreen.getMaxX()))
        {
          for(int i = 0; i < arraySize; i++)
          {
            if(troop[i] != null)
              troop[i].setXPos(troop[i].getXPos() + currentDirection);
            //if(troop[i].touchHeroFloor())
            //touchFloorHero = true;
          }
        }
      }
      else
      {
        for(int i = 0; i < arraySize; i++)
        {
          if(troop[i] != null)
            troop[i].setXPos(troop[i].getXPos() + currentDirection);
          //if(troop[i].touchHeroFloor())
            //touchFloorHero = true;
        }
      }
    }
    return touchFloorHero;
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