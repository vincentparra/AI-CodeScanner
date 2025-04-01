package com.rocs.code.scanner.app.path.impl;

import com.rocs.code.scanner.app.path.FindFilePath;
import com.rocs.code.scanner.data.model.Field;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FindFilePathImpl implements FindFilePath {
    private Path root = Paths.get("src");
    @Override
    public List<Path> findPath() {
        try {
            List<Path> pathStream = Files.walk(root).toList();
            return pathStream.stream().filter(path -> path.toString().endsWith(".java")).toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int findAllLines(String fileName, Field field) {
        int lineNumber = field.getLineNumber();
        String lineString;
        try {
            BufferedReader files = new BufferedReader(new FileReader(fileName));
            LineNumberReader lineNumberReader = new LineNumberReader(files);
            while ((lineString = lineNumberReader.readLine()) != null){
                lineNumber++;
                field.setLineNumber(lineNumber);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return lineNumber;
    }
}
