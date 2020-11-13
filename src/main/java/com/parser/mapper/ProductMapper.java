package com.parser.mapper;

import com.parser.model.Product;
import java.lang.reflect.Field;
import java.util.Arrays;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public String[] toFieldArray(Product product) {
        Field[] fields = product.getClass().getDeclaredFields();
        String[] fieldArray = Arrays.stream(fields).map(Field::getName).toArray(String[]::new);
        for (int i = 0; i < fieldArray.length; i++) {
            try {
                fields[i].setAccessible(true);
                fieldArray[i] = fields[i].get(product).toString();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(
                        "Failed to retrieve field values of product: " + product.getName());
            }
        }
        return fieldArray;
    }
}
