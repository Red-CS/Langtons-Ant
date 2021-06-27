package com.ant;

import javax.swing.JFrame;
import com.ant.display.AntDisplay;

/**
 * 
 * @author Red Williams <red.devcs@gmail.com>
 * @since Jun 27, 2021
 */
public class AntWindow extends JFrame {

    public static int WINDOW_WIDTH = 600;
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


    public static void main(String[] args) {
        new AntWindow();
    }

}
