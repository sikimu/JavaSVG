package jp.co.javanalysis.analysis;

import java.util.List;

import jp.co.javanalysis.phrase.Phrase;

public class AnalysisResultAssignmen extends AnalysisResult {

    final public AnalysisResultVariable destination;
    final public AnalysisResultCall command;
    String code;

    public AnalysisResultAssignmen(Phrase phrase) {

        code = phrase.toString();

        List<Phrase> split = phrase.split("=");
        destination = new AnalysisResultVariable(split.get(0));
        command = new AnalysisResultCall(split.get(1));
    }

    public String toString() {
        return this.code;
    }
}