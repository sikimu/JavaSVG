package javasvg;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import javasvg.signature.Signature;
import javasvg.signature.SignaturesFactory;

public class AnalysisResultIfTest {

    @Test
    public void ifを解析する括弧あり(){
        ArrayList<Signature> signatures = SignaturesFactory.create("if(abc==c){a=1;}");
        AnalysisResultIf result = new AnalysisResultIf(signatures, new Index(0));
        

        assertEquals("abc==c", result.expression.toString());//ifの条件式
        //型がAnalysisResultInBracesであること
        assertEquals("a=1", ((AnalysisResultInBraces)result.statement).get(0).toString());
    }

    @Test
    public void ifを解析する括弧なし(){
        ArrayList<Signature> signatures = SignaturesFactory.create("if(abc==c)a=1;");
        AnalysisResultIf result = new AnalysisResultIf(signatures, new Index(0));

        assertEquals("abc==c", result.expression.toString());//ifの条件式
        //型がAnalysisResultCodeであること
        assertEquals("a=1", ((AnalysisResultCode)result.statement).code);
    }    
}
