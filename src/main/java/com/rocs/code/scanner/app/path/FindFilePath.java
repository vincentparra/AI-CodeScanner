package com.rocs.code.scanner.app.path;

import com.rocs.code.scanner.data.model.Field;

import java.nio.file.Path;
import java.util.List;

public interface FindFilePath {
    List<Path> findPath();
    int findAllLines(String fileName, Field field);
}
