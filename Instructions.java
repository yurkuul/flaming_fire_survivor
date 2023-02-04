import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Instructions extends World {
    public Instructions()
    {    
        super(GameConstants.WORLD_W, GameConstants.WORLD_H, 1);
        setBackground ("instructions_bg.png");
        //Add in 200 stars at random places for flashiness :D
        for (int i = 0; i < 200; i++) {
            addObject (new Star(), Greenfoot.getRandomNumber (GameConstants.WORLD_W), Greenfoot.getRandomNumber (GameConstants.WORLD_H));
        }
    }
    
    public void act() {
        checkKeys();
    }
    
    /**
     * Checks to see if the space key or enter key is pressed and sets the
     * world accordingly
     *
     */
    private void checkKeys() {
        if (Greenfoot.isKeyDown ("space")) {
            Greenfoot.setWorld (new TitleScreen());
        } else if (Greenfoot.isKeyDown ("enter")) {
            Greenfoot.setWorld (new Game());
        }
    }
}