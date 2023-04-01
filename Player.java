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
    
}