import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Stars that are added to the world in all worlds but the Game world for 
 * aestheticness :D
 */
public class Star extends Actor {
    //An int that holds on to the transparency
    private int transparency;
    
    public Star() {
        //Sets the transparency to a random transparency
        transparency = Greenfoot.getRandomNumber (255);
        getImage().setTransparency (transparency);
    }
    
    public void act() {
        changeTransparency();
    }    
    
    /**
     * Lowers the transparency if it is greater than 0 and sets the 
     * transparency of the star
     * If the transparency reaches 0, the transparency resets
     *
     */
    private void changeTransparency() {
        if (transparency > 0) {
            transparency--;
            getImage().setTransparency (transparency);
        } else {
            transparency = 250;
            getImage().setTransparency (transparency);
        }
    }
}