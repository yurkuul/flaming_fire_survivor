import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

public class TitleScreen extends World
{
    private boolean scenarioStarted;

    public TitleScreen() {    
        super(GameConstants.WORLD_W, GameConstants.WORLD_H, 1);
        scenarioStarted = false;
        setBackground ("title_bg.png");
        prepare();
    }

    private void prepare() {
        //Adds in 50 stars for flashiness :D
        for (int i = 0; i < 50; i++) {
            addObject (new Star(), Greenfoot.getRandomNumber (GameConstants.WORLD_W), Greenfoot.getRandomNumber (300));
        }
        //Text Titles that take on parameters to determine the button type
        //Start Button
        addObject (new TitleText(1), 1105, 374);
        //Instructions Button
        addObject (new TitleText(2), 1000, 450);
        //Adds in 2 clouds at the beginning
        Cloud cloud = new Cloud(1);
        addObject(cloud,1293,98);
        Cloud cloud2 = new Cloud(0);
        addObject(cloud2,2,279);
    }

    public void act() {
        
    }

    private void addCloud() {
        
    }

    public void startMusic() {
        GameConstants.music.playLoop();
    }

    public void pauseMusic() {
        GameConstants.music.pause();
    }
}