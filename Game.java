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
    
    /**
     * Method used to get a list of all mobs and adds in new mobs if the size
     * of the list is less than 15
     *
     */
    private void checkAddMob() {
        List <Mob> mobs = getObjects(Mob.class);
        //Add a mob 2% of the time if there is less than 15 mobs in the world
        if ((Greenfoot.getRandomNumber (50) < 1) && (mobs.size() < 15)) {
            //ints used to determine what platform the mobs spawn on
            int y;
            int groundlvl;
            if (Greenfoot.getRandomNumber (2) == 0) {
                y = 90;
                groundlvl = 1;
            } else {
                y = 330;
                groundlvl = 2;
            }
            addObject (new Mob(Greenfoot.getRandomNumber (2), groundlvl), Greenfoot.getRandomNumber (630) + 330, y);
        }
    }
    
    /**
     * Method accessed and used to change the score in the PointsScoreboard 
     * class
     *
     * 
     */
    public void updateScore(int points) {
        score.addScore(points);
    }
    
    private void prepare() {
        
    }
}