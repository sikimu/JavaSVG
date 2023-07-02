package javasvg;

import javasvg.phrase.Phrase;

public class AnalysisResultCode extends AnalysisResult {

    String code;

    public AnalysisResultCode(String code) {
        this.code = code;
    }

    public AnalysisResultCode(Phrase phrase) {
        this.code = phrase.toString();
    }

    public String toString() {
        return this.code;
    }
}