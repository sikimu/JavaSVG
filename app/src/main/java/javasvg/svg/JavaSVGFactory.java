package javasvg.svg;

import java.util.ArrayList;

import javasvg.AnalysisResultPackage;

public class JavaSVGFactory {
    
    public static ArrayList<JavaSVG> create(AnalysisResultPackage result){
        ArrayList<JavaSVG> svgs = new ArrayList<JavaSVG>();

        for (String className : result.getAllClasses()) {
            svgs.add(new JavaSVG(className));
        }

        return svgs;
    }
}
