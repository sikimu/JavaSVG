package javasvg;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class App {

    private static String folderPath = "c:\\Users\\iihit\\java\\JavaSVG";

    public static void main(String[] args) throws Exception {

        // folderPath内のファイルを再帰的に取得
        List<File> fileList = getAllFiles(folderPath);

        // ファイル名をすべて出力
        for (int i = 0; i < fileList.size(); i++) {
            File file = fileList.get(i);
            if (file.getName().endsWith(".java")) {
                JavaStaticAnalysisResultSource result = JavaStaticAnalysisFactory.create(file);
                System.out.println(result.toString());
            }
        }

        SVGCreator.createSVG("C:\\Users\\iihit\\java\\output.svg");
    }

    /**
     * 再帰的にファイルを取得する
     * @param folderPath
     * @throws Exception
     */
    private static List<File> getAllFiles(String folderPath) throws Exception {
        List<File> fileList = new ArrayList<File>();
        File[] files = new File(folderPath).listFiles();
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            if (file.isDirectory()) {
                fileList.addAll(getAllFiles(file.getPath()));
            } else {
                fileList.add(file);
            }
        }
        return fileList;
    }
}
