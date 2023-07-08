package jp.co.javanalysis.analysis;

import jp.co.javanalysis.phrase.Phrase;

public class AnalysisResultCode extends AnalysisResult {

    String code;

    public AnalysisResultCode(Phrase phrase) {

        code = phrase.toString();
    }

    public String toString() {
        return this.code;
    }
}