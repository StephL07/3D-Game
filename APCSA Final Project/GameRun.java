//Stephen Labys
/* On my honor as a student I have neither
given nor recieved any unauthorized aid on this assignment */

/* All graphics/game mechanics are derived from AP Invaders project, and other mechanics, such as key listeners or mouse listeners, adapted from Oracle documentation. 
Raycasting concept and mechanics adapted from https://en.wikipedia.org/wiki/Ray_casting#:~:text=Ray%20casting%20is%20the%20most,scenes%20to%20two%2Ddimensional%20images.*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.Timer;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.Color;
import java.awt.Graphics;
import java.lang.Math;
import java.util.*;

public class GameRun extends JPanel implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
   
   private int tick = 0;
   private int cycle = 15;
   private int tick2 = 0;
   private int cycle2 = 4;
   private int longTick = 0;
   private int longCycle = 1000;
   private final Timer movement = new Timer(5, new Movement());
   private int mouseCenter;
   private Color gray = new Color(43, 51, 57);
   private Color darkGray = new Color(34, 35, 37);


   Map m = new Map();    
   Player player = new Player((50.0 * 4), 60.0, m);
   private ArrayList<Enemy> aliens = new ArrayList<Enemy>();
   private int alienCounter = 5;
      
   public GameRun() {
      addKeyListener(this);
      addMouseMotionListener(this);
      addMouseWheelListener(this);
      addMouseListener(this);
      movement.start();
      genAliens();
   }
   
   public void genAliens() {
      double xVal = 180.0;
      double yVal = 50.0;
      for (int i = 0; i < alienCounter; i++) {
         Enemy alien = new Enemy(xVal, yVal, 1);
         aliens.add(alien);
         yVal -= 4.0;
      }
   }  
   
   public void addAlien(int amount) {
      double xVal = 7.0;
      double yVal = 6.0;
      for (int i = 0; i < amount; i++) {
         Enemy alien = new Enemy(xVal, yVal, 1);
         aliens.add(alien);
      }
   }
   
   public void drawHealth(Graphics g) {
      g.drawString(player.getHealth() + "", 100, 20);
    }
    
   public void paintComponent(Graphics g) {
        //set the background
        g.setColor(darkGray);
        g.fillRect(0, 0, 1440, 900);
        g.setColor(gray);
        g.fillRect(0, 0, 1440, 450);

        player.drawWalls(m, g, aliens);
        g.setColor(Color.WHITE);
        player.drawWeapon(g);
        player.drawDisplay(g);
        for (int i = 0; i < aliens.size(); i++) {
           if (aliens.get(i).getHealth() <= 0) {
             aliens.remove(i);
           }
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }
   
    @Override
    public void keyPressed(KeyEvent ev) {
    int key = ev.getKeyCode();
    
    if (key == KeyEvent.VK_W) {
        if (player.moveForward() == 1) {
           m.removeAlienSpawner();
        }
        else if (player.moveForward() == 2) {
           m.removeWeaponBox();
        }
    } 
    
    else if (key == KeyEvent.VK_A) {
        player.moveLeft();
    } 
    
    else if (key == KeyEvent.VK_S) {
        player.moveBack();
    }
    
    else if (key == KeyEvent.VK_D) {
        player.moveRight();
    }
    
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    public void mouseMoved(MouseEvent e) {
       int mX = (int)e.getPoint().getX();
       int pointTo = mouseCenter - mX;
       player.changeAngle(pointTo);
       
       mouseCenter = mX;
       //System.out.println(mX);
    }
    
    public void mouseDragged(MouseEvent me) {
      mouseMoved(me);
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    public void mousePressed(MouseEvent e) {
      player.fire();
    }
    
    public void mouseWheelMoved(MouseWheelEvent e) {
      int move = e.getWheelRotation();
      player.setScrollWheel(move);
    }
    
    class Movement implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            tick = (tick + 1) % cycle;
            tick2 = (tick2 + 1) % cycle2;
            longTick = (longTick + 1) % longCycle;
            if (tick2 == 0) {
               for (Enemy alien : aliens) {
                  alien.move(player, m);
               }
            }
            if (tick == 0 && player.getBlast()) {
               player.unstate();
            }
            else if (tick == 0 && player.getBlast2()) {
               player.unstate2();
            }
            if (longTick == 0) {
               int num = m.getCounter();
               alienCounter += 2 * num;
               int b = m.getCounter();
               double x = 5.0;
               double y = 5.0;
               int type = (int)((5 * Math.random()) + 1);
               if (type == 5) {
                  type = 2;
               }
               else {
                  type = 1;
               }
               if (b == 0) {
                     x = 180.0;
                     y = 80.0;
                  }
                  else if (b == 1) {
                     x = 150.0; 
                     y = 50.0;
                  }
                  else if (b == 2) {
                     x = 120.0;
                     y = 80.0;
                  }
                  else if (b == 3){
                     x = 10.0;
                     y = 80.0;
                  }
                  else {
                     num = 0;
                  }
               for (int i = 0; i < alienCounter; i++) {
                  Enemy alien = new Enemy(x, y, type);
                  aliens.add(alien);
               }
            }
            repaint();
        }
    }
}