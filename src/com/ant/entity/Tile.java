package com.ant.entity;

/**
 * Tile Class
 * @author Red Williams <red.devcs@gmail.com>
 * @since Jun 27, 2021
 */
public class Tile {

    /**
     * State of the Tile (on or off)
     */
    private boolean state;

    /**
     * Tile Class Constructor
     */
    public Tile() {
        state = false;
    }


    /**
     * Toggles the state of the Tile
     */
    public void toggleState() {
        state = !state;
    }


    /**
     * Returns the state of the Tile
     * @return the state of the TIle
     */
    public boolean getState() {
        return state;
    }

}
