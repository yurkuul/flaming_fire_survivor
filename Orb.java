import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Orb class which is added into the world and used as an attack move by
 * the player
 */
public class Orb extends Actor
{
    //An array to hold onto the frames of the orb
    GreenfootImage[] orbFrames = new GreenfootImage [16];
    //Used as a counter for setting the image
    private int currImg;
    //An int used to determine how long the fire orb stays in the world for
    private int lifeSpan;
    
    public Orb() {
        //Adds in the images of the orbs to the array list of images
        for (int i = 0; i < orbFrames.length; i++) {
            orbFrames[i] = new GreenfootImage ("fireAttack (" + i + ").png");
            orbFrames[i].scale (40, 40);
            orbFrames[i].rotate (270);
        }
        //Sets the lifespan of the orb to a constant
        lifeSpan = GameConstants.ORB_LIFESPAN;
    }
    
    public void act() {
        currImg++;
        animateMove();
    }    

    /**
     * Animates the fire orb
     *
     */
    private void animateMove() {
        move (GameConstants.PLAYER_ATTACK_SPEED);
        //Modulus helps slow down the orb frames
        setImage (orbFrames [currImg/GameConstants.PLAYER_ATTACK_SPEED % orbFrames.length]);
    }

    private void checkGround() {
        
    }

    private void checkLifeSpan() {
        
    }
}