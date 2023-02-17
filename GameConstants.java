import greenfoot.*;
/**
* A separate class used to hold on to constants within the game
* Constants are accessed and used when neccessary
* Character states or mob states are used within the corresponding actors
* for comparisons
*/
public class GameConstants{
    //World constants
    static final int WORLD_W = 1300, WORLD_H = 700;
    static final int WORLD_GRAVITY = 1;
    
    //Player constants
    static final int PLAYER_W = 56, PLAYER_H = 56;
    static final int PLAYER_WALKING_SPEED = 5;
    static final int PLAYER_ANIMATION_SPEED= 10;
    static final int PLAYER_JUMPING_STRENGTH = -15;
    static final int PLAYER_CLIMBING_SPEED = 5;
    static final int PLAYER_FALL_XSPEED = 4;
    static final int PLAYER_ATTACK_SPEED = 3;
    
    //Fire orb constants
    static final int ORB_LIFESPAN = 100;
    
    //Character States
    static final int CHARACTERSTATE_IDLE = 0;
    static final int CHARACTERSTATE_WALKINGLEFT = 1;
    static final int CHARACTERSTATE_WALKINGRIGHT = 2;
    static final int CHARACTERSTATE_JUMP = 3;
    static final int CHARACTERSTATE_JUMPLEFT = 4;
    static final int CHARACTERSTATE_JUMPRIGHT = 5;
    static final int CHARACTERSTATE_FALL = 6;
    static final int CHARACTERSTATE_FALLLEFT = 7;
    static final int CHARACTERSTATE_FALLRIGHT = 8;
    static final int CHARACTERSTATE_CLIMBLADDER_UP = 9;
    static final int CHARACTERSTATE_CLIMBLADDER_DOWN = 10;
    static final int CHARACTERSTATE_LADDER_IDLE = 11;
    static final int CHARACTERSTATE_ATTACK = 12;
    static final int CHARACTERSTATE_HIT = 13;
    
    //Mob constants
    static final int MOB_WALKING_SPEED = 2;
    
    //Mob States
    static final int MOBSTATE_WALKLEFT = 0;
    static final int MOBSTATE_WALKRIGHT = 1;
    static final int MOBSTATE_CHASEPLAYERLEFT = 2;
    static final int MOBSTATE_CHASEPLAYERRIGHT = 3;
    static final int MOBSTATE_HIT = 4;
    static final int MOBSTATE_DEATH = 5;
    
    //Scoreboards
    static final LivesScoreboard LIVES_SCOREBOARD = new LivesScoreboard();
    
    //An int used to hold on to the final score of the player when the game ends
    static int finalScore;
    
    //For background music
    public static GreenfootSound music = new GreenfootSound ("Background Music.mp3");
}