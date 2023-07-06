package jp.co.javanalysis.analysis.code;

import jp.co.javanalysis.phrase.Phrase;

public class DestinationCode {

    final public String code;

    public DestinationCode(Phrase phrase) {
        code = phrase.toString();
    }
    
}
