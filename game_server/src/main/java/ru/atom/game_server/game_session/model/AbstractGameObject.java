package ru.atom.game_server.game_session.model;

import ru.atom.game_server.game_session.geometry.Point;

public abstract class AbstractGameObject implements Positionable {

    private int id;
    private static int nextId = 0;
    protected Point position;

    protected AbstractGameObject(int x, int y) {
        this.position = new Point(x, y);
        this.id = nextId;
        nextId++;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Point getPosition() {
        return position;
    }
}
