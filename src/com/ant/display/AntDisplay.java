package com.ant.display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Hashtable;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import com.ant.AntWindow;
import com.ant.entity.Ant;
import com.ant.entity.Direction;
import com.ant.entity.Tile;

/**
 * AntDisplay Class
 * Renders the content to the screen, controlling the Simulation
 * @author Red Williams <red.devcs@gmail.com>
 * @since Jun 27, 2021
 */
@SuppressWarnings("serial")
public class AntDisplay extends JPanel
    implements MouseListener, MouseMotionListener, KeyListener {

    /** Grid of Tiles */
    private Tile[][] grid;

    /** Ant's X position in the grid */
    private int antXIndex;

    /** Ant's Y position in the grid */
    private int antYIndex;

    /** Ant object */
    private Ant ant;

    /** Counts the number of steps in the sequence */
    private int steps;

    /** Whether the simulation is running or not */
    private boolean isRunning;

    /** Timer for screen events */
    private Timer antTimer;

    /** Timer delay */
    private int timerDelay;

    /** Tile Size */
    private final int TILE_SIZE = 5;

    /** Min Timer Delay */
    private final int MIN_DELAY = 5;

    /** Max Timer Delay */
    private final int MAX_DELAY = 1000;

    /** Timer Delay Slider setter */
    private JSlider delaySlider;

    /**
     * AntDisplay Class Constructor
     */
    public AntDisplay() {

        // Set Panel properties
        setLayout(null);
        setPreferredSize(new Dimension(AntWindow.WINDOW_WIDTH,
            AntWindow.WINDOW_HEIGHT));
        setBackground(new Color(245, 245, 245));
        setFocusable(true);
        requestFocus();

        // Create Grid of Tiles
        grid = new Tile[AntWindow.WINDOW_WIDTH][AntWindow.WINDOW_HEIGHT];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new Tile();
            }
        }

        // Initialize Ant
        ant = new Ant(Direction.DOWN);

        // Place Ant in the center of the screen
        antXIndex = AntWindow.WINDOW_WIDTH / 2;
        antYIndex = AntWindow.WINDOW_HEIGHT / 2;

        // Set other instance variables
        isRunning = false;
        steps = 0;
        timerDelay = MIN_DELAY;

        // Add Event Listeners
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);

        // Set Delay Slider
        delaySlider = new JSlider(MIN_DELAY, MAX_DELAY, timerDelay);
        delaySlider.setMajorTickSpacing(100);
        delaySlider.setMinorTickSpacing(50);
        delaySlider.setPaintTicks(true);
        delaySlider.setBounds(30, 60, 175, 45);
        delaySlider.setFocusable(false);
        delaySlider.setOpaque(false);

        // Set Slider Labels
        Hashtable<Integer, JLabel> labelTable =
            new Hashtable<Integer, JLabel>();
        labelTable.put(MIN_DELAY, new JLabel(MIN_DELAY + "ms"));
        labelTable.put(MAX_DELAY, new JLabel(MAX_DELAY + "ms"));
        delaySlider.setLabelTable(labelTable);
        delaySlider.setPaintLabels(true);
        add(delaySlider);

        // Register StateChange Listener
        delaySlider.addChangeListener(new ChangeListener() {

            /**
             * Listens and Runs whenever the state of the slider
             * {@code delaySlider} changes
             * @param e ChangeEvent
             */
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();

                // Only run after the user drops the slider
                if (!source.getValueIsAdjusting()) {
                    timerDelay = source.getValue();

                    // If Timer has been started before
                    if (antTimer != null) {
                        antTimer.setInitialDelay(timerDelay);
                        antTimer.setDelay(timerDelay);

                        // Restart if it's running
                        if (isRunning) {
                            antTimer.restart();
                        }
                    }
                }
            }

        });

    }


    /**
     * Paints the Screen
     * @param g Graphics Object
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw Tiles
        for (int row = 0; row < AntWindow.WINDOW_HEIGHT; row++) {
            for (int col = 0; col < AntWindow.WINDOW_WIDTH; col++) {
                if (grid[col][row].getState()) {
                    g2d.setColor(Color.BLACK);
                    g2d.fillRect(col, row, TILE_SIZE, TILE_SIZE);
                }
            }
        }

        // Draw Ant
        g2d.setColor(Color.RED);
        g2d.fillRect(antXIndex, antYIndex, TILE_SIZE, TILE_SIZE);

        // Draw Steps Counter
        g2d.drawString("Steps: " + steps, 50, 50);
    }


    /**
     * Run's the Langton's Ant Simulation
     */
    public void run() {

        antTimer = new Timer(timerDelay, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // Turn
                Tile antTile = grid[antXIndex][antYIndex];
                if (antTile.getState()) {
                    ant.setDirection(ant.getDirection().getLeftDirection());
                }
                else {
                    ant.setDirection(ant.getDirection().getRightDirection());
                }

                // Toggle State
                antTile.toggleState();

                // Move
                switch (ant.getDirection()) {
                    case UP:
                        antYIndex -= TILE_SIZE;
                        break;
                    case DOWN:
                        antYIndex += TILE_SIZE;
                        break;
                    case LEFT:
                        antXIndex -= TILE_SIZE;
                        break;
                    case RIGHT:
                        antXIndex += TILE_SIZE;
                        break;

                }

                // -- Reposition if off screen
                if (antXIndex >= AntWindow.WINDOW_WIDTH) {
                    antXIndex = 0;
                }
                else if (antXIndex < 0) {
                    antXIndex = AntWindow.WINDOW_WIDTH - 1;
                }

                if (antYIndex >= AntWindow.WINDOW_HEIGHT) {
                    antYIndex = 0;
                }
                else if (antYIndex < 0) {
                    antYIndex = AntWindow.WINDOW_HEIGHT - 1;
                }

                // -- Increment steps and repaint
                steps++;
                repaint();

            }

        });
        antTimer.start();
    }


    /**
     * Toggles the Tile at the specified position, then repaints the screen
     * @param xIndex X Position of Tile
     * @param yIndex Y Position of Tile
     */
    private void toggleTile(int xIndex, int yIndex) {
        grid[xIndex][yIndex].toggleState();
        repaint();
    }


    /**
     * Returns whether or not the mouse is in bounds during the drag event
     * when adding/removing new Tiles. See {@link #mouseDragged(MouseEvent)}
     * @param xPos X Position of the mouse
     * @param yPos Y Position of the mouse
     * @return Whether or not the mouse is in bounds
     */
    private boolean isInBounds(int xPos, int yPos) {
        return (xPos >= 0 && xPos < AntWindow.WINDOW_WIDTH) && (yPos >= 0
            && yPos < AntWindow.WINDOW_HEIGHT);
    }


    /**
     * Handles the Mouse clicking the screen, which toggles the Tile
     * at the location selected
     * @param e MouseEvent
     */
    @Override
    public void mouseClicked(MouseEvent e) {

        // Only toggle Tiles if the simulation is paused
        if (!isRunning) {

            // Get the points
            int pointX = e.getX() - (e.getX() % TILE_SIZE);
            int pointY = e.getY() - (e.getY() % TILE_SIZE);
            toggleTile(pointX, pointY);
        }
    }


    /**
     * Handles the Mouse dragged across the screen, which toggles multiple Tiles
     * at once
     * @param e MouseEvent
     */
    @Override
    public void mouseDragged(MouseEvent e) {

        // Only toggle Tiles if the simulation is paused
        if (!isRunning) {

            // Get the points
            int pointX = e.getX() - (e.getX() % TILE_SIZE);
            int pointY = e.getY() - (e.getY() % TILE_SIZE);

            // Handles a drag going of screen
            if (isInBounds(pointX, pointY)) {
                toggleTile(pointX, pointY);
            }
        }
    }


    /**
     * Handles a key being pressed, simulating user controls:
     * Enter = Pause/Unpause Simulation
     * Escape = Reset and Restart
     * @param e KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {

            // Pause
            if (isRunning) {
                antTimer.stop();
                isRunning = false;
            }

            // Run loop
            else {
                isRunning = true;
                run();
            }
        }

    }

    /*
     * Unused Input Listener Methods
     */


    @Override
    public void mousePressed(MouseEvent e) {

    }


    @Override
    public void mouseReleased(MouseEvent e) {

    }


    @Override
    public void mouseEntered(MouseEvent e) {}


    @Override
    public void mouseExited(MouseEvent e) {}


    @Override
    public void mouseMoved(MouseEvent e) {}


    @Override
    public void keyTyped(KeyEvent e) {}


    @Override
    public void keyPressed(KeyEvent e) {}

}
