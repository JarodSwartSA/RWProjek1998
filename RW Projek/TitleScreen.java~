import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TitleScreen 
{
  private static boolean changeScreen = false;
  public TitleScreen()
  {
    ScreenListeners.setTitle();
    init();
    while(!changeScreen)
    {
      ScreenListeners.listen(); 
    }
  }
  static void init()
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
    changeScreen = true;
  }
  public static void main(String [] args)
  {
    TitleScreen ts = new TitleScreen();
  }
}
