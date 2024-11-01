//Stephen Labys
/* On my honor as a student I have neither
given nor recieved any unauthorized aid on this assignment */

/* All graphics/game mechanics are derived from AP Invaders project, and other mechanics, such as key listeners or mouse listeners, adapted from Oracle documentation. 
Raycasting concept and mechanics adapted from https://en.wikipedia.org/wiki/Ray_casting#:~:text=Ray%20casting%20is%20the%20most,scenes%20to%20two%2Ddimensional%20images.*/

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.*;
import java.applet.*;

public class Wall {
   private int wallHeight;
   private int pos;
   private int color;
   private int zOffset;

   public Wall(int h, int p, int  s, int z) {
      wallHeight = h;
      pos = p;
      color = s;
      zOffset = z;
   }
   
   public void drawWall(Graphics g) {
      int numToSubtract = 3000/wallHeight;
      if (numToSubtract > 200) {
         numToSubtract = 200;
      }
      
      Color red = new Color((200 - numToSubtract), 0, 0);
      Color blue = new Color(50 - (numToSubtract / 4), 50 - (numToSubtract / 4), 50 - (numToSubtract / 4));
      Color green = new Color(0, (200- numToSubtract), 0);
      Color white = new Color((200 - numToSubtract), (200 - numToSubtract), (200 - numToSubtract));
      Color yellow = new Color((200- numToSubtract), (200 - numToSubtract), 0);
      Color cyan = new Color (0, (200 - numToSubtract), (200 - numToSubtract));
      Color brightWhite = new Color ((255 - (numToSubtract / 2)), (255 - (numToSubtract / 2)), (255 - (numToSubtract / 2)));
      
      if (color == 2) {
         g.setColor(red);
      }
      else if (color == 3) {
         g.setColor(Color.BLACK);
      }
      else if (color == 4) {
         g.setColor(white);
      }
      else if (color == 5) {
         g.setColor(green);
      }
      else if (color == 6) {
         g.setColor(yellow);
      }
      else if (color == 7) {
         g.setColor(cyan);
      }
      else if (color == 8) {
         g.setColor(brightWhite);
      }
      else {
         g.setColor(blue);
      }
      g.fillRect(pos - 4, (450 - wallHeight - zOffset), 4, 2 * wallHeight);
   }
}