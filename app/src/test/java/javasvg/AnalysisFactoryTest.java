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
    public void createClass() {
        AnalysisResultSource arSource = AnalysisFactory.create("public class A{int aaaa(int ccc){bbbb}}");

        assertTrue(arSource.get(0) instanceof AnalysisResultClass);
        AnalysisResultClass arClass = (AnalysisResultClass) arSource.get(0);
        assertEquals("A", arClass.name);

        assertTrue(arClass.inBraces.get(0) instanceof AnalysisResultMethod);
        AnalysisResultMethod arMethod = (AnalysisResultMethod) arClass.inBraces.get(0);
        assertEquals("aaaa", arMethod.name);
        assertEquals("int ccc", arMethod.arguments.toString());
        assertEquals("bbbb", arMethod.inBraces.get(0).toString());
    }    

    @Test
    public void 途中にコメントの混じったクラスの読み込み() {
        AnalysisResultSource arSource = AnalysisFactory.create("public class A//aiueo\n{int aaaa(int ccc){bbbb}}");

        assertTrue(arSource.get(0) instanceof AnalysisResultClass);
        AnalysisResultClass arClass = (AnalysisResultClass) arSource.get(0);
        assertEquals("A", arClass.name);

        assertTrue(arClass.inBraces.get(0) instanceof AnalysisResultMethod);
        AnalysisResultMethod arMethod = (AnalysisResultMethod) arClass.inBraces.get(0);
        assertEquals("aaaa", arMethod.name);
        assertEquals("int ccc", arMethod.arguments.toString());
        assertEquals("bbbb", arMethod.inBraces.get(0).toString());
    }        

    @Test
    public void スロー付きメソッドのあるクラスの読み込み(){
        AnalysisResultSource arSource = AnalysisFactory.create("public class A{int aaaa(int ccc) throws Exception{bbbb}}");

        assertTrue(arSource.get(0) instanceof AnalysisResultClass);
        AnalysisResultClass arClass = (AnalysisResultClass) arSource.get(0);
        assertEquals("A", arClass.name);
        
        assertTrue(arClass.inBraces.get(0) instanceof AnalysisResultMethod);
        AnalysisResultMethod arMethod = (AnalysisResultMethod) arClass.inBraces.get(0);
        assertEquals("aaaa", arMethod.name);

        assertEquals("int ccc", arMethod.arguments.toString());
        assertEquals("bbbb", arMethod.inBraces.get(0).toString());
    }
}