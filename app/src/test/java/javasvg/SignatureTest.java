package javasvg;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;

public class SignatureTest {
    @Test
    @DisplayName("単純なextractのテスト")
    public void testExtract() {
        Signature signature = new Signature();

        String source = "hello world";
        int index = signature.extract(source, 0);
        
        assertEquals(11, index);
        assertEquals("hello", signature.get(0));
    }

    @Test
    @DisplayName("クラスが含まれているもののテスト")
    public void testExtract2() {
        Signature signature = new Signature();

        String source = "public class Hello {aaaaaaaaaaaaaaaaaaa}";
        int index = signature.extract(source, 0);
        
        assertEquals(19, index);
        assertEquals(3, signature.size());
        assertEquals("public", signature.get(0));
        assertEquals("class", signature.get(1));
        assertEquals("Hello", signature.get(2));
    }    

    @Test
    @DisplayName("();をそれぞれ解析されることのテスト")
    public void testExtract3() {
        Signature signature = new Signature();

        String source = "e);";
        int index = signature.extract(source, 0);
        
        assertEquals(1, index);
        assertEquals(1, signature.size());
        assertEquals("e", signature.get(0));

        index = signature.extract(source, index);
        assertEquals(2, index);
        assertEquals(1, signature.size());
        assertEquals(")", signature.get(0));     
        
        index = signature.extract(source, index);
        assertEquals(3, index);
        assertEquals(1, signature.size());
        assertEquals(";", signature.get(0));          
    }        

    @Test
    @DisplayName("ダブルコーテーションが含まれているもののテスト")
    public void testExtract4() {
        Signature signature = new Signature();

        String source = "public \"aaa\" aiueo";
        int index = signature.extract(source, 0);

        assertEquals(18, index);
        assertEquals("public", signature.get(0));
        assertEquals("\"aaa\"", signature.get(1));
        assertEquals("aiueo", signature.get(2));
    }    

    @Test
    @DisplayName("/**/が含まれているもののテスト")
    public void testExtract5() {
        Signature signature = new Signature();

        String source = "pu/*aiueo*/ueo";
        signature.extract(source, 0);

        assertEquals("pu", signature.get(0));
        assertEquals("/*aiueo*/", signature.get(1));
        assertEquals("ueo", signature.get(2));
    }   
}