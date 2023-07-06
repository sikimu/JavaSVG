package jp.co.javanalysis.analysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javasvg.Index;
import jp.co.javanalysis.FileUtil;
import jp.co.javanalysis.phrase.Phrase;
import jp.co.javanalysis.phrase.PhraseListFactory;

public class AnalysisFactory {

  public static AnalysisResultPackage createPackage(String folderPath) throws Exception {

    // folderPath内のファイルを再帰的に取得
    List<File> fileList = FileUtil.getAllFiles(folderPath);

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

  public static AnalysisResultSource create(File file) throws IOException {
    String source = FileUtil.readFileToString(file);
    return create(source);
  }

  public static AnalysisResultSource create(String source) {
    return createSource(PhraseListFactory.create(source));
  }

  /**
   * ソースコードを解析する
   * 
   * @param source
   * @param index
   * @return
   */
  static AnalysisResultSource createSource(ArrayList<Phrase> signatures) {

    // 解析結果(ソースコード)
    AnalysisResultSource jsarSource = new AnalysisResultSource();

    Index index = new Index(0);
    while (signatures.size() > index.get()) {

      Phrase phrase = signatures.get(index.get());

      // クラス文節だった
      if (phrase.contains("class")) {
        AnalysisResultClass resultClass = new AnalysisResultClass(signatures, index);
        jsarSource.add(resultClass);
      } 
      else {
        jsarSource.add(new AnalysisResultCode(phrase));
        index.increment();
      }
    }

    return jsarSource;
  }
}
