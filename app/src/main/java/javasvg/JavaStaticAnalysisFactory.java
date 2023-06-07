package javasvg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class JavaStaticAnalysisFactory {
  public static JavaStaticAnalysisResult create(File file) throws Exception {

    BufferedReader reader = new BufferedReader(new FileReader(file));
 
    // 一ブロック分ごとに文字列を格納するリスト
    ArrayList<String> blockList = new ArrayList<String>();

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
        blockList.add(tempString);
        tempString = "";
        isFix = false;
      }
    }

    reader.close();
    return new JavaStaticAnalysisResult(String.join("\r\n", blockList));
  }
}