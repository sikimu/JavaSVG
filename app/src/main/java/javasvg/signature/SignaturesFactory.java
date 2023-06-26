package javasvg.signature;
import java.util.ArrayList;

import javasvg.Index;

public class SignaturesFactory {
  // 文節リストを作成
  public static ArrayList<Signature> create(String source) {
    
    ArrayList<Signature> signatures = new ArrayList<Signature>();

    source = source.trim();

    Index index = new Index(0);
    while (index.get() < source.length()) {

      // 1文節を取得
      Signature signature = new Signature(source, index);
      signatures.add(signature);

      // 1文節の後のスペースを飛ばす
      while (index.get() < source.length()) {
        char c = source.charAt(index.get());
        if (c == ' ' || c == '\n' || c == '\t' || c == '\r') {
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
