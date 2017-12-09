package ru.atom.gameserver.gamesession.model;

import ru.atom.gameserver.gamesession.geometry.Point;

/**
 * GameObject that can move during game
 */
public interface Movable extends Positionable, Tickable {
    /**
     * Tries to move entity towards specified direction for time
     * @return final position after movement
     */
    Point move(Direction direction, long time);
    
    enum Direction {
        UP, DOWN, RIGHT, LEFT, IDLE
    }
}
