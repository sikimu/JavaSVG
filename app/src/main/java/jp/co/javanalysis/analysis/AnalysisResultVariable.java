package jp.co.javanalysis.analysis;

import jp.co.javanalysis.phrase.Phrase;

/**
 * 値
 */
public class AnalysisResultVariable {

    final public String code;

    public AnalysisResultVariable(Phrase phrase) {
        code = phrase.toString();
    }
    
    public String toString() {
        return this.code;
    }
}
