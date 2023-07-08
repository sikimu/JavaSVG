package jp.co.javanalysis.analysis;

import jp.co.javanalysis.phrase.Phrase;

public class AnalysisResultCommand extends AnalysisResult {

    public String code;

    final public AnalysisResultCall call;

    public AnalysisResultCommand(Phrase phrase) {
        code = phrase.toString();

        if(phrase.get(1).equals("(")) {
            call = new AnalysisResultCall(phrase);
        } else {
            call = null;
        }
    }

    public String toString() {
        return this.code;
    }
}