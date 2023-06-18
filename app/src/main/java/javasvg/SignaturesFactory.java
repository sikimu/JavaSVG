package javasvg;
import java.util.ArrayList;

class SignaturesFactory {
  // 文節リストを作成
  static ArrayList<Signature> create(String source) {
    
    ArrayList<Signature> signatures = new ArrayList<Signature>();

    int index = 0;
    while (true) {
      // 1文節を取得
      Signature signature = new Signature();
      index = signature.extract(source, index);
      if (signature.size() == 0) {
        break;
      }
      signatures.add(signature);
    }

    return signatures;
  }
}
