package javasvg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import javasvg.signature.Signature;
import javasvg.signature.SignaturesFactory;

public class AnalysisInBracesTest {

    @Test
    public void testCreateInBraces() {
        ArrayList<Signature> signatures = SignaturesFactory.create("{int a; int b;}public int c;");

        AnalysisResultInBraces result = AnalysisResultInBraces.create(signatures, new Index(0));

        assertEquals(4, result.size());
        assertTrue(result.get(0) instanceof AnalysisResultCode);
        assertTrue(result.get(1) instanceof AnalysisResultCode);
        assertTrue(result.get(2) instanceof AnalysisResultCode);
        assertTrue(result.get(3) instanceof AnalysisResultCode);
    }
}