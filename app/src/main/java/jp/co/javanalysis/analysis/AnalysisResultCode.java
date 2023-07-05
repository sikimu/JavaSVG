package jp.co.javanalysis.analysis;

import java.util.List;

import jp.co.javanalysis.phrase.Phrase;

public class AnalysisResultCode extends AnalysisResult {

    String destination;
    final public String command;
    String code;

    public AnalysisResultCode(Phrase phrase) {

        code = phrase.toString();

        //代入文の場合
        if(phrase.contains("=")) {
            List<Phrase> split = phrase.split("=");
            destination = split.get(0).toString();
            command = split.get(1).toString();
        } else {
            command = phrase.toString();
        }
    }

    public String toString() {
        return this.code;
    }
}