package jp.co.javanalysis.analysis;

import jp.co.javanalysis.phrase.Phrase;

public class AnalysisResultThrows{
    
    final public String arThrows;

    public AnalysisResultThrows(Phrase signature) {
        // throwsがある場合
        arThrows = signature.toString();
    }
}
