package jp.co.javanalysis.analysis;

import jp.co.javanalysis.phrase.Phrase;

public class AnalysisResultCommand extends AnalysisResult {

    public String code;

    public AnalysisResultCommand(Phrase phrase) {
        code = phrase.toString();
    }

    public String toString() {
        return this.code;
    }
}