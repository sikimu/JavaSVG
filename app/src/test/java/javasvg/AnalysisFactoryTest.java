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
    public void testClassCreate() {
        AnalysisResultSource arSource = AnalysisFactory.create("public class A{aaaaaa}");

        assertTrue(arSource.get(0) instanceof AnalysisResultClass);
        AnalysisResultClass arClass = (AnalysisResultClass) arSource.get(0);
        assertEquals("A", arClass.name);

        assertEquals("aaaaaa", arClass.inBraces.get(0).toString());
    }

    @Test
    @DisplayName("クラスの読み込みテスト")
    public void testClassCreate2() {
        AnalysisResultSource arSource = AnalysisFactory.create("public class A{aaaa(int ccc){bbbb}}");

        assertTrue(arSource.get(0) instanceof AnalysisResultClass);
        AnalysisResultClass arClass = (AnalysisResultClass) arSource.get(0);
        assertEquals("A", arClass.name);

        assertEquals("aaaa", arClass.inBraces.get(0).toString());

        assertTrue(arClass.inBraces.get(2) instanceof AnalysisResultInBraces);
        AnalysisResultInBraces arInBraces = (AnalysisResultInBraces) arClass.inBraces.get(2);
        assertEquals("bbbb", arInBraces.get(0).toString());
    }    
}