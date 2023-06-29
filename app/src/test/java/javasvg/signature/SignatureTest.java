package javasvg.signature;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javasvg.Index;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

public class SignatureTest {
    @Test
    public void 一番単純な読み込み() {
        String source = "hello world";
        Index index = new Index(0);
        Signature signature = new Signature(source, index);

        assertEquals(11, index.get());
        assertEquals("hello", signature.get(0));
    }

    @Test
    public void 文節の区切り文字を個別の文節とする() {

        String source = "();{}";
        Index index = new Index(0);
        Signature signature = new Signature(source, index);

        assertEquals("(", signature.get(0));

        signature = new Signature(source, index);
        assertEquals(")", signature.get(0));

        signature = new Signature(source, index);
        assertEquals(";", signature.get(0));

        signature = new Signature(source, index);
        assertEquals("{", signature.get(0));

        signature = new Signature(source, index);
        assertEquals("}", signature.get(0));
    }

    @ParameterizedTest
    @MethodSource("個別の文節として解析するパラメータ")
    public void 個別の文節として解析する(String source, String expected) {
        Index index = new Index(0);
        Signature signature = new Signature(source, index);

        assertEquals(expected, signature.get(1));
    }
    static Stream<Arguments> 個別の文節として解析するパラメータ() {
        return Stream.of(
                Arguments.of("aaa \"a\" bbb", "\"a\""),
                Arguments.of("aaa \"\\\"\" bbb", "\"\\\"\""),

                Arguments.of("aaa 'a' bbb", "'a'"),
                Arguments.of("aaa ',' bbb", "','"),
                Arguments.of("aaa ';' bbb", "';'"),
                Arguments.of("aaa '\\'' bbb", "'\\''"),
                Arguments.of("aaa '\"' bbb", "'\"'"),
                Arguments.of("aaa '\\\\' bbb", "'\\\\'"),
                Arguments.of("aaa ' ' bbb", "' '")
                );        
    }

    @ParameterizedTest
    @MethodSource("シングルコーテーションを個別の文節として解析するパラメータ")
    void シングルコーテーションを個別の文節として解析する(String source, String expected) {
        Index index = new Index(0);
        Signature signature = new Signature(source, index);

        assertEquals(expected, signature.get(1));
    }
    static Stream<Arguments> シングルコーテーションを個別の文節として解析するパラメータ() {
        return Stream.of(
                Arguments.of("aaa 'a' bbb", "'a'"),
                Arguments.of("aaa ',' bbb", "','"),
                Arguments.of("aaa ';' bbb", "';'"),
                Arguments.of("aaa '\\'' bbb", "'\\''"),
                Arguments.of("aaa '\"' bbb", "'\"'"),
                Arguments.of("aaa '\\\\' bbb", "'\\\\'"),
                Arguments.of("aaa ' ' bbb", "' '")
                );
    }

    @Test
    public void カンマを個別の単語として解析する() {
        String source = "a,b,c";
        Index index = new Index(0);
        Signature signature = new Signature(source, index);

        assertEquals("a", signature.get(0));
        assertEquals(",", signature.get(1));
        assertEquals("b", signature.get(2));
        assertEquals(",", signature.get(3));
        assertEquals("c", signature.get(4));
    }

    @Test
    public void 複数行コメントを個別の文節として解析する() {

        String source = "pu/*aiueo*/ueo";
        Signature signature = new Signature(source, new Index(0));

        assertEquals("pu", signature.get(0));
        assertEquals("/*aiueo*/", signature.get(1));
        assertEquals("ueo", signature.get(2));
    }
}