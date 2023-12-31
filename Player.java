import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The character of the game controlled by the player
 * 
 */
public class Player extends Actor {
    //Array lists to store all frames of actions done by the player
    GreenfootImage[] walkingLeftFrames = new GreenfootImage[4];
    GreenfootImage[] walkingRightFrames = new GreenfootImage[4];
    GreenfootImage[] climbingFrames = new GreenfootImage [3];
    GreenfootImage[] idle = new GreenfootImage[1];
    GreenfootImage[] jump = new GreenfootImage[1];
    GreenfootImage[] attack = new GreenfootImage [10];
    //Counter to change frames of player
    private int currImg;
    //Speed in the y direction for gravity/jumping
    private int ySpeed;
    //An int to hold on to the current state the character is in
    //Used to compare to the gameConstant character states and to call
    //methods according to the state it's in
    private int character_state;
    //Holds on to the lives of the player
    private int lives;
    //An int to delay frames
    private int animationDelay;

    /**
     * Player default constructor:
     *
     * Sets the images of the GreenfootImage array lists and scales
     * them to the right sizes
     */
    public Player() {
        character_state = GameConstants.CHARACTERSTATE_IDLE;
        lives = 5;
        GameConstants.LIVES_SCOREBOARD.updateLivesBar (lives);
        ySpeed = 0;
        idle [0] = new GreenfootImage ("PlayerIdle.png");
        idle[0].scale (GameConstants.PLAYER_W, GameConstants.PLAYER_H);
        setImage (idle [0]);
        jump [0] = new GreenfootImage ("PlayerJump.png");
        jump[0].scale (GameConstants.PLAYER_W, GameConstants.PLAYER_H);
        attack [0] = new GreenfootImage ("PlayerAttack.png");
        attack[0].scale (GameConstants.PLAYER_W, GameConstants.PLAYER_H);
        for (int i = 0; i < attack.length-1; i++) {
            attack [i] = attack [0];
        }
        for (int i = 0; i < walkingLeftFrames.length-1; i++) {
            walkingLeftFrames[i] = new GreenfootImage ("PlayerWalk" + i + ".png");
            walkingLeftFrames[i].scale (GameConstants.PLAYER_W, GameConstants.PLAYER_H);
        }
        walkingLeftFrames [3] = walkingLeftFrames [1];
        for (int i = 0; i < walkingRightFrames.length; i++) {
            walkingRightFrames [i] = new GreenfootImage (walkingLeftFrames[i]);
            walkingRightFrames [i].mirrorHorizontally();
        }
        for (int i = 0; i < climbingFrames.length; i++) {
            climbingFrames [i] = new GreenfootImage ("PlayerClimb" + i + ".png");
            climbingFrames [i].scale (GameConstants.PLAYER_W, GameConstants.PLAYER_H);
        }
    }

    /**
     * currImg is a counter used for animating the character movements
     * 
     * checkInputs() is the method used to check if the character state
     * is equal to any of the GameConstants character states
     * 
     * animateCharacter() is the method used to change the image of the 
     * character depending on the character state
     * 
     */
    public void act() {
        currImg++;
        checkInputs();
        animateCharacter();
        checkHit();
    } 

    /**
     * A method used to compare the current character state to the 
     * GameConstants character states and then calls the proper method 
     * according to the character state
     *
     */
    private void checkInputs() {
        if (character_state == GameConstants.CHARACTERSTATE_IDLE) {
            checkIdleInput();
        } else if (character_state == GameConstants.CHARACTERSTATE_WALKINGLEFT) {
            checkLeftWalkInput();
        } else if (character_state == GameConstants.CHARACTERSTATE_WALKINGRIGHT) {
            checkRightWalkInput();
        } else if (character_state == GameConstants.CHARACTERSTATE_JUMP) {
            jump();
        } else if (character_state == GameConstants.CHARACTERSTATE_JUMPLEFT) {
            jumpLeft();
        } else if (character_state == GameConstants.CHARACTERSTATE_JUMPRIGHT) {
            jumpRight();
        } else if (character_state == GameConstants.CHARACTERSTATE_FALL) {
            checkFallInput();
        } else if (character_state == GameConstants.CHARACTERSTATE_FALLLEFT) {
            checkFallLeftInput();
        } else if (character_state == GameConstants.CHARACTERSTATE_FALLRIGHT) {
            checkFallRightInput();
        } else if ((character_state == GameConstants.CHARACTERSTATE_CLIMBLADDER_UP) || (character_state == GameConstants.CHARACTERSTATE_CLIMBLADDER_DOWN)) {
            checkLadderClimbInput();
        } else if (character_state == GameConstants.CHARACTERSTATE_LADDER_IDLE) {
            checkLadderIdleInput();
        } else if (character_state == GameConstants.CHARACTERSTATE_ATTACK) {
            attack();
        } else if (character_state == GameConstants.CHARACTERSTATE_HIT) {
            takeDamage();
        }
    }

    /**
     * A method used to compare the current character state to the 
     * GameConstants character states and then sets the image of the 
     * character according to the character state
     *
     */
    private void animateCharacter() {
        if (character_state == GameConstants.CHARACTERSTATE_IDLE) {
            setImage (idle [0]);
        } else if (character_state == GameConstants.CHARACTERSTATE_WALKINGLEFT) {
            setImage (walkingLeftFrames[currImg/GameConstants.PLAYER_ANIMATION_SPEED % walkingLeftFrames.length]);
        } else if (character_state == GameConstants.CHARACTERSTATE_WALKINGRIGHT) {
            setImage (walkingRightFrames[currImg/GameConstants.PLAYER_ANIMATION_SPEED % walkingRightFrames.length]);
        } else if ((character_state == GameConstants.CHARACTERSTATE_JUMP) || (character_state == GameConstants.CHARACTERSTATE_FALL)) {
            setImage (jump [0]);
        } else if ((character_state == GameConstants.CHARACTERSTATE_JUMPLEFT) || (character_state == GameConstants.CHARACTERSTATE_FALLLEFT)) {
            setImage (walkingLeftFrames[0]);
        } else if ((character_state == GameConstants.CHARACTERSTATE_JUMPRIGHT) || (character_state == GameConstants.CHARACTERSTATE_FALLRIGHT)) {
            setImage (walkingRightFrames[0]);
        } else if ((character_state == GameConstants.CHARACTERSTATE_CLIMBLADDER_UP) || (character_state == GameConstants.CHARACTERSTATE_CLIMBLADDER_DOWN)) {
            setImage (climbingFrames [currImg/GameConstants.PLAYER_CLIMBING_SPEED % climbingFrames.length]);
        } else if (character_state == GameConstants.CHARACTERSTATE_ATTACK) {
            setImage (attack [0]);
        }
    }

    /**
     * The idle state is the main state for the player, hence why the
     * character state is changed when something is done to bring it out of
     * its idle state
     * 
     * This method is used to check for any user input and sets the character
     * state according to the key/mouse pressed
     *
     */
    private void checkIdleInput() {
        if (Greenfoot.isKeyDown ("A")) {
            character_state = GameConstants.CHARACTERSTATE_WALKINGLEFT;
        } else if (Greenfoot.isKeyDown ("D")) {
            character_state = GameConstants.CHARACTERSTATE_WALKINGRIGHT;
        } else if (Greenfoot.isKeyDown ("space") && onGround()) {
            character_state = GameConstants.CHARACTERSTATE_JUMP;
        } else if (!onGround()) {
            character_state = GameConstants.CHARACTERSTATE_FALL;
        } else if (Greenfoot.isKeyDown ("W") && isTouching (Ladder.class)) {
            character_state = GameConstants.CHARACTERSTATE_CLIMBLADDER_UP;
        }  else if (Greenfoot.isKeyDown ("S") && isTouching (Ladder.class)) {
            character_state = GameConstants.CHARACTERSTATE_CLIMBLADDER_DOWN;
        } else if (Greenfoot.mouseClicked (null)) {
            character_state = GameConstants.CHARACTERSTATE_ATTACK;
        }
    }

    /**
     * A method to check input by the user when on the ladder
     * If input is detected, the character state changes
     *
     */
    private void checkLadderIdleInput() {
        if (Greenfoot.isKeyDown ("W") && isTouching (Ladder.class)) {
            character_state = GameConstants.CHARACTERSTATE_CLIMBLADDER_UP;
        } else if (Greenfoot.isKeyDown ("S") && isTouching (Ladder.class)) {
            character_state = GameConstants.CHARACTERSTATE_CLIMBLADDER_DOWN;
        } else if (Greenfoot.isKeyDown ("space")) {
            character_state = GameConstants.CHARACTERSTATE_JUMP;
        } else if (!isTouching (Ladder.class)) {
            character_state = GameConstants.CHARACTERSTATE_IDLE;
        }
    }

    /**
     * A method to check input by the user when walking left
     * If input is detected, the character state changes
     *
     */
    private void checkLeftWalkInput() {
        walkLeft();
        if (!Greenfoot.isKeyDown ("A")) {
            character_state = GameConstants.CHARACTERSTATE_IDLE;
        } else if (Greenfoot.isKeyDown ("space")) {
            character_state = GameConstants.CHARACTERSTATE_JUMPLEFT;
        } else if (Greenfoot.isKeyDown ("W") && isTouching (Ladder.class)) {
            character_state = GameConstants.CHARACTERSTATE_CLIMBLADDER_UP;
        } else if (Greenfoot.isKeyDown ("S") && isTouching (Ladder.class)) {
            character_state = GameConstants.CHARACTERSTATE_CLIMBLADDER_DOWN;
        } else if (Greenfoot.mouseClicked (null)) {
            character_state = GameConstants.CHARACTERSTATE_ATTACK;
        }
        if (!onGround()) {
            character_state = GameConstants.CHARACTERSTATE_FALLLEFT;
        }
    }

    /**
     * A method to check input by the user when walking right
     * If input is detected, the character state changes
     *
     */
    private void checkRightWalkInput() {
        walkRight();
        if (!Greenfoot.isKeyDown ("D")) {
            character_state = GameConstants.CHARACTERSTATE_IDLE;
        } else if (Greenfoot.isKeyDown ("space")) {
            character_state = GameConstants.CHARACTERSTATE_JUMPRIGHT;
        } else if (Greenfoot.isKeyDown ("W") && isTouching (Ladder.class)) {
            character_state = GameConstants.CHARACTERSTATE_CLIMBLADDER_UP;
        } else if (Greenfoot.isKeyDown ("S") && isTouching (Ladder.class)) {
            character_state = GameConstants.CHARACTERSTATE_CLIMBLADDER_DOWN;
        } else if (Greenfoot.mouseClicked (null)) {
            character_state = GameConstants.CHARACTERSTATE_ATTACK;
        }
        if (!onGround()) {
            character_state = GameConstants.CHARACTERSTATE_FALLRIGHT;
        }
    }

    /**
     * A method to check input by the user when falling
     * If input is detected, the character state changes
     *
     */
    private void checkFallInput() {
        fall();
        if (onGround()) {
            character_state = GameConstants.CHARACTERSTATE_IDLE;
        }
        if (Greenfoot.mouseClicked (null)) {
            character_state = GameConstants.CHARACTERSTATE_ATTACK;
        }
    }

    /**
     * A method to check input by the user when falling left
     * If input is detected, the character state changes
     *
     */
    private void checkFallLeftInput() {
        fallLeft();
        if (onGround()) {
            character_state = GameConstants.CHARACTERSTATE_WALKINGLEFT;
        }
        if (Greenfoot.isKeyDown ("S") && isTouching (Ladder.class)) {
            character_state = GameConstants.CHARACTERSTATE_CLIMBLADDER_DOWN;
        } else if (Greenfoot.mouseClicked (null)) {
            character_state = GameConstants.CHARACTERSTATE_ATTACK;
        }
    }

    /**
     * A method to check input by the user when falling right
     * If input is detected, the character state changes
     *
     */
    private void checkFallRightInput() {
        fallRight();
        if (onGround()) {
            character_state = GameConstants.CHARACTERSTATE_WALKINGRIGHT;
        }
        if (Greenfoot.isKeyDown ("S") && isTouching (Ladder.class)) {
            character_state = GameConstants.CHARACTERSTATE_CLIMBLADDER_DOWN;
        } else if (Greenfoot.mouseClicked (null)) {
            character_state = GameConstants.CHARACTERSTATE_ATTACK;
        }
    }

    /**
     * A method to check the character state for climbing ladders specifically
     * and calls methods accordingly
     * If input is detected, the character state changes
     *
     */
    private void checkLadderClimbInput() {
        if (character_state == GameConstants.CHARACTERSTATE_CLIMBLADDER_UP) {
            climbLadderUp();
        } else if (character_state == GameConstants.CHARACTERSTATE_CLIMBLADDER_DOWN) {
            climbLadderDown();
        }
        if (!Greenfoot.isKeyDown("W")) {
            character_state = GameConstants.CHARACTERSTATE_LADDER_IDLE;
        }
    }

    /**
     * For walking left
     *
     */
    private void walkLeft() {
        move (-GameConstants.PLAYER_WALKING_SPEED);
    }

    /**
     * For walking right
     *
     */
    private void walkRight() {
        move (GameConstants.PLAYER_WALKING_SPEED);
    }

    /**
     * Method for jumping
     *
     */
    private void jump() {
        ySpeed = -15;
        character_state = GameConstants.CHARACTERSTATE_FALL;
    }

    /**
     * For jumping left
     *
     */
    private void jumpLeft() {
        ySpeed = GameConstants.PLAYER_JUMPING_STRENGTH;
        character_state = GameConstants.CHARACTERSTATE_FALLLEFT;
    }

    /**
     * For jumping right
     *
     */
    private void jumpRight() {
        ySpeed = GameConstants.PLAYER_JUMPING_STRENGTH;
        character_state = GameConstants.CHARACTERSTATE_FALLRIGHT;
    }

    /**
     * For regular falling
     *
     */
    private void fall() {
        if (ySpeed <= 7 ) {
            ySpeed = ySpeed + GameConstants.WORLD_GRAVITY;
        }
        setLocation(getX(), getY() + ySpeed);
    }

     /**
     * For falling left (occurs when "A" was pressed when jumping)
     *
     */
    private void fallLeft() {
        if (ySpeed <= 5 ) {
            ySpeed = ySpeed + GameConstants.WORLD_GRAVITY;
        }
        setLocation(getX() - GameConstants.PLAYER_FALL_XSPEED, getY() + ySpeed);
    }

    /**
     * For falling right (occurs when "D" was pressed when jumping)
     *
     */
    private void fallRight() {
        if (ySpeed <= 5 ) {
            ySpeed = ySpeed + GameConstants.WORLD_GRAVITY;
        }
        setLocation(getX() + GameConstants.PLAYER_FALL_XSPEED, getY() + ySpeed);
    }

    /**
     * For climbing the ladder up
     * getOneIntersectingObject is used to set the X value of the player
     * to the X value of the ladder (so that the player stays in the middle)
     *
     */
    private void climbLadderUp() {
        Ladder ladder = (Ladder)getOneIntersectingObject (Ladder.class);
        if (ladder != null) {
            setLocation (ladder.getX(), getY() - GameConstants.PLAYER_CLIMBING_SPEED);
        }
    }

    /**
     * For going down the ladder
     * getOneIntersectingObject is used to set the X value of the player
     * to the X value of the ladder (so that the player stays in the middle)
     *
     */
    private void climbLadderDown() {
        Ladder ladder = (Ladder)getOneIntersectingObject (Ladder.class);
        if (ladder != null) {
            setLocation (ladder.getX(), getY() + GameConstants.PLAYER_CLIMBING_SPEED);
        }
    }

    /**
     * A method that returns a boolean for if the player is on the ground
     */
    private boolean onGround() {
        Actor under = getOneObjectAtOffset (0, getImage().getHeight()/2, Ground.class);
        return under != null;
    }

    /**
     * A method that gets the X and Y value of the mous and uses it to aim
     * the fire orb
     * animationDelay is used to keep the image of the player shooting for a
     * longer period of time
     *
     */
    private void attack() {
        MouseInfo m = Greenfoot.getMouseInfo();
        int mouseX = m.getX();
        int mouseY = m.getY();
        animationDelay++;
        if (animationDelay >= 10) {
            Orb o = new Orb();
            ((Game)getWorld()).addObject (o, getX(), getY());
            o.turnTowards (mouseX, mouseY);
            character_state = GameConstants.CHARACTERSTATE_IDLE;
            animationDelay = 0;
        }
    }

    /**
     * A method to check if the player is touching a mob
     *
     */
    private void checkHit() {
        if (isTouching (Mob.class)) {
            character_state = GameConstants.CHARACTERSTATE_HIT;
        }
    }

    /**
     * A method called if the player has touched a mob
     * The location is set back to the beginning and the lives scoreboard
     * is updated
     *
     */
    private void takeDamage() {
        setLocation (49, 623);
        lives--;
        GameConstants.LIVES_SCOREBOARD.updateLivesBar (lives);
        character_state = GameConstants.CHARACTERSTATE_IDLE;
    }
}