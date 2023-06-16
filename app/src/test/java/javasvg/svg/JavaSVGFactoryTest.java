package javasvg.svg;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javasvg.AnalysisFactory;
import javasvg.AnalysisResultPackage;
import javasvg.AnalysisResultSource;

public class JavaSVGFactoryTest {
    @Test
    @DisplayName("create基本")
    void testCreate() {

        ArrayList<AnalysisResultSource> list = new ArrayList<AnalysisResultSource>();

        AnalysisResultSource sourceA = AnalysisFactory.create("class A { void a(){} }");
        AnalysisResultSource sourceB = AnalysisFactory.create("class B { void b(){} }");
        list.add(sourceA);
        list.add(sourceB);

        AnalysisResultPackage result = new AnalysisResultPackage(list);

        ArrayList<JavaSVG> svgs = JavaSVGFactory.create(result);

        assertEquals(2, svgs.size());
        assertEquals("A", svgs.get(0).code);
        assertEquals("B", svgs.get(1).code);
    }
}
