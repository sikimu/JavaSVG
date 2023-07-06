package jp.co.javanalysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

  /**
   * 再帰的にファイルを取得する
   * 
   * @param folderPath
   * @throws Exception
   */
  public static List<File> getAllFiles(String folderPath) throws Exception {
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

  /**
   * ファイルを読み込む
   * 
   * @param file
   * @return
   * @throws IOException
   */
  public static String readFileToString(File file) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
    StringBuilder source = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
      source.append(line).append(System.lineSeparator());
    }
    reader.close();
    return source.toString();
  }  
}
