import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ScreenListeners 
{
  private static boolean titleScreen, gameScreen;
  private static final int WAIT_TIME = GameScreen.getWaitTime(); 
  private static int bulletDelayerCount = 0;
  private static final int keyDelayerMax = 3;
    
  public static void setTitle()
  {
    titleScreen = true;
    gameScreen = false;
  }
  public static void setGame()
  {
    titleScreen = false;
    gameScreen = true;
  }
  
  public static void listen()
  {
    if(titleScreen)
    {
      titlePressedActions();
    }
    if(gameScreen)
    {
        gamePressedActions();
    }
  }
  
  static void titlePressedActions()
  {
    if(StdDraw.isKeyPressed(KeyEvent.VK_ENTER))
      {
        TitleScreen.setChangeScreen();  
        StdDraw.clear();
        GameScreen.init();
      }
  }
  
  static void gamePressedActions()
  {    
    if(StdDraw.isKeyPressed(KeyEvent.VK_Q))
    {
      System.exit(0);
    }
    if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT))
    {
      GameScreen.getHero().setXPos(GameScreen.getHero().getXPos() - 1);
    }
    if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT))
    {
      GameScreen.getHero().setXPos(GameScreen.getHero().getXPos() + 1);
    }
    if(StdDraw.isKeyPressed(KeyEvent.VK_SPACE))
    {
      bulletDelayerCount++;
      if(bulletDelayerCount == keyDelayerMax)
      {
        Bullet.addBullet();
        bulletDelayerCount = 0;
      }
    }
    if(StdDraw.isKeyPressed(KeyEvent.VK_A))
    {
      if(GameScreen.getHero().getSightDeg() <= 180)
        GameScreen.getHero().setSightDeg(GameScreen.getHero().getSightDeg() + 2);
    }
    if(StdDraw.isKeyPressed(KeyEvent.VK_D))
    {
      if(GameScreen.getHero().getSightDeg() >= 0)
        GameScreen.getHero().setSightDeg(GameScreen.getHero().getSightDeg() - 2);
    }
  }
}