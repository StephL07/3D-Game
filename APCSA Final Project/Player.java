//Stephen Labys
/* On my honor as a student I have neither
given nor recieved any unauthorized aid on this assignment */

/* All graphics/game mechanics are derived from AP Invaders project, and other mechanics, such as key listeners or mouse listeners, adapted from Oracle documentation. 
Raycasting concept and mechanics adapted from https://en.wikipedia.org/wiki/Ray_casting#:~:text=Ray%20casting%20is%20the%20most,scenes%20to%20two%2Ddimensional%20images.*/

import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.*;
import java.applet.*;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.*;
import java.lang.Math;


public class Player {
   private double posX;
   private double posY;
   private double viewAngle = 90.0;
   private double viewAngleZ = 0.0;
   private int[] inventory = {1, 0, 0, 0};
   private int health = 10000;
   private int ammo = 10;
   private int active = 0;
   private int score = 0;
   private boolean state = false;
   private boolean state2 = false;
   private Map MapToUse;   
    
   public Player(double positionX, double positionY, Map m) {
      posX = positionX;
      posY = positionY;
      MapToUse = m;
   }
   
   public double getX() {
      return posX;
   }
   
   public double getY() {
      return posY;
   }
   
   public double getViewAngle() {
      return viewAngle;
   }
   
   public double getViewAngleZ() {
      return viewAngleZ;
   }
   
   public boolean getBlast() {
      return state;
   }
   
   public boolean getBlast2() {
      return state2;
   }
   
   public void setScrollWheel(int a) {
      state = false;
      if (a > 0) {
         active++;
         if (active > 3) {
            active = 0;
         }
      }
      else {
         active--;
         if (active < 0) {
            active = 3;
         }
      }
   }
   
   public int moveForward() {
      double testPosX = posX + Math.cos(Math.toRadians(viewAngle));
      double testPosY = posY + Math.sin(Math.toRadians(viewAngle));
      if (MapToUse.getValue(testPosX, testPosY) == 0) {
         posX += Math.cos(Math.toRadians(viewAngle));
         posY += Math.sin(Math.toRadians(viewAngle));
      }
      else if (MapToUse.getValue(testPosX, testPosY) == 2) {
         if (ammo < 30) {
            ammo += 10;
         }
         if (ammo > 30) {
            ammo = 30;
         }
      }
      else if (MapToUse.getValue(testPosX, testPosY) == 4) {
         health = 10000;
      }
      else if (MapToUse.getValue(testPosX, testPosY) == 7) {
         addWeapon();
         return 2;
      }
      else if (MapToUse.getValue(testPosX, testPosY) == 5) {
         return 1;
      }
      return 0;
   }
   
   public void moveLeft() {
      double tempAngle = viewAngle + 90.0;
      if (tempAngle > 360.0) {
         tempAngle -= 360.0;
      }
      double testPosX = posX + Math.cos(Math.toRadians(tempAngle));
      double testPosY = posY + Math.sin(Math.toRadians(tempAngle));
      if (MapToUse.getValue(testPosX, testPosY) == 0) {
         posX += Math.cos(Math.toRadians(tempAngle));
         posY += Math.sin(Math.toRadians(tempAngle));
      }
      else if (MapToUse.getValue(testPosX, testPosY) == 2) {
         if (ammo < 30) {
            ammo += 10;
         }
         if (ammo > 30) {
            ammo = 30;
         }
      }
      else if (MapToUse.getValue(testPosX, testPosY) == 4) {
         health = 10000;
      }
      else if (MapToUse.getValue(testPosX, testPosY) == 7) {
         addWeapon();
      }
   }
   
   public void moveBack() {
      double testPosX = posX - Math.cos(Math.toRadians(viewAngle));
      double testPosY = posY - Math.sin(Math.toRadians(viewAngle));
      if (MapToUse.getValue(testPosX, testPosY) == 0) {
         posX -= Math.cos(Math.toRadians(viewAngle));
         posY -= Math.sin(Math.toRadians(viewAngle));
      }
      else if (MapToUse.getValue(testPosX, testPosY) == 2) {
         if (ammo < 30) {
            ammo += 10;
         }
         if (ammo > 30) {
            ammo = 30;
         }
      }
      else if (MapToUse.getValue(testPosX, testPosY) == 4) {
         health = 10000;
      }
      else if (MapToUse.getValue(testPosX, testPosY) == 7) {
         addWeapon();
      }
   }
   
   public void moveRight() {
      double tempAngle = viewAngle - 90.0;
      if (tempAngle < 0) {
         tempAngle += 360.0;
      }
      double testPosX = posX + Math.cos(Math.toRadians(tempAngle));
      double testPosY = posY + Math.sin(Math.toRadians(tempAngle));
      if (MapToUse.getValue(testPosX, testPosY) == 0) {
          posX += Math.cos(Math.toRadians(tempAngle));
          posY += Math.sin(Math.toRadians(tempAngle));
      }
      else if (MapToUse.getValue(testPosX, testPosY) == 2) {
         if (ammo < 30) {
            ammo += 10;
         }
         if (ammo > 30) {
            ammo = 30;
         }
      }
      else if (MapToUse.getValue(testPosX, testPosY) == 4) {
         health = 10000;
      }
      else if (MapToUse.getValue(testPosX, testPosY) == 7) {
         addWeapon();
      }
   }
   
   public void addWeapon() {
      if (inventory[1] == 0) {
         inventory[1] = 2;
      }
      else if (inventory[2] == 0) {
         inventory[2] = 3;
      }
      else {
         inventory[3] = 4;
      }
   }
   
   public void setActive() {
      active++;
      if (active > 2) {
         active = 0;
      }
   }
   
   public void addAmmo() {
      ammo += 10;
   }
   
   public void loseAmmo() {
      ammo--;
   }
   
   public int getHealth() {
      return health;
   }
   
   public void addHealth() {
      health += 10;
   }
   
   public void loseHealth() {
      health -= 10;
   }
   
   public void setWeapon(int i) {
      if (inventory[0] == 0) {
         inventory[0] = i;
         ammo += 30;
      }
      else if (inventory[1] == 0) {
         inventory[1] = i;
         ammo += 20;
      }
      else if (inventory[2] == 0){
         inventory[2] = i;
         ammo += 20;
      }
      else {
         inventory[3] = i;
         ammo += 20;
      }
   }
   
   public void incrementScore() {
      score++;
   }
   
   public void changeAngle(int a) {
      double angle = (double)a;
      if (angle < 0) {
         if (Math.abs(angle) > viewAngle) {
            angle -= viewAngle;
            viewAngle = 360.0 - angle;
            return;
         }
         viewAngle += angle;
         return;
      }   
      else if (angle > 0) {
         if (angle + viewAngle > 360) {
            viewAngle = (viewAngle + angle - 360.0);
            return;
         }
         else {
            viewAngle += angle;
            return;
         }
      }
      return;
   }
   
   public void changeAngleZ(int a) {
      double angle = (double)a;
      if (angle < 0) {
         if (Math.abs(angle) > viewAngle) {
            angle -= viewAngle;
            viewAngle = 360.0 - angle;
            return;
         }
         viewAngle += angle;
         return;
      }   
      else if (angle > 0) {
         if (angle + viewAngle > 360) {
            viewAngle = (viewAngle + angle - 360.0);
            return;
         }
         else {
            viewAngle += angle;
            return;
         }
      }
      return;
   }
   
   public void drawWalls(Map m, Graphics g, ArrayList<Enemy> aliens) {
      Graphics2D g2 = (Graphics2D) g;
      Image displayImg = Toolkit.getDefaultToolkit().getImage("alien.png");
      double rayAngle = (viewAngle - (90/2));
      int pos = 1440;
      while (rayAngle < (viewAngle + (90/2))) {
      
         Ray R = new Ray(posX, posY, rayAngle);
         while (R.getLayer(m) == 0) {
            R.increment(m);
         }
         
         double distance = Math.sqrt(Math.pow(posX - R.getX(), 2) + Math.pow(posY - R.getY(), 2));
         if (distance == 0.0) {
            distance = 0.1;
         }
         
         int color = R.getLayer(m);
         
         int wallHeight = (int)(1440.0 / distance);
         
         Wall W = new Wall(wallHeight, pos, color, (int)viewAngleZ);
         W.drawWall(g);
          
         rayAngle += 0.25;
         pos -= 4;
      }
      for (int i = 0; i < aliens.size(); i++) {
         double a = 0;
         if (aliens.get(i).getX() > posX) {
            if (aliens.get(i).getY() > posY) {
               a = Math.atan((aliens.get(i).getY() - posY) / (aliens.get(i).getX() - posX));
               a = Math.toDegrees(a);
            }
            else {
               a = Math.atan((-1 * (aliens.get(i).getY() - posY)) / (aliens.get(i).getX() - posX));
               a = 360 - Math.toDegrees(a);
            }
         }
         else {
            if (aliens.get(i).getY() > posY) {
               a = Math.atan((aliens.get(i).getY() - posY) / (-1 * (aliens.get(i).getX() - posX)));
               a = 180 - Math.toDegrees(a);
            }
            else {
               a = Math.atan((-1 * (aliens.get(i).getY() - posY)) / (-1 * (aliens.get(i).getX() - posX)));
               a = 180 + Math.toDegrees(a);
            }
         }
         double distance2 = Math.sqrt(Math.pow(Math.abs(aliens.get(i).getX() - posX), 2) + Math.pow(Math.abs(aliens.get(i).getY() - posY), 2));
         double lowerBoundAngle = viewAngle - 45.0;
         double upperBoundAngle = viewAngle + 45.0;
         if (lowerBoundAngle < 0) {
            lowerBoundAngle = 360 + lowerBoundAngle;
         }
         if (upperBoundAngle > 360) {
            upperBoundAngle = 360 - lowerBoundAngle;
         }
         boolean found = false;
         if (a > lowerBoundAngle && a < upperBoundAngle) {
            Ray R2 = new Ray(posX, posY, a);
            while (R2.getLayer(m) == 0) {
              R2.increment(m);
              if (Math.round(R2.getX()) == Math.round(aliens.get(i).getX()) && Math.round(R2.getY()) == Math.round(aliens.get(i).getY())) {
                  found = true;
              }
            }
         }
         if (found) {
         if (Math.abs(viewAngle - a) < (100 / distance2)) {
            if (state) {
               aliens.get(i).damage(inventory[active]);
            }
            else if (state2 && distance2 < 5) {
               aliens.get(i).punch();
            }
         }
         if (distance2 < 10) {
            damage();
         }
            if (aliens.get(i).getHealth() < 50) {
               Image display2Img = Toolkit.getDefaultToolkit().getImage("alien2.png");
               int columnToDraw = 720;
               columnToDraw += (int)((720 / 45) * (viewAngle - a));
               int alienWidth = (int)(2000/distance2);
               int yAdd = (int)(1440/distance2);
               g2.drawImage(display2Img, columnToDraw - alienWidth, 450 + yAdd - alienWidth, alienWidth, alienWidth, null);
            }
            
            else {
               int columnToDraw = 720;
               columnToDraw += (int)((720 / 45) * (viewAngle - a));
               int alienWidth = (int)(2000/distance2);
               int yAdd = (int)(1440/distance2);
               g2.drawImage(displayImg, columnToDraw - alienWidth, 450 + yAdd - alienWidth, alienWidth, alienWidth, null);
            }
         }
      }
      g2.finalize();
   }
   
   public void damage() {
      health -= 2;
      if (health < 0) {
         health = 0;
      }
   }
   
   public void drawInventory(Graphics g) {
      Graphics2D g2 = (Graphics2D) g;
      Image displayImg = Toolkit.getDefaultToolkit().getImage("selected.png");
      if (active == 0) {
         g2.drawImage(displayImg, 960, 762, null);
      }
      else if (active == 1) {
         g2.drawImage(displayImg, 1080, 762, null);
      }
      else if (active == 2) {
         g2.drawImage(displayImg, 1200, 762, null);
      }
      else {
         g2.drawImage(displayImg, 1320, 762, null);
      }
      
      Image displayImg2 = Toolkit.getDefaultToolkit().getImage("blank.png");
      
      for (int i = 0; i < 4; i++) {
         if (inventory[i] == 0) {
            displayImg2 = Toolkit.getDefaultToolkit().getImage("blank.png");
            g2.drawImage(displayImg2, 960 + (120 * i), 762, null);
         }
         else if (inventory[i] == 1) {
            displayImg2 = Toolkit.getDefaultToolkit().getImage("weapon1sprite.png");
            g2.drawImage(displayImg2, 960 + (120 * i), 762, null);
         }
         else if (inventory[i] == 2) {
            displayImg2 = Toolkit.getDefaultToolkit().getImage("weapon2sprite.png");
            g2.drawImage(displayImg2, 960 + (120 * i), 762, null);
         }
         else if (inventory[i] == 3) {
            displayImg2 = Toolkit.getDefaultToolkit().getImage("weapon3sprite.png");
            g2.drawImage(displayImg2, 960 + (120 * i), 762, null);
         }
         else if (inventory[i] == 4) {
            displayImg2 = Toolkit.getDefaultToolkit().getImage("weapon4sprite.png");
            g2.drawImage(displayImg2, 960 + (120 * i), 762, null);
         }
      }
      g2.finalize();
   }
   
   public void drawDisplay(Graphics g) {
      Graphics2D g2 = (Graphics2D) g;
      
      Image displayImg = Toolkit.getDefaultToolkit().getImage("display.png");
      g2.drawImage(displayImg, 0, 762, 1440, 100, null);
      g.setFont(new Font("CourierNew", Font.PLAIN, 40)); 
      int health2 = (int)Math.round((health / 100));
      if (health2 == 100) {
         g.drawString(health2 + "", 50, 795);
      }
      else if (health2 > 9) {
         g.drawString(health2 + "", 64, 795);
      }
      else {
         g.drawString(health2 + "", 76, 795);
      }
      if (ammo > 9) {
         g.drawString(ammo + "", 240, 795);
      }
      else {
         g.drawString(ammo + "", 250, 795);
      }
      g2.finalize();
      drawInventory(g);
   }
   
   public void drawWeapon(Graphics g) {
      
      Graphics2D g2 = (Graphics2D) g;
      Image displayImg = Toolkit.getDefaultToolkit().getImage("blank.png");
      if (state) {
         Image temp = Toolkit.getDefaultToolkit().getImage("blast.png");
         g2.drawImage(temp, 420, 400, null);
      }
      if (state2) {
         Image temp2 = Toolkit.getDefaultToolkit().getImage("fist2.png");
         g2.drawImage(temp2, 420, 500, null);
      }
      if (inventory[active] == 0 && !state2) {
         displayImg = Toolkit.getDefaultToolkit().getImage("fists.png");
      }
      else if (inventory[active] == 1) {
         displayImg = Toolkit.getDefaultToolkit().getImage("weapon1.png");
      }
      else if (inventory[active] == 2) {
         displayImg = Toolkit.getDefaultToolkit().getImage("weapon2.png");
      }
      else if (inventory[active] == 3) {
         displayImg = Toolkit.getDefaultToolkit().getImage("weapon3.png");
      }
      else if (inventory[active] == 4){
         displayImg = Toolkit.getDefaultToolkit().getImage("weapon4.png");
      }
      g2.drawImage(displayImg, 420, 500, null);
      g2.finalize();
      return;
   }
   
   public void fire() {
      if ((ammo > 0) && (inventory[active] != 0)) {
         ammo--;
         state = true;
      }
      else {
         inventory[3] = 0;
         if (inventory[active] == 0) {
            state2 = true;
         }
      }
   }
   
   public void unstate() {
      state = false;
   }
   
   public void unstate2() {
      state2 = false;
   }
   
   public int[] getCoords() {
      int[] array = {0, 0};
      int counter = (int)Math.round(posX / 4);
      int counter2 = (int)Math.round(posY / 4);
      array[0] = counter;
      array[1] = counter2;
      return array;
   }
}