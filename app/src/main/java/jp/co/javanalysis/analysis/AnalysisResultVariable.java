package jp.co.javanalysis.analysis;

import jp.co.javanalysis.phrase.Phrase;

public class AnalysisResultVariable {

    final public String code;

    public AnalysisResultVariable(Phrase phrase) {
        code = phrase.toString();
    }
    
}
