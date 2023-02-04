import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class EndGame extends World
{
    public EndGame()
    {    
        super(GameConstants.WORLD_W, GameConstants.WORLD_H, 1);
        setBackground ("endgame_bg.png");
        //Add in the FinalScore class which displays the final score
        addObject (new FinalScore(), GameConstants.WORLD_W/2-20,GameConstants.WORLD_H/2 + 110);
        //Add 200 stars into the world at different locations for flashiness :D
        for (int i = 0; i < 200; i++) {
            addObject (new Star(), Greenfoot.getRandomNumber (GameConstants.WORLD_W), Greenfoot.getRandomNumber (GameConstants.WORLD_H));
        }
    }
    
    public void act() {
        //If the space key is pressed, set the world to the title screen
        if (Greenfoot.isKeyDown("space")) {
            Greenfoot.setWorld (new TitleScreen());
            GameConstants.music.stop();
            Greenfoot.stop();
        }
    }
}