//Stephen Labys
/* On my honor as a student I have neither
given nor recieved any unauthorized aid on this assignment */

/* All graphics/game mechanics are derived from AP Invaders project, and other mechanics, such as key listeners or mouse listeners, adapted from Oracle documentation. 
Raycasting concept and mechanics adapted from https://en.wikipedia.org/wiki/Ray_casting#:~:text=Ray%20casting%20is%20the%20most,scenes%20to%20two%2Ddimensional%20images.*/

/*--------------------INSTRUCTIONS--------------------------
In this game, you are the pilot of a cargo spacecraft which has been
attacked by aliens. You start in the front of the ship, and have to 
progress to the cargo hold, removing any aliens spawners you find
while making sure to get rid of the aliens that attacked your ship. 
You start with a simple weapon, but can find better ones as you go 
through the ship. You can also refill your ammo and health with 
special boxes that you can find in the ship.

Controls:

W - Move Forward
A - Move Left
S - Move Back
D - Move Right

Click - Attack
Mouse Scroll Wheel - Weapon Select
Move Mouse Left - Look Left
Move Mouse Right - Look Right

Interactable Areas:

[Walk in to box to access function]
White Box - Restores Health
Red Box - Adds Ammo
Cyan Box - New Weapon
Green Box - Alien Spawner

*/


import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

public class Main {

   public static final int WIDTH = 1440;
   public static final int HEIGHT = 900;
    
   public static void main (String[] args) throws Exception {
        JFrame frame = new JFrame("Game");
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocation(100, 50);
        GameRun field = new GameRun();
        frame.addKeyListener(field);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(field);
        frame.setResizable(false);
        frame.setVisible(true);               
   }
}
