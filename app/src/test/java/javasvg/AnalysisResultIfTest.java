package javasvg;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import javasvg.signature.Signature;
import javasvg.signature.SignaturesFactory;

public class AnalysisResultIfTest {

    @Test
    public void ifを解析する(){
        ArrayList<Signature> signatures = SignaturesFactory.create("if(abc==c){}");
        AnalysisResultIf result = new AnalysisResultIf(signatures, new Index(0));

        assertEquals("abc==c", result.expression.toString());//ifの条件式
    }
}
