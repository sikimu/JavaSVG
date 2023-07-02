package javasvg.phrase;
import java.util.ArrayList;

import javasvg.Index;

public class PhraseListFactory {
  // 文節リストを作成
  public static ArrayList<Phrase> create(String source) {
    
    ArrayList<Phrase> signatures = new ArrayList<Phrase>();

    source = source.trim();

    Index index = new Index(0);
    while (index.get() < source.length()) {

      // 1文節を取得
      Phrase phrase = new Phrase(source, index);
      signatures.add(phrase);

      // 1文節の後のスペースを飛ばす
      while (index.get() < source.length()) {
        char c = source.charAt(index.get());
        if (c == ' ' || c == '\n' || c == '\t' || c == '\r' || c == ';') {
          index.increment();
        }
        else {
          break;
        }
      }
    }

    return signatures;
  }
}
