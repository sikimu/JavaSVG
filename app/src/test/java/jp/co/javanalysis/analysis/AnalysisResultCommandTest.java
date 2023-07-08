package jp.co.javanalysis.analysis;

import jp.co.javanalysis.phrase.Phrase;
import jp.co.javanalysis.phrase.PhraseListFactory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;

public class AnalysisResultCommandTest {

    @Test
    public void メソッド呼び出しテスト() {

        ArrayList<Phrase> phraseList = PhraseListFactory.create("a();");

        AnalysisResultCommand analysisResultCommand = new AnalysisResultCommand(phraseList.get(0));
        
        assertEquals("a ( )", analysisResultCommand.call.code);
    }
}