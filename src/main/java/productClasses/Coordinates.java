package Collection.Classes;
import Exceptions.invalidValueException;
import Exceptions.cannotBeNullException;


public class Coordinates {
    private Float x; //Максимальное значение поля: 177, Поле не может быть null
    private float y;

    /**
     *
     * @param x
     * @param y
     * @throws invalidValueException
     * @throws cannotBeNullException
     */
    public Coordinates(Float x, float y) throws invalidValueException, cannotBeNullException{
        if (x.floatValue() > 177){
            throw new invalidValueException("Maximum X value 177");
        }
        else if (x == null){
            throw new cannotBeNullException("set X");
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
