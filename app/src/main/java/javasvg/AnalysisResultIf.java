package javasvg;

import java.util.ArrayList;

import javasvg.signature.Signature;

public class AnalysisResultIf extends AnalysisResult {

    public AnalysisResultCode expression;

    public AnalysisResultIf(ArrayList<Signature> signatures, Index index) {

        index.add(2);// if(を飛ばす
        Signature signature = signatures.get(index.get());
        this.expression = new AnalysisResultCode(signature.get(0));
        index.increment();
    }
}