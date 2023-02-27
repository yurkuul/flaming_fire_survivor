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
    
    /**
     * A method accessed by the Game world that updates the image based off
     * of the parameter lives
     *
     * 
     */
    public void updateLivesBar(int lives) {
        if (lives == 5) {
            setImage (livesBar [0]);
        } else if (lives == 4) {
            setImage (livesBar [1]);
        } else if (lives == 3) {
            setImage (livesBar [2]);
        } else if (lives == 2) {
            setImage (livesBar [3]);
        } else if (lives == 1) {
            setImage (livesBar [4]);
        } else if (lives == 0) { //If there are no lives left, set the world to EndGame
            Greenfoot.setWorld (new EndGame());
        }
    }
}