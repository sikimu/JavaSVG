package javasvg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AnalysisFactory {
  public static AnalysisResultSource create(File file) throws IOException {
    String source = readFileToString(file);
    return createSource(source, 0);
  }

  public static AnalysisResultSource create(String source) {
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

    while (true) {
      // 1文節を取得
      Signature signature = new Signature();
      index = signature.extract(source, index);
      if(signature.size() == 0){
        break;
      }
      
      // クラス文節だった
      if(signature.contains("class")){
        AnalysisResultCode code = new AnalysisResultCode(signature.toString());
        // 1文節を取得
        Signature brances = new Signature();
        index = brances.extract(source, index);
        if (brances.contains("{") == false) {
          throw new RuntimeException("クラスの開始ブレースがありません");
        }
        AnalysisResultInBraces inBraces = new AnalysisResultInBraces(createInBrances(source, index));
        AnalysisResultClass resultClass = new AnalysisResultClass(code, inBraces);
        jsarSource.add(resultClass);
      }
      else{
        jsarSource.add(new AnalysisResultCode(signature.toString()));
      }
    }

    return jsarSource;
  }

  private static ArrayList<AnalysisResult> createInBrances(String source, int index) {

    ArrayList<AnalysisResult> list = new ArrayList<AnalysisResult>();

    while (true) {
      // 1文節を取得
      Signature signature = new Signature();
      index = signature.extract(source, index);

      if (signature.contains("{")) {
        AnalysisResultInBraces inBraces = new AnalysisResultInBraces(createInBrances(source, index));
        list.add(inBraces);
      }
      else if (signature.contains("}")) {
        break;
      } 
      else{
        list.add(new AnalysisResultCode(signature.toString()));
      }
    }

    return list;
  }
}
