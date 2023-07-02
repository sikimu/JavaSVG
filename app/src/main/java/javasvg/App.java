package javasvg;

import java.util.ArrayList;

import javasvg.svg.JavaSVG;
import javasvg.svg.JavaSVGFactory;
import javasvg.svg.SVGCreator;
import jp.co.javanalysis.analysis.AnalysisFactory;
import jp.co.javanalysis.analysis.AnalysisResultPackage;

public class App {

    private static String folderPath = "c:\\Users\\iihit\\java\\JavaSVG";

    public static void main(String[] args) throws Exception {

        AnalysisResultPackage result = AnalysisFactory.createPackage(folderPath);

        System.out.println(String.join("\n", result.getAllClasses()));

        ArrayList<JavaSVG> svgs = JavaSVGFactory.create(result);

        SVGCreator.createSVG("C:\\Users\\iihit\\java\\output.svg", svgs);
    }
}
