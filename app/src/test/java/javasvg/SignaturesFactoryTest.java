package javasvg;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class SignaturesFactoryTest {
    @Test
    void メソッドの引数と処理の間にスペースがある場合() {
        ArrayList<Signature> signatures = SignaturesFactory.create("() {}");

        assertEquals("(", signatures.get(0).get(0));
        assertEquals(")", signatures.get(1).get(0));
        assertEquals("{", signatures.get(2).get(0));
        assertEquals("}", signatures.get(3).get(0));
    }
}
