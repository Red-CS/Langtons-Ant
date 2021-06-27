package com.ant.entity;

/**
 * 
 * @author Red Williams <red.devcs@gmail.com>
 * @since Jun 26, 2021
 */
public class Tile {

    private boolean state;

    /**
     * Tile Class Constructor
     */
    public Tile() {
        state = false;
    }


    public boolean toggleState() {
        return state = !state;
    }


    public boolean getState() {
        return state;
    }

}
