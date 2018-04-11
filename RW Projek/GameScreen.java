import java.awt.geom.*;
import java.awt.image.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class GameScreen 
{
  private static GameScreen screen; //The GameScreen object used for the whole program
  private static Hero hero; //The Hero object used for the whole program
  private static Troop troop; //The Troop object used for the whole program, assuming there will only ever be one group of enemies on the screen. Can be changed
  private static Enemy [] enemyArray; //The array of Enemies from the Troop class 
  private static final double MINX = -20, MINY = -20, MAXX = 20, MAXY = 20; //How big the screen is
  private static final int WAIT_TIME = 15; //Refresh rate
  private static int numBullets = 0;
  
  static int count = 0;
  
  public GameScreen() 
  {
    StdDraw.enableDoubleBuffering();
    runGame(); //GT runGame
  }
  public static void main(String [] args)
  {
    init();
  }
  public static void init() //Used to start the initial game screen objects
  {
    StdDraw.setXscale(MINX,MAXX); 
    StdDraw.setYscale(MINY,MAXY);
    ScreenListeners.setGame(); //GT Screenlisteners class setGame method. 
    hero = new Hero();  //GT Hero class, Hero constructor
    initEnemies(); //GT initEnemies method
    screen = new GameScreen(); //GT GameScreen constructor
  }
  public static void initEnemies()
  {
    troop = new Troop(6, 1.25, StdDraw.BLUE, 3,1); 
    double rand = Math.random();
    if(rand > 0.5) //Just used to determine on which side of the screen the enemies start
      rand = MAXX;
    else
      rand = MINX;
    troop.setSquareTroop(rand); //GT Troop class setSquareTroop method
    enemyArray = troop.getTroop(); // Assigns the array of enemies from troop to a local array here
  }
  
  public static void runGame()
  {
    while(true)
    {
      ScreenListeners.listen(); //GT ScreenListeners Listen class
      refresh(); // Refresh the screen. GT refresh method
    }
  }
  public static double getMaxX()
  {
    return MAXX;
  }
  
  public static double getMinX()
  {
    return MINX;
  }
  
  public static double getMaxY()
  {
    return MAXY;
  }
  
  public static double getMinY()
  {
    return MINY;
  }
  public static Hero getHero()
  {
    return hero;
  }
  
  public static int getWaitTime()
  {
    return WAIT_TIME;
  }
  public static void refresh()
  {
    StdDraw.setPenColor(StdDraw.BLACK);
    hero.drawHero(); //GT hero class, draw hero method
    hero.drawSight(); //GT hero class, drawSight method
    drawEnemies(); //GT drawEnemies method
    drawBullets(); //GT drawBullet method
    StdDraw.pause(WAIT_TIME); //Refresh rate
    StdDraw.show(); //Show the newly drawn objects
    StdDraw.clear();
    //Clear the screen, and the start the whole cycle all over again
  }
  public static void drawEnemies()
  {
    if(!troop.drawTroop()) //GT Troop class, drawTroop Method
    {
      initEnemies(); //If the drawTroop method returns false, then a new troop of enemies is made and drawn
    }
    else if (troop.moveTroop()) //If the troop exists, move the troop. GT Troop class, moveTroop method
    {
      JOptionPane.showMessageDialog(null,"Game Over"); //If the method returns true, it measn the troop has reached the floor. Begin the game again
      StdDraw.pause(1500);
      init();
    }
      
  }
  
  public static void drawBullets()
  {
    Bullet[] bul = Bullet.getBulletArray(); //Gets the current bullet array from the Bullet class
    for(int i = 0; i < Bullet.getVisibleBullets(); i++) //GT Bullet class, getVisibleBullets method
    {
      if(bul[i] != null)// If the bullet in this position exists
      {
        bul[i].refreshBullet(); //GT Bullet class, refreshBullet method
        Bullet.remakeArray(); //Remakes array so that all the null positions are at the end
        if(bul[i] != null) 
          bul[i].drawBullet(); 
        if(bul[i] != null && killEnemies(bul[i].getXPos(), bul[i].getYPos())) //GT killEnemies method 
        {
          Bullet.removeBullet(i); //Now that it has killed an enemy, the bullet must be destroyed
        }
      }   
    }
  }
  public static boolean killEnemies(double x, double y)
  { 
    //This is a boolean method, that returns false if it has not killed any enemies
    boolean output = false;
    for(int i = 0; i < enemyArray.length; i++ )
    {
      if(enemyArray[i] != null && enemyArray[i].testKilled(x,y)) //GT testKilled method, Enemy class
      {
        troop.killEnemy(i); //GT kill enemy method, Troop class. Sets enemy to null
        output = true;
      }
    }
    return output;
  }
}