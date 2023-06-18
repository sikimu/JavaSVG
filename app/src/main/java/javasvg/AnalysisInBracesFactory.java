package javasvg;

import java.util.ArrayList;

class AnalysisInBracesFactory {

    private ArrayList<AnalysisResult> list;
    
    public AnalysisInBracesFactory(ArrayList<Signature> signatures, Index index) {
        this.list = new ArrayList<AnalysisResult>();

        Signature signature = signatures.get(index.get());

        if (signature.contains("{") == false) {
            throw new IllegalArgumentException("開始括弧がありません");
        }
        index.increment();

        while (signatures.size() > index.get()) {
            signature = signatures.get(index.get());

            if (signature.contains("{")) {
                AnalysisInBracesFactory factory = new AnalysisInBracesFactory(signatures, index);
                list.add(factory.createInBrances());
            } 
            else if (signature.contains("(")){
                AnalysisResultInParenthesesFactory factory = new AnalysisResultInParenthesesFactory(signatures, index);
                list.add(factory.createInParentheses());
            } else if (signature.contains("}")) {
                index.increment();
                break;
            } else {
                list.add(new AnalysisResultCode(signature.toString()));
                index.increment();
            }            
        }
    }

    public AnalysisResultInBraces createInBrances() {
        return new AnalysisResultInBraces(list);
    }
}