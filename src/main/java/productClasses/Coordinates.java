package productClasses;
import exceptions.InvalidValueException;
import exceptions.CannotBeNullException;


public class Coordinates {
    private Float x; //Максимальное значение поля: 177, Поле не может быть null
    private float y;

    /**
     *
     * @param x
     * @param y
     * @throws InvalidValueException
     * @throws CannotBeNullException
     */
    public Coordinates(Float x, float y) throws InvalidValueException, CannotBeNullException {
        if (x.floatValue() > 177){
            throw new InvalidValueException("Maximum X value 177");
        }
        else if (x == null){
            throw new CannotBeNullException("set X");
        }
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
