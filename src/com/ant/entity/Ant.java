package com.ant.entity;

/**
 * Ant Class
 * Ant should only be concerned about it's Direction,
 * the AntDisplay should handle the movement
 * @author Red Williams <red.devcs@gmail.com>
 * @since Jun 27, 2021
 */
public class Ant {

    /**
     * Direction the Ant is facing
     */
    Direction currentDirection;

    /**
     * Ant Class Constructor
     */
    public Ant(Direction initialDirection) {
        currentDirection = initialDirection;
    }


    /**
     * Returns the current direction of the Ant
     * @return the current direction of the Ant
     */
    public Direction getDirection() {
        return currentDirection;
    }


    /**
     * Sets the new direction of the ant
     * @param newDirection New Direction of the Ant
     */
    public void setDirection(Direction newDirection) {
        currentDirection = newDirection;
    }

}
