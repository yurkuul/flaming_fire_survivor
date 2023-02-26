import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Scoreboard for the lives of the player
 */
public class LivesScoreboard extends Actor {
    //An array that holds on to all the images of the lives bar
    GreenfootImage[] livesBar = new GreenfootImage [5];
    
    public LivesScoreboard() {
        //Adds in all images of the lives bar to the array
        for (int i = 0; i < livesBar.length; i++) {
            livesBar [i] = new GreenfootImage ("playerHealth" + i + ".png");
            livesBar[i].mirrorHorizontally();
        }
        setImage (livesBar [0]);
    }
    
    public void updateLivesBar(int lives) {
        
    }
}