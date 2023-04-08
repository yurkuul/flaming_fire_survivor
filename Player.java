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

    /**
     * currImg is a counter used for animating the character movements
     * 
     * checkInputs() is the method used to check if the character state
     * is equal to any of the GameConstants character states
     * 
     * animateCharacter() is the method used to change the image of the 
     * character depending on the character state
     * 
     */
    public void act() {
        currImg++;
        checkInputs();
        animateCharacter();
        checkHit();
    } 

    /**
     * A method used to compare the current character state to the 
     * GameConstants character states and then calls the proper method 
     * according to the character state
     *
     */
    private void checkInputs() {
        if (character_state == GameConstants.CHARACTERSTATE_IDLE) {
            checkIdleInput();
        } else if (character_state == GameConstants.CHARACTERSTATE_WALKINGLEFT) {
            checkLeftWalkInput();
        } else if (character_state == GameConstants.CHARACTERSTATE_WALKINGRIGHT) {
            checkRightWalkInput();
        } else if (character_state == GameConstants.CHARACTERSTATE_JUMP) {
            jump();
        } else if (character_state == GameConstants.CHARACTERSTATE_JUMPLEFT) {
            jumpLeft();
        } else if (character_state == GameConstants.CHARACTERSTATE_JUMPRIGHT) {
            jumpRight();
        } else if (character_state == GameConstants.CHARACTERSTATE_FALL) {
            checkFallInput();
        } else if (character_state == GameConstants.CHARACTERSTATE_FALLLEFT) {
            checkFallLeftInput();
        } else if (character_state == GameConstants.CHARACTERSTATE_FALLRIGHT) {
            checkFallRightInput();
        } else if ((character_state == GameConstants.CHARACTERSTATE_CLIMBLADDER_UP) || (character_state == GameConstants.CHARACTERSTATE_CLIMBLADDER_DOWN)) {
            checkLadderClimbInput();
        } else if (character_state == GameConstants.CHARACTERSTATE_LADDER_IDLE) {
            checkLadderIdleInput();
        } else if (character_state == GameConstants.CHARACTERSTATE_ATTACK) {
            attack();
        } else if (character_state == GameConstants.CHARACTERSTATE_HIT) {
            takeDamage();
        }
    }

    /**
     * A method used to compare the current character state to the 
     * GameConstants character states and then sets the image of the 
     * character according to the character state
     *
     */
    private void animateCharacter() {
        if (character_state == GameConstants.CHARACTERSTATE_IDLE) {
            setImage (idle [0]);
        } else if (character_state == GameConstants.CHARACTERSTATE_WALKINGLEFT) {
            setImage (walkingLeftFrames[currImg/GameConstants.PLAYER_ANIMATION_SPEED % walkingLeftFrames.length]);
        } else if (character_state == GameConstants.CHARACTERSTATE_WALKINGRIGHT) {
            setImage (walkingRightFrames[currImg/GameConstants.PLAYER_ANIMATION_SPEED % walkingRightFrames.length]);
        } else if ((character_state == GameConstants.CHARACTERSTATE_JUMP) || (character_state == GameConstants.CHARACTERSTATE_FALL)) {
            setImage (jump [0]);
        } else if ((character_state == GameConstants.CHARACTERSTATE_JUMPLEFT) || (character_state == GameConstants.CHARACTERSTATE_FALLLEFT)) {
            setImage (walkingLeftFrames[0]);
        } else if ((character_state == GameConstants.CHARACTERSTATE_JUMPRIGHT) || (character_state == GameConstants.CHARACTERSTATE_FALLRIGHT)) {
            setImage (walkingRightFrames[0]);
        } else if ((character_state == GameConstants.CHARACTERSTATE_CLIMBLADDER_UP) || (character_state == GameConstants.CHARACTERSTATE_CLIMBLADDER_DOWN)) {
            setImage (climbingFrames [currImg/GameConstants.PLAYER_CLIMBING_SPEED % climbingFrames.length]);
        } else if (character_state == GameConstants.CHARACTERSTATE_ATTACK) {
            setImage (attack [0]);
        }
    }
}