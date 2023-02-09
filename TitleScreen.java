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
        addCloud();
        scenarioStarted = true;
        if (scenarioStarted == true) {
            startMusic();
        }
    }

    /**
     * Adds in clouds to the world 0.5% of the time and if there are less than
     * 6 clouds in the world
     *
     */
    private void addCloud() {
        List <Cloud> c = getObjects (Cloud.class);
        if ((Greenfoot.getRandomNumber (200) < 1) && (c.size() < 6)) {
            //an int to determine the direction the cloud travels and
            //where it is added in
            int cloudDirection = Greenfoot.getRandomNumber (2);
            if (cloudDirection == 0) {
                addObject (new Cloud (cloudDirection), 0, Greenfoot.getRandomNumber (GameConstants.WORLD_H/2));
            } else if (cloudDirection == 1) {
                addObject (new Cloud (cloudDirection), 1250, Greenfoot.getRandomNumber (GameConstants.WORLD_H/2));
            }
        }
    }

    public void startMusic() {
        GameConstants.music.playLoop();
    }

    public void pauseMusic() {
        GameConstants.music.pause();
    }
}