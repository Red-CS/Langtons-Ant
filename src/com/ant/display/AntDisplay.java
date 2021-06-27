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
 * 
 * @author Red Williams <red.devcs@gmail.com>
 * @since Jun 26, 2021
 */
public class AntDisplay extends JPanel
    implements MouseListener, MouseMotionListener, KeyListener {

    private final int TILE_SIZE = 5;
    private Tile[][] grid;
    private int antXIndex;
    private int antYIndex;
    private Ant ant;
    private int steps;
    private boolean isRunning;
    private Timer antTimer;
    private int timerDelay;

    private final int MIN_DELAY = 5;
    private final int MAX_DELAY = 1000;

    /**
     * AntDisplay Class Constructor
     */
    public AntDisplay() {
        setLayout(null);
        setPreferredSize(new Dimension(AntWindow.WINDOW_WIDTH,
            AntWindow.WINDOW_HEIGHT));
        setBackground(new Color(245, 245, 245));
        setFocusable(true);
        requestFocus();

        grid = new Tile[AntWindow.WINDOW_WIDTH][AntWindow.WINDOW_HEIGHT];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new Tile();
            }
        }

        ant = new Ant(Direction.DOWN);

        antXIndex = AntWindow.WINDOW_WIDTH / 2;
        antYIndex = AntWindow.WINDOW_HEIGHT / 2;

        isRunning = false;
        steps = 0;
        timerDelay = MIN_DELAY;

        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);

        JSlider jslider = new JSlider(MIN_DELAY, MAX_DELAY, timerDelay);
        jslider.setMajorTickSpacing(100);
        jslider.setMinorTickSpacing(50);
        jslider.setPaintTicks(true);
        jslider.setBounds(30, 60, 175, 45);
        jslider.setFocusable(false);
        jslider.setOpaque(false);

        Hashtable<Integer, JLabel> labelTable =
            new Hashtable<Integer, JLabel>();
        labelTable.put(5, new JLabel("5ms"));
        labelTable.put(1000, new JLabel("1000ms"));
        jslider.setLabelTable(labelTable);
        jslider.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider)e.getSource();
                if (!source.getValueIsAdjusting()) {
                    int newDelay = source.getValue();
                    System.out.println(newDelay);
                    timerDelay = newDelay;
                    if (antTimer != null) {
                        antTimer.setInitialDelay(timerDelay);
                        antTimer.setDelay(timerDelay);
                        if (isRunning) {
                            antTimer.restart();
                        }
                    }
                }
            }

        });

        jslider.setPaintLabels(true);
        add(jslider);

    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;

        // Draw Black Tiles
        for (int row = 0; row < AntWindow.WINDOW_HEIGHT; row++) {
            for (int col = 0; col < AntWindow.WINDOW_WIDTH; col++) {
                if (grid[row][col].getState()) {
                    g2d.setColor(Color.BLACK);
                    g2d.fillRect(row, col, TILE_SIZE, TILE_SIZE);
                }
            }
        }

        // Draw Ant, if applicable
        if (ant != null) {
            g2d.setColor(Color.RED);
            g2d.fillRect(antXIndex, antYIndex, TILE_SIZE, TILE_SIZE);
        }

        // Draw Steps Counter
        g2d.drawString("Steps: " + steps, 50, 50);

    }


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

                // -- Reposition
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

                steps++;
                repaint();

            }

        });
        antTimer.start();
    }


    private void toggleTile(int xIndex, int yIndex) {
        grid[xIndex][yIndex].toggleState();
        repaint();
    }


    private boolean isInBounds(int xPos, int yPos) {
        return (xPos >= 0 && xPos < AntWindow.WINDOW_WIDTH) && (yPos >= 0
            && yPos < AntWindow.WINDOW_HEIGHT);
    }


    /**
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (!isRunning) {
            int pointX = e.getX() - (e.getX() % TILE_SIZE);
            int pointY = e.getY() - (e.getY() % TILE_SIZE);
            toggleTile(pointX, pointY);
        }
    }


    /**
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }


    /**
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }


    /**
     * @param e
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }


    /**
     * @param e
     */
    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }


    /**
     * @param e
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if (!isRunning) {
            int pointX = e.getX() - (e.getX() % TILE_SIZE);
            int pointY = e.getY() - (e.getY() % TILE_SIZE);
            if (isInBounds(pointX, pointY)) {
                toggleTile(pointX, pointY);
            }
        }
    }


    /**
     * @param e
     */
    @Override
    public void mouseMoved(MouseEvent e) {

    }


    /**
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }


    /**
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub

    }


    /**
     * @param e
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

}
