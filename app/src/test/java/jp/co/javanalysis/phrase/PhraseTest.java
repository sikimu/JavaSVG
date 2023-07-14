package jp.co.javanalysis.phrase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import javasvg.Index;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class PhraseTest {
    @Test
    public void 一番単純な読み込み() {
        String source = "hello world";
        Index index = new Index(0);
        Phrase signature = new Phrase(source, index);

        assertEquals(11, index.get());
        assertEquals("hello", signature.get(0));
    }

    @Test
    public void クラス形状(){
        String source = "public class A{aaaaaa}";
        Index index = new Index(0);
        Phrase signature = new Phrase(source, index);

        assertEquals("public", signature.get(0));
        assertEquals("class", signature.get(1));
        assertEquals("A", signature.get(2));
    }

    @Test
    public void 文節の区切り文字を個別の文節とする() {

        String source = "{}";
        Index index = new Index(0);

        Phrase signature = new Phrase(source, index);
        assertEquals("{", signature.get(0));

        Phrase signature2 = new Phrase(source, index);
        assertEquals("}", signature2.get(0));
    }

    @ParameterizedTest
    @MethodSource("個別の文節として解析するパラメータ")
    public void 個別の文節として解析する(String source, String expected) {
        Index index = new Index(0);
        Phrase signature = new Phrase(source, index);

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
        Phrase signature = new Phrase(source, index);

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
        Phrase signature = new Phrase(source, index);

        assertEquals("a", signature.get(0));
        assertEquals(",", signature.get(1));
        assertEquals("b", signature.get(2));
        assertEquals(",", signature.get(3));
        assertEquals("c", signature.get(4));
    }

    @Test
    public void 正規表現を使ったstartsWith(){
        List<String> WORDS = Arrays.asList(",", "!=");
        String regex = String.join("|", WORDS);

        String source = "!=aaaaa";

        assertTrue(source.matches("^(" + regex + ").*"));
    }

    @ParameterizedTest
    @CsvSource({
        "1+1, 1, +, 1",
        "1-1, 1, -, 1",
        "1*1, 1, *, 1",
        "1/1, 1, /, 1",
        "1%1, 1, %, 1",
        "1=1, 1, =, 1",
        "1==1, 1, ==, 1",
        "1!=1, 1, !=, 1",
        "1<1, 1, <, 1",
        "1<=1, 1, <=, 1",
        "1>1, 1, >, 1",
        "1>=1, 1, >=, 1",
        "1&&1, 1, &&, 1",
        "1||1, 1, ||, 1",
        "1&1, 1, &, 1",
        "1|1, 1, |, 1",
        "1^1, 1, ^, 1",
        "1<<1, 1, <<, 1",
        "1>>1, 1, >>, 1",
        "1>>>1, 1, >>>, 1",
        "1+=1, 1, +=, 1",
        "1-=1, 1, -=, 1",
        "1*=1, 1, *=, 1",
        "1/=1, 1, /=, 1",
        "1%=1, 1, %=, 1",
        "1&=1, 1, &=, 1",
        "1|=1, 1, |=, 1",
        "1^=1, 1, ^=, 1",
        "1<<=1, 1, <<=, 1",
        "1>>=1, 1, >>=, 1",
        "1>>>=1, 1, >>>=, 1",
        "1++1, 1, ++, 1",
        "1--1, 1, --, 1",
    })
    public void 演算子を個別の単語として解析する(String source, String expected1, String expected2, String expected3) {
        Index index = new Index(0);
        Phrase signature = new Phrase(source, index);

        assertEquals(expected1, signature.get(0));
        assertEquals(expected2, signature.get(1));
        assertEquals(expected3, signature.get(2));
    }

    @Test
    public void 複数行コメントを個別の文節として解析する() {

        String source = "pu/*aiueo*/ueo";
        Phrase signature = new Phrase(source, new Index(0));

        assertEquals("pu", signature.get(0));
        assertEquals("ueo", signature.get(1));
        assertEquals("/*aiueo*/", signature.getComment(0));
    }

    @Test
    public void コメントを個別の文節として解析する(){
            
            String source = "pu//aiueo\r\nueo";
            Phrase signature = new Phrase(source, new Index(0));
    
            assertEquals("pu", signature.get(0));
            assertEquals("ueo", signature.get(1));
            assertEquals("//aiueo", signature.getComment(0));
    }

    @Test
    public void equalも単語として解析する(){
            
            String source = "pu=aiueo";
            Phrase signature = new Phrase(source, new Index(0));
    
            assertEquals("pu", signature.get(0));
            assertEquals("=", signature.get(1));
            assertEquals("aiueo", signature.get(2));
    }

    @Test
    public void splitのテスト(){
        String source = "aaa=bbb=ccc";
        Phrase phrase = new Phrase(source, new Index(0));

        List<Phrase> list = phrase.split("=");
        assertEquals("aaa", list.get(0).get(0));
        assertEquals("bbb", list.get(1).get(0));
        assertEquals("ccc", list.get(2).get(0));
    }

    @Test
    public void indexofのテスト(){
        String source = "public int a(int b){}";
        Phrase phrase = new Phrase(source, new Index(0));

        assertEquals(3, phrase.indexOf("("));
        assertEquals(6, phrase.indexOf(")"));
    }

    @Test
    public void subPhraseのテスト(){
        String source = "public int a(int b){}";
        Phrase phrase = new Phrase(source, new Index(0));

        assertEquals("int b", phrase.subPhrase(4, 6).toString());
    }
}