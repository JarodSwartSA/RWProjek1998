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
    StdDraw.pause(WAIT_TIME);
    StdDraw.show();
    StdDraw.clear();
  }
  public static void drawEnemies()
  {
    if(!troop.drawTroop()) //GT Troop class, drawTroop Method
    {
      initEnemies(); //If the drawTroop method returns false, then a new troop of enemies is made and drawn
    }
    else
    troop.moveTroop(); //If the troop exists, move the troop. GT Troop class, moveTroop method
  }
  
  public static void drawBullets()
  {
    Bullet[] bul = Bullet.getBulletArray(); //Gets the current bullet array from the Bullet class
    for(int i = 0; i < Bullet.getVisibleBullets(); i++) //GT Bullet class, getVisibleBullets method
    {
      if(bul[i] != null)
      {
        bul[i].refreshBullet();
        Bullet.remakeArray();
        if(bul[i] != null)
          bul[i].drawBullet();
        if(bul[i] != null && killEnemies(bul[i].getXPos(), bul[i].getYPos()))
        {
          Bullet.removeBullet(i);
        }
      }   
    }
  }
  public static boolean killEnemies(double x, double y)
  {
    boolean output = false;
    for(int i = 0; i < enemyArray.length; i++ )
    {
      if(enemyArray[i] != null && enemyArray[i].testKilled(x,y))
      {
        troop.killEnemy(i);
        output = true;
      }
    }
    return output;
  }
}