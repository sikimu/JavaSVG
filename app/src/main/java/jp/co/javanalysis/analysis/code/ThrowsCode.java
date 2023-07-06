package jp.co.javanalysis.analysis.code;

import jp.co.javanalysis.phrase.Phrase;

public class ThrowsCode{
    
    final public String arThrows;

    public ThrowsCode(Phrase signature) {
        // throwsがある場合
        arThrows = signature.toString();
    }
}
