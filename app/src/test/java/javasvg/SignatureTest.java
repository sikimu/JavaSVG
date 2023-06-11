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
        int index = signature.extract(source, 1);
        
        assertEquals(2, index);
        assertEquals(1, signature.size());
        assertEquals(")", signature.get(0));
    }        
}