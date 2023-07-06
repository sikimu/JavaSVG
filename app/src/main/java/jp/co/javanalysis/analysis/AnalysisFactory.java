package jp.co.javanalysis.analysis;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javasvg.Index;
import jp.co.javanalysis.FileUtil;
import jp.co.javanalysis.phrase.Phrase;
import jp.co.javanalysis.phrase.PhraseListFactory;

public class AnalysisFactory {

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
