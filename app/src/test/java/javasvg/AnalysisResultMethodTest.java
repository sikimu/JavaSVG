package javasvg;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class AnalysisResultMethodTest {

    @Test
    public void 基本的な引数ありメソッドの解析() {
        ArrayList<Signature> signatures = SignaturesFactory.create("public int a(int b){}");
        AnalysisResultMethod result = new AnalysisResultMethod(signatures, new Index(0));

        assertEquals("a", result.name);
        assertEquals("int b", result.arguments.toString());
    }
}
