package jp.co.javanalysis.analysis;

import java.util.List;

import jp.co.javanalysis.analysis.code.CommandCode;
import jp.co.javanalysis.phrase.Phrase;

public class AnalysisResultCode extends AnalysisResult {

    AnalysisResultVariable destination;
    CommandCode command;
    String code;

    public AnalysisResultCode(Phrase phrase) {

        code = phrase.toString();

        //代入文の場合
        if(phrase.contains("=")) {
            List<Phrase> split = phrase.split("=");
            destination = new AnalysisResultVariable(split.get(0));
            command = new CommandCode(split.get(1));
        } else {
            command = new CommandCode(phrase);
        }
    }

    public String toString() {
        return this.code;
    }
}