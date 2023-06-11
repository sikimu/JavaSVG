package javasvg;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AnalysisFactoryTest {
    @Test
    public void testCreate() {
        AnalysisResultSource arSource = AnalysisFactory.create("new FileReader(file);");

        assertEquals("new FileReader", arSource.get(0).toString());
        assertEquals("(", arSource.get(1).toString());
    }
}