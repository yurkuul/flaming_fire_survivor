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


    /**
     * If the actor is clicked, it checks to see if the actor is type 1 (Start
     * button) or type 2 (Instructions button) and sets the world accordingly
     *
     */
    private void checkClicked() {
        if (Greenfoot.mouseClicked(this)) {
            if (textType == 1) {
                Greenfoot.setWorld (new Game());
            } else {
                Greenfoot.setWorld (new Instructions());
            }
        }
    }

    /**
     * A method used to check if the mouse is hovering on the actor and changes
     * the image to a glowed version if it is
     *
     */
    private void checkHover() {
        if (Greenfoot.mouseMoved(this) && (textType == 1)) {
            setImage ("startTextHovered.png");
        } else if (Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this) && (textType == 1)) {
            setImage ("startText.png");
        }
        if (Greenfoot.mouseMoved(this) && (textType == 2)) {
            setImage ("InstructionsTextHovered.png");
        } else if (Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this) && (textType == 2)) {
            setImage ("InstructionsText.png");
        }
    }
}