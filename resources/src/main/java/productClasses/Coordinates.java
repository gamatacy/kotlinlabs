package productClasses;

import annotations.LowerThan;
import annotations.NotNull;
import exceptions.CannotBeNullException;
import exceptions.InvalidValueException;

import java.io.Serializable;


public class Coordinates implements Serializable {
    @NotNull
    @LowerThan(value = 177)
    private Float x; //Максимальное значение поля: 177, Поле не может быть null
    private float y;

    /**
     *
     * @param x
     * @param y
     * @throws InvalidValueException
     * @throws CannotBeNullException
     */
    public Coordinates(Float x, float y){
        this.x = x;
        this.y = y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
