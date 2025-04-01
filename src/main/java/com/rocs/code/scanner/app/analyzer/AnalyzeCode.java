package com.rocs.code.scanner.app.analyzer;
import com.rocs.code.scanner.data.model.Field;

public interface AnalyzeCode {
    int countTotalCodeSpace(String fileName, Field field);
    int countConsecutiveCodeSpace(String fileName, Field field);
    int countTotalInLineComments(String fileName, Field field);
    int countComplexComments(String fileName, Field field);
    int countTotalDecision(String fileName, Field field);
    int countImport(String fileName, Field field);
    int countGenericNamingConvention(String fileName, Field field);
    int countTryCatch(String fileName, Field field);
    int countRarePrint(String fileName, Field field);
    int countMultiLineCommentFormat(String fileName, Field field);
}