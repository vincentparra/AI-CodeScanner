package com.rocs.code.scanner.app.collection.impl;

import com.rocs.code.scanner.app.collection.TrainCollection;
import com.rocs.code.scanner.data.model.Field;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TrainCollectionImpl implements TrainCollection {
    @Override
    public List<String> collectAllAiComments() {

        List<String> phrase = new ArrayList<>();
        String resourcePath = "AIDataSet.txt";
        String lineString;
        try{
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath);
            InputStreamReader streamReader = new InputStreamReader(Objects.requireNonNull(inputStream), StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader);
            String line;
            while ((line = reader.readLine()) != null) {
                phrase.add(line);
            }
        } catch (IOException | NullPointerException e) {
            throw new RuntimeException("Failed to load AI phrases from resource file: " + resourcePath, e);
        }

        return phrase;
    }

    @Override
    public List<String> collectAllAiNamingConvention() {
        List<String> namingConventions = new ArrayList<>();
        String resourcePath = "AIDataSet2.txt";
        String lineString;
        try{
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath);
            InputStreamReader streamReader = new InputStreamReader(Objects.requireNonNull(inputStream), StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader);
            String line;
            while ((line = reader.readLine()) != null) {
                namingConventions.add(line);
            }
        } catch (IOException | NullPointerException e) {
            throw new RuntimeException("Failed to load AI phrases from resource file: " + resourcePath, e);
        }

        return namingConventions;
    }


    @Override
    public List<String> collectCode(String fileName, Field field) {
        List<String> phrase = new ArrayList<>();
        String lineString;
        try {
            BufferedReader files = new BufferedReader(new FileReader(fileName));
            LineNumberReader lineNumberReader = new LineNumberReader(files);
            while ((lineString = lineNumberReader.readLine()) != null){
                phrase.add(lineString);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return phrase;
    }
}
