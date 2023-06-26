package javasvg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import javasvg.signature.Signature;
import javasvg.signature.SignaturesFactory;

public class AnalysisResultInParenthesesFactoryTest {

    //CreateInParenthesesの基本的なテスト
    @Test
    public void testCreateInParentheses() {
        ArrayList<Signature> signatures = SignaturesFactory.create("(int a, int b, int c)");
        AnalysisResultInParentheses result = AnalysisResultInParentheses.create(signatures, new Index(0));

        assertEquals(1, result.size());
        assertTrue(result.get(0) instanceof AnalysisResultCode);
    }
}