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
import javax.swing.JPanel;
import javax.swing.Timer;
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
    private int mouseX;
    private int mouseY;
    private Ant ant;
    private boolean loopStarted;
    private int steps;

    /**
     * AntDisplay Class Constructor
     */
    public AntDisplay() {
        // TODO Auto-generated constructor stub
        setPreferredSize(new Dimension(AntWindow.WINDOW_WIDTH,
            AntWindow.WINDOW_HEIGHT));
        setBackground(new Color(75, 75, 75));
        setFocusable(true);
        requestFocus();

        grid = new Tile[AntWindow.WINDOW_WIDTH][AntWindow.WINDOW_HEIGHT];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new Tile();
            }
        }

        ant = new Ant(Direction.UP);

        antXIndex = AntWindow.WINDOW_WIDTH / 2;
        antYIndex = AntWindow.WINDOW_HEIGHT / 2;

        steps = 0;

        loopStarted = false;
        System.out.println(antXIndex + " " + antYIndex);

        addMouseMotionListener(this);
        addKeyListener(this);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;

        // Draw Recently changed Tile
        if (loopStarted) {
            for (int row = 0; row < AntWindow.WINDOW_HEIGHT; row++) {
                for (int col = 0; col < AntWindow.WINDOW_WIDTH; col++) {
                    if (grid[row][col].getState()) {
                        g2d.setColor(Color.BLACK);
                        g2d.fillRect(row, col, TILE_SIZE, TILE_SIZE);

                    }
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
        if (ant == null) {
            System.out.println("Cannot run, no Ant is present");
            return;
        }

        loopStarted = true;

        Timer antTimer = new Timer(5, new ActionListener() {

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
                    default:

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


    /**
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

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
        // TODO Auto-generated method stub

    }


    /**
     * @param e
     */
    @Override
    public void mouseMoved(MouseEvent e) {
// mouseX = e.getX();
// mouseY = e.getY();
// System.out.println(mouseX + " " + mouseY);

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
        // Run loop
        run();

    }

}
