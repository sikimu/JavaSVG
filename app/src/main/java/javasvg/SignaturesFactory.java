package javasvg;
import java.util.ArrayList;

class SignaturesFactory {
  // 文節リストを作成
  static ArrayList<Signature> create(String source) {
    
    ArrayList<Signature> signatures = new ArrayList<Signature>();

    Index index = new Index(0);
    while (index.get() < source.length()) {

      // 1文節を取得
      Signature signature = new Signature(source, index);
      signatures.add(signature);

      // 空白や改行は読み飛ばす
      while(index.get() < source.length()){
        char c = source.charAt(index.get());
        if(c == ' ' || c == '\n' || c == '\t' || c == '\r'){
          index.increment();
        }
        else{
          break;
        }
      }
    }

    return signatures;
  }
}
