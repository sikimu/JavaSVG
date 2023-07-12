package jp.co.javanalysis.analysis;

import jp.co.javanalysis.phrase.Phrase;
import jp.co.javanalysis.phrase.PhraseListFactory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;

public class AnalysisResultCalculationExpressionTest {

    @Test
    public void 基本テスト() {

        ArrayList<Phrase> phraseList = PhraseListFactory.create("a+b;");

        AnalysisResultCalculationExpression analysisResultCalculationExpression = new AnalysisResultCalculationExpression(phraseList.get(0));

        assertEquals("a", analysisResultCalculationExpression.getVariable(0).toString());
        assertEquals("b", analysisResultCalculationExpression.getVariable(1).toString());
    }
}