import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * A health bar made for the mobs
 */
public class MobHealth extends Actor {
    //An array to hold on to the health bar images
    GreenfootImage[] healthBar = new GreenfootImage [3];
    
    public MobHealth() {
        for (int i = 0; i < healthBar.length; i++) {
            healthBar [i] = new GreenfootImage ("mobHealth" + i + ".png");
        }
        setImage (healthBar [0]);
    }
    
    /**
     * A public method accessed by the mob class that updates their health
     * according to the parameter health
     */
    public void updateHealthBar(int health) {
        if (health == 2) {
            setImage (healthBar [1]);
        } else if (health == 1) {
            setImage (healthBar [2]);
        }
    }
}