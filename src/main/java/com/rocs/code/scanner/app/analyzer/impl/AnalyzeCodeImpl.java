package com.rocs.code.scanner.app.analyzer.impl;

import com.rocs.code.scanner.app.collection.TrainCollection;
import com.rocs.code.scanner.app.collection.impl.TrainCollectionImpl;
import com.rocs.code.scanner.data.model.Field;
import com.rocs.code.scanner.app.analyzer.AnalyzeCode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AnalyzeCodeImpl implements AnalyzeCode {
    private TrainCollection stringCollection = new TrainCollectionImpl();
    @Override
    public int countTotalInLineComments(String fileName, Field field) {
        int count = field.getInlineCommentCount();
        String lineString;
        try {
            BufferedReader files = new BufferedReader(new FileReader(fileName));
            LineNumberReader lineNumberReader = new LineNumberReader(files);
            while ((lineString = lineNumberReader.readLine()) != null){
                if(lineString.matches(".*//.*")){
                    count++;
                }
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        field.setInlineCommentCount(count);
        return count;
    }
    @Override
    public int countComplexComments(String fileName, Field field) {
        int commentCount = field.getCommentCount();
        List<String> code = stringCollection.collectCode(fileName, field);
        List<String> aiPhrases = stringCollection.collectAllAiComments();
        String regexPattern = "\\b(" + String.join("|", aiPhrases) + ")\\b.*";
        Pattern aiPattern = Pattern.compile(regexPattern, Pattern.CASE_INSENSITIVE);
        for (String c : code) {
            Matcher matcher = aiPattern.matcher(c);
            if(c.contains("//") && matcher.find()) {
                commentCount++;
            }
        }
        field.setCommentCount(commentCount);
        return commentCount;
    }
    @Override
    public int countConsecutiveCodeSpace(String fileName, Field field) {
        int countConsecutiveCodeSpace = 0;
        String lineString;
        boolean isConsecutive = false;
        int countOccurance = 1;
        try {
            BufferedReader files = new BufferedReader(new FileReader(fileName));
            LineNumberReader lineNumberReader = new LineNumberReader(files);
            while ((lineString = lineNumberReader.readLine()) != null){
                if(lineString.isEmpty()){
                    countOccurance++;
                    if(countOccurance >= 2){
                        isConsecutive = true;
                    }
                    if (isConsecutive){
                        countConsecutiveCodeSpace++;
                        field.setEmptyCodeSpaceCount(countConsecutiveCodeSpace);
                    }
                }else{
                    isConsecutive = false;
                    countOccurance = 0;
                }
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        return countConsecutiveCodeSpace;
    }
    @Override
    public int countTotalCodeSpace(String fileName, Field field) {
        int totalCodeSpace = 0;
        String lineString;
        try {
            BufferedReader files = new BufferedReader(new FileReader(fileName));
            LineNumberReader lineNumberReader = new LineNumberReader(files);
            while ((lineString = lineNumberReader.readLine()) != null){
                if(lineString.isBlank()){
                    totalCodeSpace++;

                }
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        return totalCodeSpace;
    }
    @Override
    public int countTotalDecision(String fileName, Field field) {
        int decisionCount = 0;
        String lineString;
        try {
            BufferedReader files = new BufferedReader(new FileReader(fileName));
            LineNumberReader lineNumberReader = new LineNumberReader(files);
            String totalRegexPattern = "^.switch\\s\\(|case\\s|if\\s\\(|else\\sif\\s\\(|else\\s\\{|try\\s\\{|catch\\s\\{";
            Pattern totalPattern = Pattern.compile(totalRegexPattern);
            while ((lineString = lineNumberReader.readLine()) != null){
                Matcher matcher = totalPattern.matcher(lineString);
                if(matcher.find()){
                    decisionCount++;
                    field.setDecisionCount(decisionCount);
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return decisionCount;
    }
    @Override
    public int countImport(String fileName, Field field) {
        int importCount = field.getImportCount();
        String lineString;
        try {
            BufferedReader files = new BufferedReader(new FileReader(fileName));
            LineNumberReader lineNumberReader = new LineNumberReader(files);
            while ((lineString = lineNumberReader.readLine()) != null){
                if(lineString.contains("import java.util.*") || lineString.contains("import java.io.*")||lineString.contains("import java.text.*")){
                    importCount++;
                    field.setImportCount(importCount);
                }
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        return importCount;
    }
    @Override
    public int countGenericNamingConvention(String fileName, Field field) {
        int count = field.getCountNamingConvention();
        List<String> code = stringCollection.collectCode(fileName, field);
        List<String> namingConvention = stringCollection.collectAllAiNamingConvention();
        String regexPattern = "\\s.*(" + String.join("|", namingConvention) + ").*";
        Pattern aiPattern = Pattern.compile(regexPattern, Pattern.CASE_INSENSITIVE);
        for (String c : code) {
            Matcher matcher = aiPattern.matcher(c);
            if(matcher.matches()) {
                count++;
                field.setCountNamingConvention(count);
            }
        }
        return count;
    }

    @Override
    public int countTryCatch(String fileName, Field field) {
        int countTryCatch = 0;
        String lineString;
        try {
            BufferedReader files = new BufferedReader(new FileReader(fileName));
            LineNumberReader lineNumberReader = new LineNumberReader(files);
            while ((lineString = lineNumberReader.readLine()) != null){
                if(lineString.contains("e.printStackTrace(")||lineString.contains("System.err.println(")){
                    countTryCatch++;
                    field.setExceptionHandlingCount(countTryCatch);
                }
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        return countTryCatch;
    }
    @Override
    public int countRarePrint(String fileName, Field field) {
        int countRarePrint = 0;
        String lineString;
        try {
            BufferedReader files = new BufferedReader(new FileReader(fileName));
            LineNumberReader lineNumberReader = new LineNumberReader(files);
            while ((lineString = lineNumberReader.readLine()) != null){
                if(lineString.contains("e.printStackTrace(")||lineString.contains("System.err.println(")){
                    countRarePrint++;
                    field.setExceptionHandlingCount(countRarePrint);
                }
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        return countRarePrint;
    }

    @Override
    public int countMultiLineCommentFormat(String fileName, Field field) {
        int countMultiLineComment = 0;
        int countSpace = 0;
        boolean hasInlineComment = false;
        String lineString;
        try {
            BufferedReader files = new BufferedReader(new FileReader(fileName));
            LineNumberReader lineNumberReader = new LineNumberReader(files);
            while ((lineString = lineNumberReader.readLine()) != null){
                if(lineString.endsWith("*/")){
                    hasInlineComment = true;
                }
                if(hasInlineComment){
                    countSpace++;
                    if(countSpace >= 3 && (lineString.matches(".*public.*")||lineString.matches("public.*"))){
                        countMultiLineComment++;
                        field.setWrongPatternCount(countMultiLineComment);
                        countSpace = 0;
                        hasInlineComment =false;
                    }
                    if(lineString.matches(".*private.*")||lineString.matches("private.*")){
                        countMultiLineComment++;
                        hasInlineComment = false;
                        field.setWrongPatternCount(countMultiLineComment);
                    }
                }
            }
        }catch (IOException e) {
            throw new RuntimeException();
        }
        return countMultiLineComment;
    }
}