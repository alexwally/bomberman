package ru.atom.gameserver.gamesession.model;

public class Flame extends AbstractGameObject implements Tickable {

    private final long lifetime = 200;
    private int timer = 0;
    private boolean isDead = false;

    public Flame(int x, int y) {
        super(x, y);
    }

    @Override
    public void tick(long elapsed) {
        timer += elapsed;
        if (timer >= lifetime) {
            isDead = true;
        }
        //destroy();
    }
}
