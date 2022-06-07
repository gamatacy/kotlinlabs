package productClasses;

import annotations.*;
import enums.*;
import exceptions.InvalidValueException;

import java.lang.reflect.Field;

public class FieldsValidator {

    public static void validateField(String value, Field field) throws InvalidValueException {
        nullCheck(value, field);
        boundCheck(value, field);
        enumCheck(value, field);
    }

    /**
    public static void validateArray(String[] values) throws InvalidValueException{
        int index = 0;
        for (Field field : Product.class.getDeclaredFields()) {
            if (field.getType() == Coordinates.class) {
                for (Field field1 : Coordinates.class.getDeclaredFields()){
                    validateField(values[index], field1);
                    index += 1;
                }
            } else if (field.getType() == Organization.class) {
                for (Field field1 : Coordinates.class.getDeclaredFields()){
                    validateField(values[index], field1);
                    index += 1;
                }
            } else {
                validateField(values[index], field);
            }
            index += 1;
        }
    }
     */

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

