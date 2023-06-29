package javasvg.signature;

import java.util.Map;
import java.util.HashMap;

/**
 * 単語を表す
 */
public class Word {

    public final String word;
    public final Type type;

    public enum Type {
        FREE, // 自由語
        CLASS, // class
        LEFT_BRACKET, // {
        RIGHT_BRACKET, // }
        LEFT_PARENTHESIS, // (
        RIGHT_PARENTHESIS, // )
        VOID, // void
        INT, // int
        STRING, // String
        DOUBLE, // double
        BOOLEAN, // boolean
        CHAR, // char
        FLOAT, // float
        LONG, // long
        BYTE, // byte
        SHORT, // short
        OBJECT, // Object
        ARRAY_LIST // ArrayList
    }

    private static final Map<String, Type> WORD_TYPE_MAP = new HashMap<>();
    static {
        WORD_TYPE_MAP.put("class", Type.CLASS);
        WORD_TYPE_MAP.put("{", Type.LEFT_BRACKET);
        WORD_TYPE_MAP.put("}", Type.RIGHT_BRACKET);
        WORD_TYPE_MAP.put("(", Type.LEFT_PARENTHESIS);
        WORD_TYPE_MAP.put(")", Type.RIGHT_PARENTHESIS);
        WORD_TYPE_MAP.put("void", Type.VOID);
        WORD_TYPE_MAP.put("int", Type.INT);
        WORD_TYPE_MAP.put("String", Type.STRING);
        WORD_TYPE_MAP.put("double", Type.DOUBLE);
        WORD_TYPE_MAP.put("boolean", Type.BOOLEAN);
        WORD_TYPE_MAP.put("char", Type.CHAR);
        WORD_TYPE_MAP.put("float", Type.FLOAT);
        WORD_TYPE_MAP.put("long", Type.LONG);
        WORD_TYPE_MAP.put("byte", Type.BYTE);
        WORD_TYPE_MAP.put("short", Type.SHORT);
        WORD_TYPE_MAP.put("Object", Type.OBJECT);
        WORD_TYPE_MAP.put("ArrayList", Type.ARRAY_LIST);
    }

    public Word(String word) {
        this.word = word;
        this.type = WORD_TYPE_MAP.getOrDefault(word, Type.FREE);
    }
}
