package javasvg;

import java.util.ArrayList;

import javasvg.phrase.Phrase;

public class AnalysisResultIf extends AnalysisResult {

    final public AnalysisResultCode expression;

    final public AnalysisResult statement; 

    public AnalysisResultIf(ArrayList<Phrase> signatures, Index index) {

        index.add(2);// if(を飛ばす
        this.expression = new AnalysisResultCode(signatures.get(index.get()).get(0));
        index.add(2);// )を飛ばす
        //次の処理が{なら
        if (signatures.get(index.get()).get(0).equals("{")) {
            this.statement = AnalysisResultInBraces.create(signatures, index);
        }
        //1行解析
        else{
            this.statement = new AnalysisResultCode(signatures.get(index.get()).get(0));
            index.increment();
        }
    }
}