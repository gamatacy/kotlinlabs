package productClasses;

import annotations.AutoGen;
import enums.InputMode;

import java.io.BufferedReader;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Function;


public class FieldsReader {
    private Field[] allFields;

    public FieldsReader(Class<?> aClass) {
        this.allFields = getRecursiveFields(aClass);
    }

    public Object[] read(BufferedReader reader, InputMode inputMode)  {
        Object[] values = new Object[allFields.length];

        int counter = 0;
        while (counter < allFields.length) {
            Field field = allFields[counter];
            String input = null;

            if (inputMode == InputMode.USER) {
                if (field.isAnnotationPresent(AutoGen.class)) {
                    counter++;
                    continue;
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
                    Object objectValue = StringToObject(input, field.getType());
                    FieldsValidator.validateField(input, field);
                    values[counter] = objectValue;
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

    public static Field[] getRecursiveFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();

        for (Field field : clazz.getDeclaredFields()) {
            Class<?> fieldType = field.getType();
            if (clazz.getPackage().equals(fieldType.getPackage()) && !fieldType.isEnum()) {
                fields.addAll(Arrays.asList(getRecursiveFields(fieldType)));
                continue;
            }
            fields.add(field);
        }
        return fields.toArray(new Field[]{});
    }

    public static Object StringToObject(String str, Class<?> fieldClass) {
        HashMap<Class<?>, Function<String, ?>> parser = new HashMap<>();
        parser.put(int.class, Integer::parseInt);
        parser.put(float.class, Float::parseFloat);
        parser.put(Integer.class, Integer::valueOf);
        parser.put(Float.class, Float::valueOf);
        parser.put(String.class, String::valueOf);

        if (fieldClass == Date.class){
            return str;
        }

        if (str.length() == 0) return null;

        Function<String, ?> function = parser.get(fieldClass);
        if (function != null) {
            try {
                return function.apply(str);
            } catch (Exception e) {
                throw e;
            }
        }

        if (fieldClass.isEnum())
            try {
                Object enumConstant = Enum.valueOf((Class<Enum>) fieldClass, str);
                return enumConstant;
            } catch (IllegalArgumentException e) {
                return null;
            }

        throw new UnsupportedOperationException("Can't parse string to " + fieldClass.getName());
    }
}

