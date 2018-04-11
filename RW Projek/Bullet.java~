public class Bullet
{
  private double xpos, ypos, acceleration, velocity, deg;
  private int bulletNum;
  private static final double bulletRadius = 0.5;
  private static final int MAX_BULLETS = 10;
  private static final int WAIT_TIME = GameScreen.getWaitTime();
  private static Bullet[] bulletArray = new Bullet[MAX_BULLETS];
  private static int bulletsVisible = 0;
  
  public Bullet(int bulletNum)
  {
    xpos = GameScreen.getHero().getSightX();
    ypos = GameScreen.getHero().getSightY();
    velocity = 30;
    this.bulletNum = bulletNum;
    drawBullet(); 
    deg = GameScreen.getHero().getSightDeg();
  }
  public void setYPos(double y)
  {
    ypos = y;
  }
  public void setXPos(double x)
  {
    xpos = x;
  }
  public double getXPos()
  {
    return xpos;
  }
  public double getYPos()
  {
    return ypos;
  }
  public static int getMaxBullets()
  {
    return MAX_BULLETS;
  }
  public static int getVisibleBullets()
  {
    return bulletsVisible;
  }
  public static double getBulletSize()
  {
    return bulletRadius;
  }
  public void refreshBullet()
  {
    if(!pastBoundary())
    {
      ypos = ypos + calcDisplacement()* Math.sin(Math.toRadians(deg));
      xpos = xpos + calcDisplacement()* Math.cos(Math.toRadians(deg));
      
    }
    else
    {
      bulletArray[bulletNum] = null;
      bulletsVisible--;
    }
  }
  public int getNumVisibleBullets()
  {
    return bulletsVisible;
  }
  public double calcDisplacement()
  {
    return velocity*WAIT_TIME * Math.pow(10,-3);
  }
  public boolean pastBoundary()
  {
    return ((GameScreen.getMaxY() - ypos) <= 0 || (xpos < GameScreen.getMinX() || xpos > GameScreen.getMaxX()));
  }
  public void drawBullet()
  {
    StdDraw.setPenColor(StdDraw.RED);
    StdDraw.filledCircle(xpos, ypos, bulletRadius);
    StdDraw.setPenColor();
  }
  public static void addBullet()
  {
    if(bulletsVisible + 1 <= MAX_BULLETS)
    {
      bulletsVisible++;
      bulletArray[bulletsVisible-1] = new Bullet(bulletsVisible-1);
    }
    else
      System.out.println("No more bullets available");
  }
  public static void remakeArray()
  {
    Bullet temp;
    for(int i = 0; i < MAX_BULLETS; i++)
    {
      if(bulletArray[i] == null)
      {
        for(int j = i + 1; j < MAX_BULLETS; j++)
        {
          if(bulletArray[j] != null)
          {
            bulletArray[i] = bulletArray[j];
            bulletArray[i].bulletNum = i;
            bulletArray[j] = null;
            j = MAX_BULLETS + 1;
          }
        }
      }
    }
  }
  public static Bullet[] getBulletArray()
  {
    return bulletArray;
  }
  
  public static void removeBullet(int i)
  {
    bulletArray[i] = null;
    bulletsVisible--;
  }
}