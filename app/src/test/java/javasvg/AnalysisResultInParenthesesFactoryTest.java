package javasvg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class AnalysisResultInParenthesesFactoryTest {

    //CreateInParenthesesの基本的なテスト
    @Test
    public void testCreateInParentheses() {
        ArrayList<Signature> signatures = SignaturesFactory.create("(int a, int b, int c)");
        AnalysisResultInParenthesesFactory factory = new AnalysisResultInParenthesesFactory(signatures, 0);
        AnalysisResultInParentheses result = factory.createInParentheses();

        assertEquals(1, result.size());
        assertTrue(result.get(0) instanceof AnalysisResultCode);
        assertEquals(3, factory.getFixIndex());
    }
}