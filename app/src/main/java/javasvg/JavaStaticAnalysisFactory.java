package javasvg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JavaStaticAnalysisFactory {
  public static JavaStaticAnalysisResultSource create(File file) throws IOException {
    String source = readFileToString(file);
    return createSource(source, 0);
  }

  static String readFileToString(File file) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(file));
    StringBuilder source = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
      source.append(line).append(System.lineSeparator());
    }
    reader.close();
    return source.toString();
  }

  static JavaStaticAnalysisResultSource createSource(String source, int index){

    // 解析結果(ソースコード)
    JavaStaticAnalysisResultSource jsarSource = new JavaStaticAnalysisResultSource();

    // 1文節を取得
    Signature signature = new Signature();
    while (index < source.length()) {
      index = signature.extract(source, index) + 1;
      jsarSource.add(new JavaStaticAnalysisResultCode(signature.toString()));
    }

    return jsarSource;
  }
}
