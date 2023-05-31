package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

// BEGIN
public class Validator {
    public static List<String> validate(Address address) {
        List<String> annotationField = new ArrayList<>();
        for (Field field : address.getClass().getDeclaredFields()) {
            NotNull annot = field.getAnnotation(NotNull.class);
            if (annot != null) {
                try {
                    field.setAccessible(true);
                    if (field.get(address) == null) {
                        annotationField.add(field.getName());
                    }
                } catch (NoSuchFieldError | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return annotationField;
    }
}
// END
