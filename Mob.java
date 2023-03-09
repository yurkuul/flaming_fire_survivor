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
        currImg++;
        checkStates();
        animate();
    }    

    /**
     * Method used for animating the mob
     *
     */
    private void animate() {
        if ((mob_state == GameConstants.MOBSTATE_WALKLEFT) || (mob_state == GameConstants.MOBSTATE_CHASEPLAYERLEFT)) {
            setImage (walkingRightFrames [Math.min(currImg/3 % (walkingRightFrames.length*5), walkingRightFrames.length-1)]);
        } else if ((mob_state == GameConstants.MOBSTATE_WALKRIGHT) || (mob_state == GameConstants.MOBSTATE_CHASEPLAYERRIGHT)) {
            setImage (walkingLeftFrames [Math.min(currImg/3 % (walkingLeftFrames.length*5), walkingLeftFrames.length-1)]);
        } else if (mob_state == GameConstants.MOBSTATE_HIT) {
            setImage ("MobHit.png");
        } else if (mob_state == GameConstants.MOBSTATE_DEATH) {
            setImage (deathFrames [Math.min(currImg/6 % (deathFrames.length*6), deathFrames.length-1)]);
        }
    }

    /**
     * A method used to check the mob states and/or sets them and calls 
     * methods accordingly
     *
     */
    private void checkStates() {
        if (mob_state == GameConstants.MOBSTATE_WALKLEFT || mob_state == GameConstants.MOBSTATE_CHASEPLAYERLEFT) {
            walkLeft();
        } else if (mob_state == GameConstants.MOBSTATE_WALKRIGHT || mob_state == GameConstants.MOBSTATE_CHASEPLAYERRIGHT) {
            walkRight();
        } else if (mob_state == GameConstants.MOBSTATE_HIT) {
            hit();
        }
        if (isTouching (Barrier.class)) {
            useBarrier();
        }
        checkAggro();
        //For making mobs swarm the player
        if (aggro.size() > 0 && Math.random() < 0.05 && !isTouching (Barrier.class)) {
            if (aggro.get(0).getX() > getX()){
                mob_state = GameConstants.MOBSTATE_CHASEPLAYERLEFT;
            } else {
                mob_state = GameConstants.MOBSTATE_CHASEPLAYERRIGHT;
            }
        }
        if (isTouching (Orb.class)) {
            mob_state = GameConstants.MOBSTATE_HIT;
        }
    }

    /**
     * Changes the mob's direction when it runs into a barrier
     *
     */
    private void useBarrier() {
        Barrier b = (Barrier)getOneIntersectingObject (Barrier.class);
        if (GameConstants.MOBSTATE_WALKLEFT != b.leftSide()) {
            mob_state = GameConstants.MOBSTATE_WALKRIGHT;
        } else if (GameConstants.MOBSTATE_WALKRIGHT != b.leftSide()) {
            mob_state = GameConstants.MOBSTATE_WALKLEFT;
        } else if ((GameConstants.MOBSTATE_CHASEPLAYERLEFT - GameConstants.MOBSTATE_WALKLEFT) != b.leftSide()) {
            mob_state = GameConstants.MOBSTATE_WALKRIGHT;
        }else if ((GameConstants.MOBSTATE_CHASEPLAYERRIGHT - GameConstants.MOBSTATE_WALKRIGHT) != b.leftSide()) {
            mob_state = GameConstants.MOBSTATE_WALKLEFT;
        }
    }

    
    /**
     * If the object is in range of the mob, it is added to the aggro list
     *
     */
    private void checkAggro() {
        aggro = getObjectsInRange (100, Player.class);
    }

    /**
     * For the mob walking left
     *
     */
    private void walkLeft() {
        move (GameConstants.MOB_WALKING_SPEED + bonusSpeed);
    }

    /**
     * For the mob walking right
     *
     */
    private void walkRight() {
        move (0 - (GameConstants.MOB_WALKING_SPEED + bonusSpeed));
    }


    /**
     * A method that checks if the aggro list is not empty and if it is, then
     * it follows the player, otherwise, it walks around
     *
     */
    private void followPlayer() {
        if (aggro.size() > 0) {
            move (GameConstants.MOB_WALKING_SPEED);
        } else if (aggro.size() == 0) {
            mob_state = GameConstants.MOBSTATE_WALKLEFT;
        }
        //Setting the location of the mob back to the proper y level if it
        //falls off by accident or somehow gets past a barrier
        if ((getY() != 330) || (getY() != 90)) {
            if (groundLevel == 2) {
                setLocation (getX(), 330);
            } else {
                setLocation (getX(), 90);
            }
        }
    }

    private void hit() {
        
    }

    private void checkDeath() {
        
    }

    private void moveHealthBar() {
        
    }
}