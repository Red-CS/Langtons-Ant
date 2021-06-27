package com.ant.entity;

/**
 * Direction Enumerated Type
 * @author Red Williams <red.devcs@gmail.com>
 * @since Jun 27, 2021
 */
public enum Direction {
    LEFT() {
        @Override
        public Direction getLeftDirection() {
            return DOWN;
        }


        @Override
        public Direction getRightDirection() {
            return UP;
        }
    },

    RIGHT() {
        @Override
        public Direction getLeftDirection() {
            return UP;
        }


        @Override
        public Direction getRightDirection() {
            return DOWN;
        }
    },

    UP() {
        @Override
        public Direction getLeftDirection() {
            return LEFT;
        }


        @Override
        public Direction getRightDirection() {
            return RIGHT;
        }
    },

    DOWN() {
        @Override
        public Direction getLeftDirection() {
            return RIGHT;
        }


        @Override
        public Direction getRightDirection() {
            return LEFT;
        }
    };

    /**
     * Gets the Left Direction of the Enum
     * @return the Left Direction of the Enum
     */
    public abstract Direction getLeftDirection();

    /**
     * Gets the Right Direction of the Enum
     * @return the Right Direction of the Enum
     */
    public abstract Direction getRightDirection();
}
