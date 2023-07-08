package jp.co.javanalysis.analysis;

import jp.co.javanalysis.phrase.Phrase;

public class AnalysisResultDestination {

    final public String code;

    public AnalysisResultDestination(Phrase phrase) {
        code = phrase.toString();
    }
    
}
