package javasvg;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SignatureTest {
    @Test
    public void testExtract() {
        Signature signature = new Signature();

        String source = "hello world";
        int index = signature.extract(source, 0);
        
        assertEquals(11, index);
        assertEquals("hello", signature.get(0));
    }
}