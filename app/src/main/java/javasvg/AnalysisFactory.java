package javasvg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AnalysisFactory {

  public static AnalysisResultPackage createPackage(String folderPath) throws Exception {

    // folderPath内のファイルを再帰的に取得
    List<File> fileList = getAllFiles(folderPath);

    ArrayList<AnalysisResultSource> list = new ArrayList<AnalysisResultSource>();

    // ファイル名をすべて出力
    for (int i = 0; i < fileList.size(); i++) {
      File file = fileList.get(i);
      if (file.getName().endsWith(".java")) {
        AnalysisResultSource result = AnalysisFactory.create(file);
        list.add(result);
      }
    }

    return new AnalysisResultPackage(list);
  }

  /**
   * 再帰的にファイルを取得する
   * 
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

  public static AnalysisResultSource create(File file) throws IOException {
    String source = readFileToString(file);

    return createSource(createSignatureList(source));
  }

  public static AnalysisResultSource create(String source) {
    return createSource(createSignatureList(source));
  }

  static String readFileToString(File file) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
    StringBuilder source = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
      source.append(line).append(System.lineSeparator());
    }
    reader.close();
    return source.toString();
  }

  // 文節リストを作成
  private static ArrayList<Signature> createSignatureList(String source) {
    ArrayList<Signature> signatures = new ArrayList<Signature>();

    Index index = new Index(0);
    while (true) {
      // 1文節を取得
      Signature signature = new Signature();
      signature.extract(source, index);
      if (signature.size() == 0) {
        break;
      }
      signatures.add(signature);
    }

    return signatures;
  }

  /**
   * ソースコードを解析する
   * 
   * @param source
   * @param index
   * @return
   */
  static AnalysisResultSource createSource(ArrayList<Signature> signatures) {

    // 解析結果(ソースコード)
    AnalysisResultSource jsarSource = new AnalysisResultSource();

    Index index = new Index(0);
    while (signatures.size() > index.get()) {

      Signature signature = signatures.get(index.get());
      index.increment();

      // クラス文節だった
      if (signature.contains("class")) {
        AnalysisResultClassCode code = new AnalysisResultClassCode(signature);
        // 1文節を取得
        Signature brances = signatures.get(index.get());
        if (brances.contains("{") == false) {
          throw new RuntimeException("クラスの開始ブレースがありません");
        }

        AnalysisResultClass resultClass = new AnalysisResultClass(code, AnalysisResultInBraces.create(signatures, index));
        jsarSource.add(resultClass);
      } 
      else {
        jsarSource.add(new AnalysisResultCode(signature.toString()));
      }
    }

    return jsarSource;
  }
}
