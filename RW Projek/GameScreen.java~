import java.awt.geom.*;
import java.awt.image.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class GameScreen 
{
  private static GameScreen screen;
  private static Hero hero;
  private static Troop troop;
  private static Enemy [] enemyArray;
  private static Bullet [] bullet = new Bullet[10];
  private static final double MINX = -20, MINY = -20, MAXX = 20, MAXY = 20;
  private static final int WAIT_TIME = 15;
  private static int numBullets = 0;
  
  static int count = 0;
  
  public GameScreen()
  {
    StdDraw.enableDoubleBuffering();
    runGame();
  }
  public static void main(String [] args)
  {
    init();
  }
  public static void runGame()
  {
    while(true)
    {
      ScreenListeners.listen();
      refresh();
    }
  }
  public static void init()
  {
    StdDraw.setXscale(MINX,MAXX);
    StdDraw.setYscale(MINY,MAXY);
    ScreenListeners.setGame();
    hero = new Hero();
    initEnemies();
    screen = new GameScreen();
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
  public static void initEnemies()
  {
    troop = new Troop(6, 1.25, StdDraw.BLUE, 3,1);
    double rand = Math.random();
    if(rand > 0.5)
      rand = MAXX;
    else
      rand = MINX;
    troop.setSquareTroop(rand);
    enemyArray = troop.getTroop();
  }
  public static int getWaitTime()
  {
    return WAIT_TIME;
  }
  public static void refresh()
  {
    StdDraw.setPenColor();
    hero.drawHero();
    hero.drawSight();
    drawEnemies();
    drawBullets();
    StdDraw.pause(WAIT_TIME);
    StdDraw.show();
    StdDraw.clear();
  }
  
  public static void drawBullets()
  {
    Bullet[] bul = Bullet.getBulletArray();
    for(int i = 0; i < Bullet.getVisibleBullets(); i++)
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
  public static void drawEnemies()
  {
    if(!troop.drawTroop())
    {
      initEnemies();;
    }
    else
    troop.moveTroop();
  }
}