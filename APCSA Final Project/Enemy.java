//Stephen Labys
/* On my honor as a student I have neither
given nor recieved any unauthorized aid on this assignment */

/* All graphics/game mechanics are derived from AP Invaders project, and other mechanics, such as key listeners or mouse listeners, adapted from Oracle documentation. 
Raycasting concept and mechanics adapted from https://en.wikipedia.org/wiki/Ray_casting#:~:text=Ray%20casting%20is%20the%20most,scenes%20to%20two%2Ddimensional%20images.*/

import java.awt.Color;
import java.awt.Graphics;
import java.lang.Math;

public class Enemy {
   private int health = 100;
   private double x;
   private double y;
   private int alienType = 1;
   private boolean found = false;
   
   public Enemy(double xVal, double yVal, int type) {
      x = xVal;
      y = yVal;
      alienType = type;
   }
   
   public double getX() {
      return x;
   }
   
   public double getY() {
      return y;   
   }
   
   public int getType() {
      return alienType;
   }
   
   public void punch() {
      health -= 5;
   }
   
   public void damage(int i) {
      health -= 5 * i;
   }
   
   public int getHealth() {
      return health;
   }
   
   public void move(Player p, Map MapToUse) {
      double distance2 = Math.sqrt(Math.pow(Math.abs(x - p.getX()), 2) + Math.pow(Math.abs(y - p.getY()), 2));
      if (distance2 < 50) {
         double viewAngle = 0.0;
         if (p.getX() > x) {
            if (p.getY() > y) {
               viewAngle = Math.atan((p.getY() - y) / (p.getX() - x));
               viewAngle = Math.toDegrees(viewAngle);
            }
            else {
               viewAngle = Math.atan((-1 * (y - p.getY())) / (x - p.getX()));
               viewAngle = 360 - Math.toDegrees(viewAngle);
            }
         }
         else {
            if (p.getY() > y) {
               viewAngle = Math.atan((p.getY() - y) / (-1 * (p.getX() - x)));
               viewAngle = 180 - Math.toDegrees(viewAngle);
            }
            else {
               viewAngle = Math.atan((-1 * (p.getY() - y)) / (-1 * (p.getX() - x)));
               viewAngle = 180 + Math.toDegrees(viewAngle);
            }
         }
         double testPosX = x + (0.5 * Math.cos(Math.toRadians(viewAngle)));
         double testPosY = y + (0.5 * Math.sin(Math.toRadians(viewAngle)));
         if (MapToUse.getValue(testPosX, testPosY) == 0) {
            x += 0.5 * Math.cos(Math.toRadians(viewAngle));
            y += 0.5 * Math.sin(Math.toRadians(viewAngle));
         }
      }
      else {
         double viewAngle = 360 * Math.random();
         double testPosX = x + (0.5 * Math.cos(Math.toRadians(viewAngle)));
         double testPosY = y + (0.5 * Math.sin(Math.toRadians(viewAngle)));
         if (MapToUse.getValue(testPosX, testPosY) == 0) {
            x += 0.5 * Math.cos(Math.toRadians(viewAngle));
            y += 0.5 * Math.sin(Math.toRadians(viewAngle));
         }
      }
  
   }
}
