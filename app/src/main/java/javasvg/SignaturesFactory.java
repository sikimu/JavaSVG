package javasvg;
import java.util.ArrayList;

class SignaturesFactory {
  // 文節リストを作成
  static ArrayList<Signature> create(String source) {
    
    ArrayList<Signature> signatures = new ArrayList<Signature>();

    Index index = new Index(0);
    while (true) {
      // 1文節を取得
      Signature signature = new Signature(source, index);
      if (signature.size() == 0) {
        break;
      }
      signatures.add(signature);
    }

    return signatures;
  }
}
