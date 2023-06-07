package javasvg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class JavaStaticAnalysisFactory {
  public static JavaStaticAnalysisResultSource create(File file) throws Exception {

    BufferedReader reader = new BufferedReader(new FileReader(file));

    // 解析結果(ソースコード)
    JavaStaticAnalysisResultSource result = new JavaStaticAnalysisResultSource();

    String tempString = "";

    int i;
    boolean isFix = false;
    while ((i = reader.read()) != -1) {

      char c = (char) i;  

      // 改行は無視する
      if(c == '\n' || c == '\r'){
        continue;
      }

      // 最初の文字までは無視する
      if(c == ' ' && tempString.length() == 0){
        continue;
      }

      tempString = tempString + c;    

      if(c == ';'){
        isFix = true;
      }

      if(isFix){
        result.add(new JavaStaticAnalysisResultCode(tempString));
        tempString = "";
        isFix = false;
      }
    }

    reader.close();
    return result;
  }
}