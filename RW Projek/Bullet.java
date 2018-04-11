public class Bullet
{
  private double xpos, ypos, acceleration, velocity, deg;
  private int bulletNum;
  private static final double bulletRadius = 0.5; 
  private static final int MAX_BULLETS = 10; //Max amount of bullets that can be seen on the screen
  private static final int WAIT_TIME = GameScreen.getWaitTime();
  private static Bullet[] bulletArray = new Bullet[MAX_BULLETS]; //The Bullets that can currently be seen
  private static int bulletsVisible = 0; //The number of bullets currently visible
  
  public Bullet(int bulletNum) //Sets the initla position of the bullet
  {
    xpos = GameScreen.getHero().getSightX(); // The bullet will be shot from where the sight is
    ypos = GameScreen.getHero().getSightY();
    velocity = 30;
    this.bulletNum = bulletNum; //The number of the bullet in the array
    drawBullet(); 
    deg = GameScreen.getHero().getSightDeg(); //The angle the sight makes to the horizontal, measured from the centre of the hero
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
    if(!pastBoundary()) // GT pastBoundary method
    {
      ypos = ypos + calcDisplacement()* Math.sin(Math.toRadians(deg)); //Using trig, just increases the x and y coordinates of the bullet
      xpos = xpos + calcDisplacement()* Math.cos(Math.toRadians(deg));
      
    }
    else //If the bullet is past the edges of the screen
    {
      bulletArray[bulletNum] = null; //Destroy the bullet
      bulletsVisible--; //There is one less bullet on the screen
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
  public boolean pastBoundary() // Tests to see if the bullet has passed the left, right or top edges on the screen
  {
    return ((GameScreen.getMaxY() - ypos) <= 0 || (xpos < GameScreen.getMinX() || xpos > GameScreen.getMaxX()));
  }
  public void drawBullet() //Draws the bullet at its x and y coords
  {
    StdDraw.setPenColor(StdDraw.RED);
    StdDraw.filledCircle(xpos, ypos, bulletRadius);
  }
  public static void addBullet()
  {
    if(bulletsVisible + 1 <= MAX_BULLETS) //If another bullet can be shot without going over the limit
    {
      bulletsVisible++; 
      bulletArray[bulletsVisible-1] = new Bullet(bulletsVisible-1); //Adds a new bullet to the array
    }
    else
      System.out.println("No more bullets available");
  }
  public static void remakeArray() //Sorts the array so that all null places are at the end
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