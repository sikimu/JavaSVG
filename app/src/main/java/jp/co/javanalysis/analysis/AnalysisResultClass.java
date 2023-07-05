package jp.co.javanalysis.analysis;

import java.util.ArrayList;

import javasvg.Index;
import jp.co.javanalysis.phrase.Phrase;

public class AnalysisResultClass extends AnalysisResult {

    final public String name;

    final public AnalysisResultInBraces inBraces;

    public AnalysisResultClass(ArrayList<Phrase> signatures, Index index) {

        Phrase signature = signatures.get(index.get());
        this.name = searchName(signature);
        index.increment();

        this.inBraces = create(signatures, index);
    }

    private String searchName(Phrase signature) {

        //classの後にある単語がクラス名
        for (int i = 0; i < signature.size(); i++) {
            if (signature.get(i).equals("class")) {
                return signature.get(i + 1);
            }
        }
        throw new RuntimeException("class name not found");
    }

    private AnalysisResultInBraces create(ArrayList<Phrase> signatures, Index index){
        
        ArrayList<AnalysisResult> list = new ArrayList<AnalysisResult>();

        Phrase phrase = signatures.get(index.get());

        if (phrase.contains("{") == false) {
            throw new IllegalArgumentException("開始括弧がありません");
        }
        index.increment();

        while (signatures.size() > index.get()) {
            phrase = signatures.get(index.get());

            if (isMethod(signatures, index)) {
                list.add(new AnalysisResultMethod(signatures, index));
            } 
            else if (phrase.contains("{")) {
                list.add(AnalysisResultInBraces.create(signatures, index));
            } 
            else if (phrase.contains("}")) {
                index.increment();
                break;
            } else {
                list.add(new AnalysisResultCode(phrase));
                index.increment();
            }            
        }

        return new AnalysisResultInBraces(list);
    }

    private boolean isMethod(ArrayList<Phrase> phraList, Index index) {

        Phrase phrase = phraList.get(index.get());
        //(の位置を特定できればメソッド
        int indexOfOpenParenthesis = phrase.indexOf("(");
        if(indexOfOpenParenthesis == -1){
            return false;
        }

        return true;
    }
}