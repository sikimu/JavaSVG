package javasvg.signature;

/**
 * 単語を表す
 */
public class Word {

    public final String word;
    public final Type type;

    public enum Type{
        FREE, // 自由語
    }

    public Word(String word) {
        this.word = word;
        this.type = Type.FREE;
    }
}
