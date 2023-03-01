import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * The mob that the player has to avoid and kill
 */
public class Mob extends Actor
{
    //Array lists to hold onto all the frames of the animations
    GreenfootImage[] walkingRightFrames = new GreenfootImage [4];
    GreenfootImage[] walkingLeftFrames = new GreenfootImage [4];
    GreenfootImage[] deathFrames = new GreenfootImage [3];
    //Counter for setting the image of the mob
    private int currImg;
    //Int to carry onto the state of the mob that is used to compare with the
    //GameConstants mob states
    private int mob_state;
    //An int to carry a number that determines what platform the mob is on
    private int groundLevel;
    //An int added to the speed
    private int bonusSpeed;
    //An int to hold onto the health of the mob
    private int health;
    //A boolean to check if a health bar has been added to the mob
    private boolean healthBarAdded;
    //An int used to delay the frames of the mob
    private int animationDelay;
    //An int to hold on to the transparency of the mob
    private int fade;
    //Used later on to access the MobHealth actor
    private MobHealth mh;
    //A list holding on to type Player
    List <Player> aggro;

    /**
     * A constructor that uses parameters that detmine the direction the mob 
     * goes and the platform it is on
     */
    public Mob(int direction, int ground) {
        health = 4;
        fade = 255;
        //for loops to add in the images to the arrays
        for (int i = 0; i < walkingRightFrames.length; i++) {
            walkingRightFrames[i] = new GreenfootImage ("MobWalk" + i + ".png");
            walkingRightFrames[i].mirrorHorizontally();
        }
        for (int i = 0; i < walkingLeftFrames.length; i++) {
            walkingLeftFrames[i] = new GreenfootImage ("MobWalk" + i + ".png");
        }
        for (int i = 0; i < deathFrames.length; i++) {
            deathFrames[i] = new GreenfootImage ("mobDeath" + i + ".png");
        }
        //Sets the mob states depending on the direction the mob was facing
        if (direction == 0) {
            mob_state = GameConstants.MOBSTATE_WALKLEFT;
            setImage (walkingLeftFrames [3]);
        } else {
            mob_state = GameConstants.MOBSTATE_WALKRIGHT;
            setImage (walkingRightFrames [3]);
        }
        groundLevel = ground;
        bonusSpeed = Greenfoot.getRandomNumber (3);
        healthBarAdded = false;
        mh = new MobHealth();
    }

    public void act() {

    }    

    private void animate() {
        
    }

    private void checkStates() {
        
    }

    private void useBarrier() {
        
    }

    
    private void checkAggro() {
        
    }

    
    private void walkLeft() {
        
    }

    private void walkRight() {
        
    }

    private void followPlayer() {
        
    }

    private void hit() {
        
    }

    private void checkDeath() {
        
    }

    private void moveHealthBar() {
        
    }
}