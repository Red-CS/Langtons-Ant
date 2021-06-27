package com.ant.entity;

/**
 * 
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
            // TODO Auto-generated method stub
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
            // TODO Auto-generated method stub
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
            // TODO Auto-generated method stub
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
            // TODO Auto-generated method stub
            return LEFT;
        }
    };

    public abstract Direction getLeftDirection();


    public abstract Direction getRightDirection();
}
