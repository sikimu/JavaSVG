package javasvg;

import jp.co.javanalysis.analysis.AnalysisResultCode;
import jp.co.javanalysis.phrase.Phrase;

public class ThrowsCode{
    
    final public AnalysisResultCode arThrows;

    public ThrowsCode(Phrase signature) {
        // throwsがある場合
        arThrows = new AnalysisResultCode(signature);
    }
}
