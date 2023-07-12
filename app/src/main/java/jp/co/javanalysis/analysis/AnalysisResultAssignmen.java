package jp.co.javanalysis.analysis;

import java.util.List;

import jp.co.javanalysis.phrase.Phrase;

public class AnalysisResultAssignmen extends AnalysisResult {

    final public AnalysisResultVariable destination;
    final public AnalysisResult command;
    String code;

    public AnalysisResultAssignmen(Phrase phrase) {

        code = phrase.toString();

        List<Phrase> split = phrase.split("=");
        destination = new AnalysisResultVariable(split.get(0));
        // 演算子が含まれている場合
        if(phrase.contains("+") || phrase.contains("-") || 
            phrase.contains("*") || phrase.contains("/")) {
            command = new AnalysisResultCalculationExpression(split.get(1));
        }
        else{
            command = new AnalysisResultCall(split.get(1));
        }
    }

    public String toString() {
        return this.code;
    }
}