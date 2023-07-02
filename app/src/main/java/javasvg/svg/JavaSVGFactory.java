package javasvg.svg;

import java.util.ArrayList;

import jp.co.javanalysis.analysis.AnalysisResultPackage;

public class JavaSVGFactory {
    
    public static ArrayList<JavaSVG> create(AnalysisResultPackage result){
        ArrayList<JavaSVG> svgs = new ArrayList<JavaSVG>();

        for (String className : result.getAllClasses()) {
            svgs.add(new JavaSVG(className));
        }

        return svgs;
    }
}
