# Langton's Ant

## How it Works
Langton's Ant is a form of Cellular Autatoma that consists of an Ant on a grid of squares, where a square can either be on or off, black or white, etc.

There are two rules:
1. If the Ant reaches an off/white square, turn 90° clockwise, toggle the state of the square the ant is on, then move forward
2. If the Ant reaches an on/black square, turn 90° counterclockwise, toggle the state of the square the ant is on, then move forward

Following these rules, the Ant should move across the screen in a seemingly random manner. What makes this so interesting is that, no matter the initial configuration of the board, the Ant will eventually reach a pattern called *The Highway*. This program simulates that effect.

## Controls

- **Play/Pause** - Press *Enter* to start and stop the simulation
- **Adding Tiles** - Click anywhere on the board to change the state of the square. You can also drag to toggle multiple square at once
- **Changing the Speed** - Move the slider to change the speed at which the Ant moves. Minimum speed is 5 milliseconds, and the maximum is 1 second
- **Reseting the Simulation** - To reset the Simulation, press *Backspace*
- **Exiting the Program** - Exit the window normally or click *Escape*
