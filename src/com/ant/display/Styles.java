package com.ant.display;

import java.awt.Color;

/**
 * Styles of the Display
 * @author Red Williams <red.devcs@gmail.com>
 * @since Jun 27, 2021
 */
public @interface Styles {

    /** Background Color */
    Color bg = new Color(245, 245, 245);

    /** Tile Color */
    Color tile = new Color(0, 0, 0);

    /** Ant Color */
    Color ant = new Color(255, 0, 0);
}
