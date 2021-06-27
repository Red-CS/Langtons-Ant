package com.ant;

import javax.swing.JFrame;
import com.ant.display.AntDisplay;

/**
 * AntWindow Class
 * Holds the main frame and runs the program
 * @author Red Williams <red.devcs@gmail.com>
 * @since Jun 27, 2021
 */
@SuppressWarnings("serial")
public class AntWindow extends JFrame {

    /** Width of the Window */
    public static int WINDOW_WIDTH = 600;

    /** Height of the Window */
    public static int WINDOW_HEIGHT = 600;

    /**
     * AntWindow Class Constructor
     */
    public AntWindow() {
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Langton's Ant");
        setContentPane(new AntDisplay());
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }


    /**
     * Runs the program
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        new AntWindow();
    }

}
