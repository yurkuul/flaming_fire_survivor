import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A class used in the EndGame world that displays the player's final score
 */
public class FinalScore extends Actor
{
    public FinalScore() {
        GreenfootImage finalScore = new GreenfootImage (100, 100);
        String score = "";
        int numScore = GameConstants.finalScore;
        //changes int numScore to a string so it can be held onto String score
        score = String.valueOf (numScore);
        finalScore.setColor (Color.WHITE);
        finalScore.drawString (score, 50, 50);
        finalScore.scale (500, 275);
        setImage (finalScore);
    }
}