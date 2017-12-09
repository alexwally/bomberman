package ru.atom.gameserver.gamesession.model;

import ru.atom.gameserver.gamesession.geometry.Point;

public class Player extends AbstractGameObject implements Movable {
    private int speed = 1;

    public Player(int x, int y) {
        super(x, y);
    }

    private boolean gameOver = false;
    private String name = null;

    @Override
    public Point move(Direction direction, long time) {

        int coordinateX = getPosition().getX();
        int coordinateY = getPosition().getY();
        long distance = time * getSpeed();

        switch (direction) {
            case UP:
                coordinateY += distance;
                break;
            case DOWN:
                coordinateY -= distance;
                break;
            case LEFT:
                coordinateX -= distance;
                break;
            case RIGHT:
                coordinateX += distance;
                break;
            case IDLE:
                break;
            default:
                break;
        }

        return position = new Point(coordinateX, coordinateY);
    }

    @Override
    public void tick(long elapsed) {

    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
