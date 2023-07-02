package javasvg;

import javasvg.phrase.Phrase;

public class AnalysisResultThrows extends AnalysisResult{
    
    final public AnalysisResultCode arThrows;

    public AnalysisResultThrows(Phrase signature) {

        if (signature.contains("throws") == false) {
            throw new IllegalArgumentException("throwsがありません");
        }      

        // throwsがある場合
        arThrows = new AnalysisResultCode(signature);
    }
}
