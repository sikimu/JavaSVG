package jp.co.javanalysis.analysis;

import jp.co.javanalysis.phrase.Phrase;

public class AnalysisResultCall extends AnalysisResult {

    public String code;

    public AnalysisResultCall(Phrase phrase) {
        code = phrase.toString();
    }

    public String toString() {
        return this.code;
    }
}