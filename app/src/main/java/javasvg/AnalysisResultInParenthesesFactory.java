package javasvg;

import java.util.ArrayList;

class AnalysisResultInParenthesesFactory {

    private ArrayList<AnalysisResult> list;
    
    public AnalysisResultInParenthesesFactory(ArrayList<Signature> signatures, Index index) {
        this.list = new ArrayList<AnalysisResult>();

        Signature signature = signatures.get(index.get());

        if (signature.contains("(") == false) {
            throw new IllegalArgumentException("開始括弧がありません");
        }
        index.increment();

        while (signatures.size() > index.get()) {
            signature = signatures.get(index.get());

            if (signature.contains("(")) {
                AnalysisResultInParenthesesFactory factory = new AnalysisResultInParenthesesFactory(signatures, index);
                list.add(factory.createInParentheses());
            } else if (signature.contains(")")) {
                index.increment();;
                break;
            } else {
                list.add(new AnalysisResultCode(signature.toString()));
                index.increment();;
            }            
        }
    }

    public AnalysisResultInParentheses createInParentheses() {
        return new AnalysisResultInParentheses(list);
    }
}