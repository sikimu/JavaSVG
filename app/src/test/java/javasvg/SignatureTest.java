package javasvg;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void ダブルコーテーションを個別の文節として解析する() {
        String source = "public \"aaa\" aiueo";
        Index index = new Index(0);
        Signature signature = new Signature(source, index);

        assertEquals(18, index.get());
        assertEquals("public", signature.get(0));
        assertEquals("\"aaa\"", signature.get(1));
        assertEquals("aiueo", signature.get(2));
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