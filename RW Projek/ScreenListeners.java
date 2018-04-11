import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ScreenListeners 
{
  private static boolean titleScreen, gameScreen; //Used to specify on which screen the program is
  private static final int WAIT_TIME = GameScreen.getWaitTime();  
  private static int bulletDelayerCount = 0; //The bullets were shooting too fast, so now it shoots once every *keyDelayerMax* times the Space button is pressed
  private static final int keyDelayerMax = 3;
    
  public static void setTitle() //Now showing the title screen, and listens to the TitleScreen key commands
  {
    titleScreen = true;
    gameScreen = false;
  }
  public static void setGame() //Now showing the Gamescreen, and listens to the GameScreen key commands
  {
    titleScreen = false;
    gameScreen = true;
  }
  
  public static void listen()
  {
    if(titleScreen) //If showing the title screen
    {
      titlePressedActions(); //GT titlePressedActionsMethod
    }
    if(gameScreen) //If showing the game screen
    {
        gamePressedActions(); //GT gamePressedActions
    }
  }
  
  static void titlePressedActions()
  {
    if(StdDraw.isKeyPressed(KeyEvent.VK_ENTER)) //When Enter pressed
      {
        TitleScreen.setChangeScreen(); //GT Titlescreen class setChangeScreenMethod
        StdDraw.clear(); 
        GameScreen.init(); //GT GameScreen class int() method
      }
  }
  
  static void gamePressedActions() //Method used to test for key presses when in the game screen
  {    
    if(StdDraw.isKeyPressed(KeyEvent.VK_Q))
    {
      System.exit(0); //Exit the program
    }
    if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT))
    {
      GameScreen.getHero().setXPos(GameScreen.getHero().getXPos() - 1); //Moves the hero one unit to the left
      //Get the X position of the hero, and minus one from it.
    }
    if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT))
    {
      GameScreen.getHero().setXPos(GameScreen.getHero().getXPos() + 1);
      //Same as above, but adds one to X position
    }
    if(StdDraw.isKeyPressed(KeyEvent.VK_SPACE))
    {
      bulletDelayerCount++; //GT above variables
      if(bulletDelayerCount == keyDelayerMax)
      {
        Bullet.addBullet(); //GT Bulelt class. addBullet method
        bulletDelayerCount = 0;
      }
    }
    if(StdDraw.isKeyPressed(KeyEvent.VK_A))
    {
      if(GameScreen.getHero().getSightDeg() <= 180) //Makes the sight move around the hero using trig
        GameScreen.getHero().setSightDeg(GameScreen.getHero().getSightDeg() + 2); //GT setSightDeg method in Hero class
    }
    if(StdDraw.isKeyPressed(KeyEvent.VK_D)) //Same as above
    {
      if(GameScreen.getHero().getSightDeg() >= 0)
        GameScreen.getHero().setSightDeg(GameScreen.getHero().getSightDeg() - 2);
    }
  }
}