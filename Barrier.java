import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A wall that is used to change the direction of the mobs if it runs into it
 */
public class Barrier extends Actor
{
    //A boolean used to set the transparency of the class for debugging purposes
    private boolean debugging;
    //An int to hold on to the side the barrier is on
    private int side;
    
    /**
     * A constructor that takes on a parameter that determines the side the
     * barrier is on
     */
    public Barrier(int direction) {
        //Debugging should be set to false so it is unable to be seen when
        //playing the game
        //Set debugging to true if needed for debugging
        debugging = false;
        if (debugging == false) {
            getImage().setTransparency (0);
        } else {
            getImage().setTransparency (255);
        }
        side = direction;
    }
    
    /**
     * An accessor that returns the side that the barrier is on
     */
    public int leftSide() {
        return side;
    }
}