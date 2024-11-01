//Stephen Labys
/* On my honor as a student I have neither
given nor recieved any unauthorized aid on this assignment */

/* All graphics/game mechanics are derived from AP Invaders project, and other mechanics, such as key listeners or mouse listeners, adapted from Oracle documentation. 
Raycasting concept and mechanics adapted from https://en.wikipedia.org/wiki/Ray_casting#:~:text=Ray%20casting%20is%20the%20most,scenes%20to%20two%2Ddimensional%20images.*/

import java.io.*;
import java.awt.Color;
import java.awt.Graphics;
import java.lang.Math;

public class Map {
   
   private int counter = 0;
   private int counter2 = 0;
   private String[][] map = {
      {"W", "W", "W", "R", "C", "W", "W", "W", "W", "W", "W", "W", "R", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W"},
      {"W", " ", " ", " ", " ", " ", " ", " ", "W", " ", " ", " ", " ", "W", " ", " ", " ", " ", " ", " ", " ", " ", "W", " ", " ", "Y", " ", "R", "H", " ", " ", "B", " ", " ", " ", " ", " ", " ", " ", "R", "H", " ", "W", " ", " ", " ", " ", "W", "W", "W", "W", "W", "W", "W", "W"},
      {"W", " ", " ", " ", " ", " ", " ", " ", "W", " ", " ", " ", " ", "W", " ", " ", " ", " ", " ", " ", " ", " ", "W", " ", " ", "B", " ", " ", " ", " ", " ", "Y", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "W", " ", " ", " ", " ", "W", "W", "W", "W", "W", "W", "W", "W"},
      {"W", " ", " ", " ", " ", " ", " ", " ", "W", " ", " ", " ", " ", "W", " ", " ", " ", " ", " ", " ", " ", " ", "W", " ", " ", "Y", " ", " ", " ", " ", " ", "B", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "W", " ", " ", " ", " ", "W", "W", "W", "W", "W", "W", "W", "W"},
      {"W", " ", " ", " ", " ", " ", " ", " ", "W", " ", " ", " ", " ", "W", " ", " ", " ", " ", " ", " ", " ", " ", "W", " ", " ", "B", " ", " ", " ", " ", " ", "Y", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "W", " ", " ", " ", " ", "W", "W", "W", "W", "W", "W", "W", "W"},
      {"W", " ", " ", " ", " ", " ", " ", " ", "W", " ", " ", " ", " ", "W", " ", " ", " ", " ", " ", " ", " ", " ", "W", " ", " ", "C", " ", " ", " ", " ", " ", "B", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "W", " ", " ", " ", " ", "W", "W", "W", "W", "W", "W", "W", "W"},
      {"W", "W", "W", "W", "W", "T", " ", "T", "W", " ", " ", " ", " ", "T", " ", " ", " ", " ", " ", " ", " ", "H", "W", " ", " ", " ", " ", " ", " ", " ", " ", "Y", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "W", " ", " ", " ", " ", "W", "W", "W", "W", "W", "W", "W", "W"},
      {"W", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "R", "W", " ", " ", " ", " ", " ", " ", " ", " ", "B", " ", " ", "T", " ", " ", " ", " ", " ", " ", " ", "B", " ", " ", " ", " ", "W", "W", "W", "W", "W", "W", "W", "W"},
      {"W", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "T", " ", " ", "W", " ", "Y", "B", "Y", "B", "Y", " ", " ", "B", " ", " ", " ", " ", " ", " ", " ", "Y", " ", " ", " ", " ", "W", "W", "W", "W", "W", "W", "W", "W"},
      {"W", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "W", " ", " ", " ", " ", " ", " ", " ", " ", "Y", " ", " ", " ", " ", " ", " ", " ", "B", " ", " ", " ", " ", "W", "W", "W", "W", "W", "W", "W", "W"},
      {"W", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "W", " ", " ", " ", " ", " ", " ", " ", " ", "B", " ", " ", " ", " ", " ", " ", " ", "Y", " ", " ", " ", " ", "W", "W", "W", "W", "W", "W", "W", "W"},
      {"W", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "W", " ", " ", " ", " ", " ", " ", " ", " ", "Y", " ", " ", " ", " ", " ", " ", " ", "B", " ", " ", " ", " ", "W", "W", "W", "W", "W", "W", "W", "W"},
      {"W", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "T", " ", " ", "W", " ", " ", " ", " ", " ", " ", " ", " ", "T", " ", " ", " ", " ", " ", " ", " ", "T", " ", " ", " ", " ", "W", "W", "W", "W", "W", " ", " ", "T"},
      {"W", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "W", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "W", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "W", "W", "W", "W", " ", " ", " ", "B"},
      {"W", " ", " ", " ", "G", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "W", " ", " ", " ", " ", "G", " ", " ", " ", " ", " ", " ", "W", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "W", "W", "W", " ", " ", " ", " ", "B"},
      {"W", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "W", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "W", " ", " ", " ", " ", " ", " ", " ", "T", " ", " ", " ", " ", "W", "W", " ", " ", " ", " ", " ", "B"},
      {"W", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "W", " ", " ", " ", " ", " ", " ", " ", "B", "B", " ", " ", "W", " ", " ", " ", "G", " ", " ", " ", "Y", " ", " ", " ", " ", "T", "H", " ", " ", " ", " ", " ", "B"},
      {"W", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "W", " ", "W", " ", " ", " ", " ", " ", "B", "B", " ", " ", "W", " ", " ", " ", " ", " ", " ", " ", "B", "R", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "B"},
      {"W", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "W", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "W", "W", "W", "W", "W", "W", " ", "W", "Y", "H", " ", " ", "G", " ", " ", " ", " ", " ", " ", " ", "B"},
      {"W", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "W", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "W", " ", " ", " ", " ", "W", " ", "W", "B", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "B"},
      {"W", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "W", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "W", " ", " ", " ", " ", "W", " ", "W", "Y", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "B"},
      {"W", " ", " ", "B", "Y", " ", " ", " ", " ", " ", "B", "Y", "B", "Y", " ", " ", " ", " ", " ", "B", "Y", "B", "W", " ", " ", " ", " ", "W", "W", " ", " ", " ", " ", " ", "W", " ", " ", " ", " ", "W", " ", "W", "B", " ", " ", " ", " ", "T", "R", " ", " ", " ", " ", " ", "B"},
      {"W", " ", " ", "W", " ", " ", " ", " ", " ", " ", " ", "W", " ", " ", " ", " ", " ", " ", " ", " ", " ", "W", "W", " ", " ", " ", " ", "W", "W", " ", " ", " ", " ", " ", "W", " ", " ", " ", " ", "W", " ", "W", "Y", " ", " ", " ", " ", "W", "W", " ", " ", " ", " ", " ", "B"},
      {"W", " ", " ", "W", " ", " ", " ", " ", " ", " ", " ", "W", " ", " ", " ", " ", " ", " ", " ", " ", " ", "W", "W", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "W", " ", " ", " ", " ", "W", " ", "W", "B", " ", " ", " ", " ", "W", "W", "W", " ", " ", " ", " ", "B"},
      {"W", " ", " ", "W", " ", " ", " ", " ", " ", " ", " ", "T", " ", " ", " ", " ", " ", " ", " ", " ", " ", "W", "W", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "W", " ", " ", " ", " ", "W", " ", "W", "W", " ", " ", " ", " ", "W", "W", "W", "W", " ", " ", " ", "B"},
      {"W", " ", " ", "W", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "W", "W", " ", " ", " ", " ", " ", " ", " ", " ", " ", "R", " ", "W", " ", " ", " ", " ", "W", " ", "W", "W", " ", " ", " ", " ", "W", "W", "W", "W", "W", " ", " ", "T"},
      {"W", " ", " ", "W", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "W", "W", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "W", " ", "C", " ", " ", "W", " ", "W", "W", " ", " ", " ", " ", "W", "W", "W", "W", "W", "W", "W", "W"},
      {"W", " ", " ", "W", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "W", "W", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "W", " ", " ", " ", " ", "W", " ", "W", "W", " ", " ", " ", " ", "W", "W", "W", "W", "W", "W", "W", "W"},
      {"W", " ", " ", "W", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "W", "W", " ", " ", "T", "W", "W", "W", "W", "W", " ", " ", " ", "W", " ", " ", " ", " ", "W", " ", "W", "W", " ", " ", " ", " ", "W", "W", "W", "W", "W", "W", "W", "W"},
      {"W", " ", " ", "W", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "W", "W", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "W", " ", " ", " ", " ", "W", " ", "W", "W", " ", " ", " ", " ", "W", "W", "W", "W", "W", "W", "W", "W"},
      {"W", " ", " ", "W", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "W", "W", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "W", " ", " ", " ", " ", "w", " ", "W", "W", " ", " ", " ", " ", "W", "W", "W", "W", "W", "W", "W", "W"},
      {"W", " ", " ", "W", " ", " ", " ", " ", " ", " ", " ", "T", " ", " ", " ", " ", " ", " ", " ", " ", " ", "W", "W", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "W", " ", " ", " ", " ", "T", " ", "T", "W", " ", " ", " ", " ", "W", "W", "W", "W", "W", "W", "W", "W"},
      {"W", " ", " ", "W", " ", " ", " ", " ", " ", " ", " ", "W", " ", " ", " ", " ", " ", " ", " ", " ", " ", "W", "W", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "W", " ", " ", " ", " ", " ", " ", " ", "W", " ", " ", " ", " ", "W", "W", "W", "W", "W", "W", "W", "W"},
      {"W", "R", "H", "W", "B", "Y", "B", "Y", "B", "Y", "B", "W", "B", "Y", "B", "Y", "B", "Y", "B", "Y", "B", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W"},
   };
   
   public void removeAlienSpawner() {
      counter++;
      if (counter == 1) {
         map[18][46] = " ";
      } 
      else if (counter == 2) {
         map[16][38] = " ";
      }
      else if (counter == 3) {
         map[14][27] = " ";
      }
      else {
         map[14][4] = " ";
      }
   }
   
   public void removeWeaponBox() {
      counter2++;
      if (counter2 == 1) {
         map[26][36] = "B";
      }
      else if (counter2 == 2) {
         map[5][25] = "B";
      }
      else {
         map[0][4] = "B";
      } 
   }
   
   public int getCounter() {
      return counter;
   }
   
   public int getValue(double x, double y) {
      int counter = (int)Math.round(x / 4);
      int counter2 = (int)Math.round(y / 4);
      
      String test = map[counter2][counter];
      if (test.equals(" ")) {
         return 0;
      }
      else if (test.equals("W")) {
         return 1;
      }
      else if (test.equals("R")) {
         return 2;
      }
      else if (test.equals("B")) {
         return 3;
      }
      else if (test.equals("H")) {
         return 4;
      }
      else if (test.equals("G")) {
         return 5;
      }
      else if (test.equals("Y")) {
         return 6;
      }
      else if (test.equals("C")) {
         return 7;
      }
      else if (test.equals("T")) {
         return 8;
      }
      else {
         return 4;
      }
   }
}