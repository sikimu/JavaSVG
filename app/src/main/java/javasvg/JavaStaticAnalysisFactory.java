package javasvg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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

    // 1文節の文字列を取得
    ArrayList<String> wordList = extractWordList(source, index);

    jsarSource.add(new JavaStaticAnalysisResultCode(wordList.toString()));

    return jsarSource;
  }

  static ArrayList<String> extractWordList(String source, int index) {

    ArrayList<String> wordList = new ArrayList<String>();

    String word = "";
    while (index < source.length()) {
      char c = source.charAt(index);
      if (c == ' ' || c == '\n' || c == '\t' || c == '\r') {
        if (word.length() > 0) {
          wordList.add(word);
          word = "";
        }
      } else if (c == '(' || c == ')' || c == '{' || c == '}' || c == ';') {
        if (word.length() > 0) {
          wordList.add(word);
        }
        break;
      } else {
        word += c;
      }
      index++;
    }
    return wordList;
  }
}