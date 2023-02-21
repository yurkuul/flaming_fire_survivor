import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A Cloud class that is used in the title screen for aesthetics :D
 */
public class Cloud extends Actor
{
    //An int to hold onto the direction the cloud is travelling
    private int direction;
    //An int to hold onto the speed of the cloud
    private int speed;
    
    /**
     * A constructor that takes on a parameter that determines what direction
     * the cloud is travelling
     */
    public Cloud(int setDirection) {
        //Sets the cloud to a random size
        int dimension = Greenfoot.getRandomNumber (275) + 50;
        getImage().scale (dimension, dimension);
        //Sets the transparency of the cloud to a random transparency
        int transparency = Greenfoot.getRandomNumber (100) + 40;
        getImage().setTransparency (transparency);
        direction = setDirection;
        //Sets the speed of the cloud to a random number between 1 and 4 inclusive
        speed = Greenfoot.getRandomNumber (4) + 1;
    }
    
    public void act() {
        travel();
        checkEdge();
    }    
    
    /**
     * A method that moves the cloud depending on the direction it is travelling
     *
     */
    private void travel() {
        if (direction == 0) {
            move (speed);
        } else if (direction == 1) {
            move (0-speed);
        }
    }
    
    /**
     * A method that checks to see if the cloud is at the edge
     * If the cloud is at the edge, it resets the clouds position back to the
     * side it was on when it was first added
     *
     */
    private void checkEdge() {
        if (isAtEdge()) {
            if (direction == 0) {
                setLocation (0, Greenfoot.getRandomNumber (GameConstants.WORLD_H/2));
            } else {
                setLocation (1250, Greenfoot.getRandomNumber (GameConstants.WORLD_H/2));
            }
        }
    }
}