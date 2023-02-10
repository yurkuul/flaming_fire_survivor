import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

public class Game extends World {
    //Used to hold on to the pointsScoreboard class
    private PointsScoreboard score;
    
    /**
     * Constructor for objects of class Game.
     * 
     */
    public Game() {    
        super(GameConstants.WORLD_W, GameConstants.WORLD_H, 1);
        setBackground ("game_bg.png");
        prepare(); 
    }
    
    public void act() {
        checkAddMob();
    }
    
    private void checkAddMob() {
        
    }
    
    public void updateScore(int points) {
        
    }
    
    private void prepare() {
        
    }
}