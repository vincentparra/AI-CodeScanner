package com.rocs.code.scanner.app.collection;

import com.rocs.code.scanner.data.model.Field;

import java.util.List;

public interface TrainCollection {
    List<String> collectAllAiComments();
    List<String> collectAllAiNamingConvention();
    List<String> collectCode(String fileName, Field field);
}
