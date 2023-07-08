package jp.co.javanalysis.analysis;

import java.util.List;

import jp.co.javanalysis.phrase.Phrase;

public class AnalysisResultAssignmen extends AnalysisResult {

    AnalysisResultDestination destination;
    AnalysisResultCommand command;
    String code;

    public AnalysisResultAssignmen(Phrase phrase) {

        code = phrase.toString();

        List<Phrase> split = phrase.split("=");
        destination = new AnalysisResultDestination(split.get(0));
        command = new AnalysisResultCommand(split.get(1));
    }

    public String toString() {
        return this.code;
    }
}