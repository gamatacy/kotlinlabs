package productClasses;

import annotations.GreaterThan;
import annotations.LowerThan;
import annotations.NotNull;
import enums.OrganizationType;
import exceptions.InvalidValueException;

import java.lang.reflect.Field;

public class FieldsValidator {

    public static void validateField(String value, Field field) throws InvalidValueException {
        nullCheck(value, field);
        boundCheck(value, field);
        enumCheck(value, field);
    }

    private static void nullCheck(Object value, Field field) throws InvalidValueException {
        if (field.isAnnotationPresent(NotNull.class)) {
            if (value == null || value.toString().length() == 0) {
                throw new InvalidValueException(field.getName() + " cannot be null!");
            }
        }
    }

    private static void boundCheck(Object value, Field field) throws InvalidValueException {
        if (field.isAnnotationPresent(GreaterThan.class)) {
            if (field.getAnnotation(GreaterThan.class).value() > Float.valueOf((String) value)) {
                throw new InvalidValueException(field.getName() + " must be greater than " + field.getAnnotation(GreaterThan.class).value());
            }
        }
        if (field.isAnnotationPresent(LowerThan.class)) {
            if (field.getAnnotation(LowerThan.class).value() < Float.valueOf((String) value)) {
                throw new InvalidValueException(field.getName() + " must be lower than " + field.getAnnotation(LowerThan.class).value());
            }
        }
    }

    private static void enumCheck(Object value, Field field) throws InvalidValueException {
        if (field.getType() == OrganizationType.class) {
            if (OrganizationType.equals((String) value) == null) {
                throw new InvalidValueException("Must be OrganizationType enum!");
            }
        }
    }
}

