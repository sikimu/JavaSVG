package javasvg;

import jp.co.javanalysis.phrase.Phrase;

public class AnalysisResultCode extends AnalysisResult {

    String code;

    public AnalysisResultCode(Phrase phrase) {
        this.code = phrase.toString();
    }

    public String toString() {
        return this.code;
    }
}