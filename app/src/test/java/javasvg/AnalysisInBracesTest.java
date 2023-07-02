package javasvg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import javasvg.phrase.Phrase;
import javasvg.phrase.PhraseListFactory;

public class AnalysisInBracesTest {

    @Test
    public void 基本テスト() {
        ArrayList<Phrase> signatures = PhraseListFactory.create("{int a; int b;}public int c;");

        AnalysisResultInBraces result = AnalysisResultInBraces.create(signatures, new Index(0));

        assertEquals(2, result.size());
        assertTrue(result.get(0) instanceof AnalysisResultCode);
        assertTrue(result.get(1) instanceof AnalysisResultCode);
    }
}