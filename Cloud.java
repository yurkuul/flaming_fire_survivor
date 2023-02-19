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
    
    private void travel() {
    
    }
    
    private void checkEdge() {
        
    }
}