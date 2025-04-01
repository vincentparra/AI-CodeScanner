package com.rocs.code.scanner.data.model;

public class Field {

    private int lineNumber = 0;
    private int inlineCommentCount = 0;
    private int emptyCodeSpaceCount = 0;
    private int aiflags;
    private int humanFlags;
    private int complexCommentCount;
    private int decisionCount;
    private int humanDecision;
    private int aiDecision;
    private int countNamingConvention;
    private int rarePrintCount;
    private int importCount;
    private int wrongPatternCount;
    private int exceptionHandlingCount;
    public double addAllFields() {
        double addAll = getInlineCommentCount()
                + getEmptyCodeSpaceCount()
                + getCommentCount()+getImportCount()
                +getCountNamingConvention()
                +getExceptionHandlingCount()
                +getRarePrintCount()
                +getExceptionHandlingCount()
                +getDecisionCount()
                +getWrongPatternCount();
        return addAll;
    }
    public double getHumanPercent() {
        double humanPercent = getLineNumber() - addAllFields();
        return (humanPercent / getLineNumber())*100;
    }
    public double computeAiPercent() {
        double aiPercent = addAllFields()/getLineNumber();
        return  aiPercent * 100;
    }

    public int getCommentCount() {
        return complexCommentCount;
    }

    public void setCommentCount(int commentCount) {
        this.complexCommentCount = commentCount;
    }

    public int getAiflag() {
        return aiflags;
    }

    public void setAiflag(int aiflags) {
        this.aiflags = aiflags;
    }

    public int getEmptyCodeSpaceCount() {
        return emptyCodeSpaceCount;
    }

    public void setEmptyCodeSpaceCount(int emptyCodeSpaceCount) {
        this.emptyCodeSpaceCount = emptyCodeSpaceCount;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public int getInlineCommentCount() {
        return inlineCommentCount;
    }

    public void setInlineCommentCount(int inlineCommentCount) {
        this.inlineCommentCount = inlineCommentCount;
    }

    public int getDecisionCount() {
        return decisionCount;
    }

    public void setDecisionCount(int decisionCount) {
        this.decisionCount = decisionCount;
    }

    public int getHumanDecision() {
        return humanDecision;
    }

    public void setHumanDecision(int humanDecision) {
        this.humanDecision = humanDecision;
    }

    public int getAiDecision() {
        return aiDecision;
    }

    public void setAiDecision(int aiDecision) {
        this.aiDecision = aiDecision;
    }

    public int getCountNamingConvention() {
        return countNamingConvention;
    }

    public void setCountNamingConvention(int countNamingConvention) {
        this.countNamingConvention = countNamingConvention;
    }

    public int getRarePrintCount() {
        return rarePrintCount;
    }

    public void setRarePrintCount(int rarePrintCount) {
        this.rarePrintCount = rarePrintCount;
    }

    public int getImportCount() {
        return importCount;
    }

    public void setImportCount(int importCount) {
        this.importCount = importCount;
    }

    public int getExceptionHandlingCount() {
        return exceptionHandlingCount;
    }

    public void setExceptionHandlingCount(int exceptionHandlingCount) {
        this.exceptionHandlingCount = exceptionHandlingCount;
    }

    public int getWrongPatternCount() {
        return wrongPatternCount;
    }

    public void setWrongPatternCount(int wrongPatternCount) {
        this.wrongPatternCount = wrongPatternCount;
    }
}