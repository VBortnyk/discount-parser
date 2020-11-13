package com.parser.service.impl;

import com.opencsv.CSVWriter;
import com.parser.mapper.ProductMapper;
import com.parser.model.Product;
import com.parser.service.CsvHandler;
import com.parser.util.AppProperty;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CsvHandlerImpl implements CsvHandler<Product> {
    private final ProductMapper productMapper;

    public CsvHandlerImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public File create(String fileName, Product product) {
        Path projectPath = Paths.get(System.getProperty("user.dir"));
        Path uri = Paths.get(AppProperty.get("fileDir").replace("->", File.separator));
        Path dir = projectPath.resolve(uri).resolve(fileName);
        create(dir, fileName, product);
        return new File(String.valueOf(dir));
    }

    @Override
    public File create(Path path, String fileName, Product product) {
        if (!Files.exists(path)) {
            File file = new File(String.valueOf(path));
            if (file.length() == 0) {
                try {
                    setHeader(product.getClass().getDeclaredConstructor().newInstance(), file);
                } catch (InstantiationException | IllegalAccessException
                        | InvocationTargetException | NoSuchMethodException e) {
                    throw new RuntimeException("Failed to create header for file: "
                            + path.toString());
                }
            }
            return file;
        }
        return new File(String.valueOf(path.resolve(fileName)));
    }

    @Override
    public void write(Product product, File file) {
        String[] data = productMapper.toFieldArray(product);
        try (CSVWriter writer = new CSVWriter(new FileWriter(file.getAbsolutePath(), true))) {
            writer.writeNext(data, false);
        } catch (IOException e) {
            throw new RuntimeException("Failed to instantiate CSVWriter. Please check file path");
        }
    }

    @Override
    public void write(List<Product> data, File file) {
        data.forEach(array -> write(array, file));
    }

    @Override
    public void setHeader(Product product, File file) {
        Field[] fields = product.getClass().getDeclaredFields();
        String[] header = Arrays.stream(fields).map(Field::getName).toArray(String[]::new);
        try (CSVWriter writer = new CSVWriter(new FileWriter(file.getAbsolutePath(), true))) {
            writer.writeNext(header, false);
        } catch (IOException e) {
            throw new RuntimeException("Failed to create heder for file " + file.getName());
        }
    }
}
