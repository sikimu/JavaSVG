package jp.co.javanalysis.analysis;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import javasvg.Index;
import jp.co.javanalysis.phrase.Phrase;
import jp.co.javanalysis.phrase.PhraseListFactory;

public class AnalysisResultIfTest {

    @Test
    public void ifを解析する括弧あり(){
        ArrayList<Phrase> signatures = PhraseListFactory.create("if(abc==c){a=1;}");
        AnalysisResultIf result = new AnalysisResultIf(signatures, new Index(0));
        

        assertEquals("abc == c", result.expression.toString());//ifの条件式
        //型がAnalysisResultInBracesであること
        assertEquals("a = 1", ((AnalysisResultInBraces)result.statement).get(0).toString());
    }

    @Test
    public void ifを解析する括弧なし(){
        ArrayList<Phrase> signatures = PhraseListFactory.create("if(abc==c)a=1;");
        AnalysisResultIf result = new AnalysisResultIf(signatures, new Index(0));

        assertEquals("abc == c", result.expression.toString());//ifの条件式
        //型がAnalysisResultCodeであること
        assertEquals("a = 1", ((AnalysisResultCode)result.statement).code);
    }    
}
