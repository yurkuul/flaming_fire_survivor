import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The character of the game controlled by the player
 * 
 */
public class Player extends Actor {
    //Array lists to store all frames of actions done by the player
    GreenfootImage[] walkingLeftFrames = new GreenfootImage[4];
    GreenfootImage[] walkingRightFrames = new GreenfootImage[4];
    GreenfootImage[] climbingFrames = new GreenfootImage [3];
    GreenfootImage[] idle = new GreenfootImage[1];
    GreenfootImage[] jump = new GreenfootImage[1];
    GreenfootImage[] attack = new GreenfootImage [10];
    //Counter to change frames of player
    private int currImg;
    //Speed in the y direction for gravity/jumping
    private int ySpeed;
    //An int to hold on to the current state the character is in
    //Used to compare to the gameConstant character states and to call
    //methods according to the state it's in
    private int character_state;
    //Holds on to the lives of the player
    private int lives;
    //An int to delay frames
    private int animationDelay;

    /**
     * Player default constructor:
     *
     * Sets the images of the GreenfootImage array lists and scales
     * them to the right sizes
     */
    public Player() {
        character_state = GameConstants.CHARACTERSTATE_IDLE;
        lives = 5;
        GameConstants.LIVES_SCOREBOARD.updateLivesBar (lives);
        ySpeed = 0;
        idle [0] = new GreenfootImage ("PlayerIdle.png");
        idle[0].scale (GameConstants.PLAYER_W, GameConstants.PLAYER_H);
        setImage (idle [0]);
        jump [0] = new GreenfootImage ("PlayerJump.png");
        jump[0].scale (GameConstants.PLAYER_W, GameConstants.PLAYER_H);
        attack [0] = new GreenfootImage ("PlayerAttack.png");
        attack[0].scale (GameConstants.PLAYER_W, GameConstants.PLAYER_H);
        for (int i = 0; i < attack.length-1; i++) {
            attack [i] = attack [0];
        }
        for (int i = 0; i < walkingLeftFrames.length-1; i++) {
            walkingLeftFrames[i] = new GreenfootImage ("PlayerWalk" + i + ".png");
            walkingLeftFrames[i].scale (GameConstants.PLAYER_W, GameConstants.PLAYER_H);
        }
        walkingLeftFrames [3] = walkingLeftFrames [1];
        for (int i = 0; i < walkingRightFrames.length; i++) {
            walkingRightFrames [i] = new GreenfootImage (walkingLeftFrames[i]);
            walkingRightFrames [i].mirrorHorizontally();
        }
        for (int i = 0; i < climbingFrames.length; i++) {
            climbingFrames [i] = new GreenfootImage ("PlayerClimb" + i + ".png");
            climbingFrames [i].scale (GameConstants.PLAYER_W, GameConstants.PLAYER_H);
        }
    }

}