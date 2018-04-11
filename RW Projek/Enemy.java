import java.awt.*;

public class Enemy
{
  private double xpos, ypos;
  private static double size;
  private static Color enemyColor;
  
  public Enemy(double xpos, double ypos)
  {
    this.xpos = xpos;
    this.ypos = ypos;
  }
  
  public void drawEnemy()
  {
    StdDraw.setPenColor(enemyColor);
    StdDraw.filledCircle(xpos, ypos, size);
  }
  
  public double getXPos()
  {
    return xpos;
  }
  
  public double getYPos()
  {
    return ypos;
  }
  public void setXPos(double x)
  {
    xpos = x;
  }
  public void setYPos(double y)
  {
    ypos = y;
  }
  public static void setEnemySize(double enemySize)
  {
    size = enemySize;
  }
  public static void setEnemyColor(Color color)
  {
    enemyColor = color;
  }
  public boolean testKilled(double x, double y)
  {
    return (Math.sqrt(Math.pow((x - xpos),2)) + Math.pow((y - ypos),2) <= (size + Bullet.getBulletSize()));
  }
  public boolean touchHeroFloor()
  {
    boolean output = false;
    if((Math.sqrt(Math.pow((xpos - GameScreen.getHero().getXPos()),2)) + Math.pow((ypos - GameScreen.getHero().getYPos()),2) <= (size + GameScreen.getHero().getHeroRadius())))
      output = true;
    else if(Math.abs(ypos - GameScreen.getMinY()) <= size)
      output = true;
    return output;
  }
}