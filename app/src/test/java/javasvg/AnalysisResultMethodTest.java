package javasvg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import jp.co.javanalysis.phrase.Phrase;
import jp.co.javanalysis.phrase.PhraseListFactory;

public class AnalysisResultMethodTest {

    @Test
    public void 基本的な引数ありメソッドの解析() {
        ArrayList<Phrase> signatures = PhraseListFactory.create("public int a(int b){}");
        AnalysisResultMethod result = new AnalysisResultMethod(signatures, new Index(0));

        assertEquals("a", result.name);
        assertEquals("int b", result.arguments.toString());
    }

    @Test
    public void if文の入ったメソッドの解析() {
        ArrayList<Phrase> signatures = PhraseListFactory.create("public int a(int b){if(b == 0){return 0;}return 1;}");
        AnalysisResultMethod result = new AnalysisResultMethod(signatures, new Index(0));

        assertEquals("a", result.name);
        assertEquals("int b", result.arguments.toString());
        //メソッドの1コマンド目はAnalysisResultIfのインスタンス
        assertTrue(result.inBraces.get(0) instanceof AnalysisResultIf);
        
    }
}
