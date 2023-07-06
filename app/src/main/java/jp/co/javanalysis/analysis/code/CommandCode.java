package jp.co.javanalysis.analysis.code;

import jp.co.javanalysis.phrase.Phrase;

/**
 * 代入文の右辺のコード(equalがない場合は全体)
 */
public class CommandCode {

    final public String code;

    public CommandCode(Phrase phrase) {
        this.code = phrase.toString();
    }
}
