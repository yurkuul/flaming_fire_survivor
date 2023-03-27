import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * An actor used in the TitleScreen world
 */
public class TitleText extends Actor
{
    //An int that holds on to the type of text the actor is
    private int textType;

    /**
     * A constructor that sets the image according to the parameter text
     */
    public TitleText(int text) {
        if (text == 1) {
            setImage ("startText.png");
        } else {
            setImage ("InstructionsText.png");
        }
        textType = text;
    }

    public void act() {
        checkHover();
        checkClicked();
    }    


    private void checkClicked() {
        
    }

    private void checkHover() {

    }
}