package com.rocs.code.scanner;

import com.rocs.code.scanner.app.path.FindFilePath;
import com.rocs.code.scanner.app.path.impl.FindFilePathImpl;
import com.rocs.code.scanner.data.model.Field;
import com.rocs.code.scanner.app.analyzer.AnalyzeCode;
import com.rocs.code.scanner.app.analyzer.impl.AnalyzeCodeImpl;
import java.nio.file.Path;
import java.util.List;


public class CodeScanner {
    public static void main(String[] args) {
        AnalyzeCode codescanner = new AnalyzeCodeImpl();
        FindFilePath filePath = new FindFilePathImpl();
        List<Path> paths = filePath.findPath();
        for(Path path:paths){
            Field field = new Field();
            String fileName = String.valueOf(path);
            System.out.println("-----------------------------------------------------------------------------------");
            System.out.println(path);
            System.out.println("Total Number of inline comments    : "+codescanner.countTotalInLineComments(fileName ,field));
            System.out.println("Total Number of Complex Comments   : "+codescanner.countComplexComments(fileName,field));
            System.out.println("Total empty space                  : "+codescanner.countTotalCodeSpace(fileName,field));
            System.out.println("Consecutive empty space occurrence : "+codescanner.countConsecutiveCodeSpace(fileName,field));
            System.out.println("Total Number of decision           : "+codescanner.countTotalDecision(fileName,field));
            System.out.println("Number of try catch                : "+codescanner.countTryCatch(fileName,field));
            System.out.println("Generic naming convention          : "+codescanner.countGenericNamingConvention(fileName,field));
            System.out.println("Generic imports                    : "+codescanner.countImport(fileName,field));
            System.out.println("Rare logs                          : "+codescanner.countRarePrint(fileName,field));
            System.out.println("Wrong code pattern                 : "+codescanner.countMultiLineCommentFormat(fileName,field));
            System.out.println("Total Number of lines              : "+filePath.findAllLines(fileName,field));
            if (field.computeAiPercent() >= field.getHumanPercent()){
                System.out.println("-----------------------------------------------------------------------------------");
                System.out.println(field.computeAiPercent()+"% Chance of AI");
                System.out.println(field.getHumanPercent()+"% Chance of HUMAN");
                System.out.println("Majority of the code found is suspected as AI generated ");
                System.out.println("-----------------------------------------------------------------------------------");
            }else if(field.computeAiPercent() >= 30){
                System.out.println("-----------------------------------------------------------------------------------");
                System.out.println(field.computeAiPercent()+"% Chance of AI");
                System.out.println(field.getHumanPercent()+"% Chance of HUMAN");
                System.out.println("Chance of AI generated code is High");
                System.out.println("-----------------------------------------------------------------------------------");
            }else{
                System.out.println("-----------------------------------------------------------------------------------");
                System.out.println(field.computeAiPercent()+"% Chance of AI");
                System.out.println(field.getHumanPercent()+"% Chance of HUMAN");
                System.out.println("Chance of AI generated code is Low");
                System.out.println("-----------------------------------------------------------------------------------");
            }
            System.out.println("Minor update");
        }
    }
}