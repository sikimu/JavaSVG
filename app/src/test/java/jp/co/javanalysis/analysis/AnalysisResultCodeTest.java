package jp.co.javanalysis.analysis;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import jp.co.javanalysis.phrase.Phrase;
import jp.co.javanalysis.phrase.PhraseListFactory;

public class AnalysisResultCodeTest {

    @Test
    public void 代入なし() {
        ArrayList<Phrase> phraseList = PhraseListFactory.create("aaa(a + b);");
        Phrase phrase = phraseList.get(0);
        AnalysisResultCode result = new AnalysisResultCode(phrase);
        assertEquals("aaa ( a + b )", result.command.toString());
    }
}