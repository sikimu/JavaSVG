package javasvg;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnalysisFactoryTest {
    @Test
    public void testCreate() {
        AnalysisResultSource arSource = AnalysisFactory.create("new FileReader(file);");

        assertEquals("new FileReader", arSource.get(0).toString());
        assertEquals("(", arSource.get(1).toString());
    }

    @Test
    @DisplayName("クラスの読み込みテスト")
    public void testCreate2() {
        AnalysisResultSource arSource = AnalysisFactory.create("public class A{aaaa{bbbb}}");

        assertTrue(arSource.get(0) instanceof AnalysisResultClass);
        AnalysisResultClass arClass = (AnalysisResultClass) arSource.get(0);
        assertEquals("A", arClass.getName());

        assertEquals("aaaa", arClass.inBraces.get(0).toString());
        assertTrue(arClass.inBraces.get(1) instanceof AnalysisResultInBraces);

        AnalysisResultInBraces arInBraces = (AnalysisResultInBraces) arClass.inBraces.get(1);
        assertEquals("bbbb", arInBraces.get(0).toString());
    }
}