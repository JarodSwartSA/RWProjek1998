import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TitleScreen //Start here
{
  private static boolean changeScreen = false; //Variable used to specify which screen StdDraw is showing, there were issues with the ScreenListeners class otherwise
  
  public TitleScreen()
  {
    ScreenListeners.setTitle();  //Go-To (From now on this is GT) ScreenListeners class method setTitle()
    init(); //Gt init method
    while(!changeScreen) //This loop runs forever until the user presses enter, then it will go to the Game screen
    {
      ScreenListeners.listen(); //GT ScreenListeners listen class
    }
  }
  static void init() //Sets the titles and so for the title screen
  {
    StdDraw.setScale(-10,10);
    StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
    StdDraw.filledSquare(0,0,10);
    StdDraw.setPenColor(StdDraw.YELLOW);
    StdDraw.setFont(new Font("TimesRoman", Font.BOLD, 35));
    StdDraw.text(0,5,"SPACE INVADERS");
    StdDraw.setPenColor(StdDraw.WHITE);
    StdDraw.setFont(new Font("TimesRoman", Font.PLAIN, 30));
    StdDraw.text(0,-5,"PRESS ENTER TO SAVE THE WORLD");
    StdDraw.setPenColor();
  }
  public static void setChangeScreen()
  {
    changeScreen = true; //Now the while loop will end
  }
  
  public static void main(String [] args)
  {
    TitleScreen ts = new TitleScreen(); 
  }
}
