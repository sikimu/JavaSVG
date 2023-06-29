package javasvg.signature;

/**
 * 単語を表す
 */
public class Word {

    public final String word;
    public final Type type;

    public enum Type{
        FREE, // 自由語
        CLASS, // class
    }

    public Word(String word) {
        this.word = word;

        // 単語の種類を判定する
        if(word.equals("class")){
            type = Type.CLASS;
        }
        else{
            type = Type.FREE;
        }
    }
}
