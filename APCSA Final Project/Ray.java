//Stephen Labys
/* On my honor as a student I have neither
given nor recieved any unauthorized aid on this assignment */

/* All graphics/game mechanics are derived from AP Invaders project, and other mechanics, such as key listeners or mouse listeners, adapted from Oracle documentation. 
Raycasting concept and mechanics adapted from https://en.wikipedia.org/wiki/Ray_casting#:~:text=Ray%20casting%20is%20the%20most,scenes%20to%20two%2Ddimensional%20images.*/

import java.lang.Math;
import java.awt.Color;
import java.awt.Graphics;

public class Ray {
   private double posX;
   private double posY;
   private double rayAngle;
   private double precision = 10;
   
   public Ray(double x, double y, double a) {
      posX = x;
      posY = y;
      rayAngle = a;
   }
   
   public double getX() {
      return posX;
   }
   
   public double getY() {
      return posY;
   }
   
   public void increment(Map M) {
      posX += (Math.cos(Math.toRadians(rayAngle)) / precision);
      posY += (Math.sin(Math.toRadians(rayAngle)) / precision);
   }
   
   public int getLayer(Map M) {
      return M.getValue(posX, posY);
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