package jp.co.javanalysis.analysis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import javasvg.Index;
import jp.co.javanalysis.phrase.Phrase;
import jp.co.javanalysis.phrase.PhraseListFactory;

public class AnalysisResultInParenthesesFactoryTest {

    //CreateInParenthesesの基本的なテスト
    @Test
    public void testCreateInParentheses() {
        ArrayList<Phrase> signatures = PhraseListFactory.create("(int a, int b, int c)");
        AnalysisResultInParentheses result = AnalysisResultInParentheses.create(signatures, new Index(0));

        assertEquals(1, result.size());
        assertTrue(result.get(0) instanceof AnalysisResultCode);
    }
}