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
    
    /**
     * Used to add in all actors needed for the world
     * 
     */
    private void prepare() {
        //Ground layer
        for (int i = 0; i < 27; i++) {
            addObject (new Ground(), i*50, 676);
        }

        //Bottom platform
        for (int i = 0; i < 17; i++) {
            addObject (new Ground(), i*50+250, 400);
        }

        //Right platform
        for (int i = 0; i < 4; i++) {
            addObject (new Ground(), i*50+1125, 315);
        }

        //Left platform
        for (int i = 0; i < 5; i++) {
            addObject (new Ground(), i*50-30, 315);
        }

        //Top platform
        for (int i = 0; i < 14; i++) {
            addObject (new Ground(), i*50+320, 150);
        }
        
        //Left single platform
        Ground ground52 = new Ground();
        addObject(ground52,240,215);
        
        //Right single platform
        Ground ground68 = new Ground();
        addObject(ground68,1067,215);

        //Bottom left ladders
        Ladder ladder = new Ladder();
        addObject(ladder,392,406);
        Ladder ladder2 = new Ladder();
        addObject(ladder2,392,456);
        Ladder ladder3 = new Ladder();
        addObject(ladder3,392,506);
        Ladder ladder4 = new Ladder();
        addObject(ladder4,392,556);
        Ladder ladder5 = new Ladder();
        addObject(ladder5,392,580);
        Ladder ladder11 = new Ladder();
        addObject(ladder11,392,399);

        //Bottom right ladders
        Ladder ladder6 = new Ladder();
        addObject(ladder6,928,406);
        Ladder ladder7 = new Ladder();
        addObject(ladder7,928,456);
        Ladder ladder8 = new Ladder();
        addObject(ladder8,928,506);
        Ladder ladder9 = new Ladder();
        addObject(ladder9,928,556);
        Ladder ladder10 = new Ladder();
        addObject(ladder10,928,580);
        Ladder ladder12 = new Ladder();
        addObject(ladder12,928,394);
        
        //Adding in the player
        addObject (new Player(), 49, 623);
        
        //Barriers for mobs
        Barrier barrier = new Barrier(1);
        addObject(barrier,1075,300);
        Barrier barrier2 = new Barrier(1);
        addObject(barrier2,1000,50);
        Barrier barrier3 = new Barrier(0);
        addObject(barrier3,300,50);
        Barrier barrier4 = new Barrier(0);
        addObject(barrier4,230,300);
        
        //Scoreboard for points
        score = new PointsScoreboard();
        addObject (score, 95, 37);
        
        //Scoreboard for lives
        addObject (GameConstants.LIVES_SCOREBOARD, 1140, 660);
    }
}