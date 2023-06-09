package javasvg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class AnalysisFactory {
  public static AnalysisResultSource create(File file) throws IOException {
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

  static AnalysisResultSource createSource(String source, int index){

    // 解析結果(ソースコード)
    AnalysisResultSource jsarSource = new AnalysisResultSource();

    // 1文節を取得
    while (index < source.length()) {
      Signature signature = new Signature();
      index = signature.extract(source, index);
      // クラス文節だった
      if(signature.contains("class")){
        AnalysisResultCode code = new AnalysisResultCode(signature.toString());
        AnalysisResultClass resultClass = new AnalysisResultClass(code);
        jsarSource.add(resultClass);
        index++;
      }
      else{
        jsarSource.add(new AnalysisResultCode(signature.toString()));
        index++;
      }
    }

    return jsarSource;
  }
}
