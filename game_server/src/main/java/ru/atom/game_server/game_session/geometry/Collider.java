package ru.atom.game_server.game_session.geometry;

/**
 * Entity that can physically intersect, like flame and player
 */
public interface Collider {
    /**
     * @return true if Colliders geometrically intersect
     */
    boolean isColliding(Collider other);
}