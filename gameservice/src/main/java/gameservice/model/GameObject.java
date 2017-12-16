package gameservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import gameservice.geometry.Bar;
import gameservice.geometry.Point;

public abstract class GameObject {
    private static final int widthBox = 32;
    private static final int heightBox = 32;
    protected final transient GameSession session;
    protected final int id;
    private final transient int width;
    private final transient int height;
    protected Point position;
    private String type;

    @JsonCreator
    public GameObject(GameSession session, @JsonProperty Point position,
                      @JsonProperty String type, int width, int height) {
        this.position = position;
        this.type = type;
        this.id = session.getNewId();
        this.session = session;
        this.width = width;
        this.height = height;
    }

    public static int getWidthBox() {
        return widthBox;
    }

    public static int getHeightBox() {
        return heightBox;
    }

    public Bar getBar() {
        return new Bar(position.getX(), position.getY(),
                position.getX() + width, position.getY() + height);
    }

    public int getId() {
        return id;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameObject that = (GameObject) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        int result = session != null ? session.hashCode() : 0;
        result = 31 * result + id;
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + width;
        result = 31 * result + height;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
