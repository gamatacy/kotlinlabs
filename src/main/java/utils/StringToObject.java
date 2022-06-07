package utils;

import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

public class StringToObject {
    public static Object convert(String str, Class<?> fieldClass) {
        HashMap<Class<?>, Function<String, ?>> parser = new HashMap<>();
        parser.put(int.class, Integer::parseInt);
        parser.put(float.class, Float::parseFloat);
        parser.put(Integer.class, Integer::valueOf);
        parser.put(Float.class, Float::valueOf);
        parser.put(String.class, String::valueOf);

        if (fieldClass == Date.class) {
            return str;
        }

        if (str.length() == 0) return null;

        Function<String, ?> function = parser.get(fieldClass);

        if (function != null) {
            return function.apply(str);
        }

        if (fieldClass.isEnum())
            try {
                Object anEnum = Enum.valueOf((Class<Enum>) fieldClass, str.toUpperCase());
                return anEnum;
            } catch (Exception e) {
                return null;
            }

        throw new UnsupportedOperationException("Can't parse string to " + fieldClass.getName());
    }

}
