package com.ant.entity;

/**
 * 
 * @author Red Williams <red.devcs@gmail.com>
 * @since Jun 27, 2021
 */
public class Ant {

    Direction currentDirection;

    /**
     * Ant Class Constructor
     */
    public Ant(Direction initialDirection) {
        currentDirection = initialDirection;
    }


    public Direction getDirection() {
        return currentDirection;
    }


    public void setDirection(Direction newDirection) {
        currentDirection = newDirection;
    }

}
