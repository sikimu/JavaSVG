package javasvg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class AnalysisInBracesFactoryTest {

    @Test
    public void testCreateInBraces() {
        ArrayList<Signature> signatures = SignaturesFactory.create("{int a; int b;}public int c;");

        AnalysisInBracesFactory factory = new AnalysisInBracesFactory(signatures, new Index(0));
        AnalysisResultInBraces result = factory.createInBrances();

        assertEquals(4, result.size());
        assertTrue(result.get(0) instanceof AnalysisResultCode);
        assertTrue(result.get(1) instanceof AnalysisResultCode);
        assertTrue(result.get(2) instanceof AnalysisResultCode);
        assertTrue(result.get(3) instanceof AnalysisResultCode);
    }
}