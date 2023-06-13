package javasvg;

public class App {

    private static String folderPath = "c:\\Users\\iihit\\java\\JavaSVG";

    public static void main(String[] args) throws Exception {

        AnalysisResultPackage result = AnalysisFactory.createPackage(folderPath);

        System.out.println(String.join("\n", result.getAllClasses()));

        SVGCreator.createSVG("C:\\Users\\iihit\\java\\output.svg");
    }
}
