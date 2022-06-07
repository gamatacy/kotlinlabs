package productClasses;

import annotations.AutoGen;
import enums.*;
import utils.StringToObject;

import java.io.BufferedReader;
import java.lang.reflect.Field;
import java.util.*;

public class FieldsReader {
    private Field[] allFields;

    public FieldsReader(Class<?> aClass) {
        this.allFields = getClassFields(aClass);
    }

    public Object[] read(BufferedReader reader, InputMode inputMode) {
        Object[] values = new Object[allFields.length];

        int counter = 0;
        while (counter < allFields.length) {
            Field field = allFields[counter];
            String input = null;

            if (inputMode == InputMode.USER) {
                if (field.isAnnotationPresent(AutoGen.class)) {
                    counter++;
                    continue;
                } else if (field.getType().isEnum()) {
                    System.out.print("Enter " + field.getName() + " ( ");
                    for (Object enums : field.getType().getEnumConstants()) {
                        System.out.print(enums.toString() + " ");
                    }
                    System.out.print("): ");
                } else {
                    System.out.print("Enter " + field.getName() + " : ");
                }
            }

            if (inputMode == InputMode.SCRIPT) {
                if (field.isAnnotationPresent(AutoGen.class)) {
                    counter++;
                    continue;
                }
            }

            try {
                input = reader.readLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }


            try {
                Object objectValue = StringToObject.convert(input, field.getType());
                FieldsValidator.validateField(input, field);
                values[counter] = objectValue;
            } catch (NumberFormatException ne) {
                System.out.println(field.getName() + " must be " + field.getType());
                continue;
            } catch (Exception e) {
                if (inputMode == InputMode.SCRIPT) {
                    e.printStackTrace();
                }
                System.out.println(e.getMessage());
                continue;
            }
            counter++;
        }
        return values;
    }

    public static Field[] getClassFields(Class<?> aClass) {
        List<Field> fields = new ArrayList<>();

        for (Field field : aClass.getDeclaredFields()) {
            Class<?> fieldType = field.getType();
            if (aClass.getPackage().equals(fieldType.getPackage()) && !fieldType.isEnum()) {
                fields.addAll(Arrays.asList(getClassFields(fieldType)));
                continue;
            }
            fields.add(field);
        }
        return fields.toArray(new Field[]{});
    }

}

