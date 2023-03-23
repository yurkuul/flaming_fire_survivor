import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A Scoreboard for the points gained by the player
 */
public class PointsScoreboard extends Actor {
    //An int to hold onto the points
    private int points;
    //A custom purple colour made for the scoreboard
    Color customPurple = new Color (71, 56, 113);
    //A custom colour made to change the colour of the scoreboard
    Color custom = new Color (Greenfoot.getRandomNumber (255), Greenfoot.getRandomNumber (255), Greenfoot.getRandomNumber (255));;
    
    /**
     * Default constructor to create the image of the points scoreboard
     *
     */
    public PointsScoreboard() {
        points = 0;
        GreenfootImage pointsBoard = new GreenfootImage (150, 30);
        pointsBoard.setColor (customPurple);
        pointsBoard.fill();
        pointsBoard.setColor (Color.WHITE);
        pointsBoard.drawString("Score: " + points, 10, 20);
        setImage(pointsBoard);
    }
    
    public void addScore(int amount) {
        
    }
}