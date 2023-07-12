package jp.co.javanalysis.analysis;

import java.util.ArrayList;
import java.util.Arrays;

import jp.co.javanalysis.phrase.Phrase;

public class AnalysisResultCalculationExpression extends AnalysisResult {

    public String code;

    public ArrayList<AnalysisResultVariable> variableList = new ArrayList<AnalysisResultVariable>();

    public AnalysisResultCalculationExpression(final Phrase phrase) {
        code = phrase.toString();

        int pos = 0;
        while (true) {
            final Phrase subPhrase = phrase.subPhrase(pos, phrase.size());
            //+-*/のいずれかで一番最初に出てくるものを位置を取得
            int index = Arrays.asList("+", "-", "*", "/").stream().mapToInt(s -> subPhrase.indexOf(s)).filter(i -> i != -1).min().orElse(-1);
            if (index == -1) {
                variableList.add(new AnalysisResultVariable(subPhrase));
                break;
            }
            variableList.add(new AnalysisResultVariable(subPhrase.subPhrase(0, index)));
            pos = index + 1;
        }
    }

    public String toString() {
        return this.code;
    }

    public AnalysisResultVariable getVariable(int i) {
        return variableList.get(i);
    }
}