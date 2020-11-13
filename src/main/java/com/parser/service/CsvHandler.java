package com.parser.service;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

public interface CsvHandler<T> {

    File create(String fileName, T t);

    File create(Path path, String fineName, T t);

    void write(T t, File file);

    void write(List<T> data, File file);

    void setHeader(T t, File file);
}
