package javasvg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class JavaStaticAnalysisFactory {
  public static JavaStaticAnalysisResult create(File file) throws Exception {
    StringBuilder sb = new StringBuilder();
    BufferedReader reader = new BufferedReader(new FileReader(file));
    String line;
    while ((line = reader.readLine()) != null) {
      sb.append(line);
      sb.append(System.lineSeparator());
    }
    reader.close();
    return new JavaStaticAnalysisResult(sb.toString());
  }
}