package javasvg;

import javasvg.phrase.Phrase;

public class ThrowsCode{
    
    final public AnalysisResultCode arThrows;

    public ThrowsCode(Phrase signature) {

        if (signature.contains("throws") == false) {
            throw new IllegalArgumentException("throwsがありません");
        }      

        // throwsがある場合
        arThrows = new AnalysisResultCode(signature);
    }
}
