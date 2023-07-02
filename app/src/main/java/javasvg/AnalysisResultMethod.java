package javasvg;

import java.util.ArrayList;

import javasvg.phrase.Phrase;

public class AnalysisResultMethod extends AnalysisResult {

    final public String name;

    final public AnalysisResultCode arguments;

    final public AnalysisResultCode arThrows;

    final public AnalysisResultInBraces inBraces;

    public AnalysisResultMethod(ArrayList<Phrase> signatures, Index index) {

        Phrase signature = signatures.get(index.get());
        this.name = searchName(signature);
        index.increment();

        arguments = searchArguments(signatures, index);

        arThrows = searchThrows(signatures, index);

        this.inBraces = create(signatures, index);
    }

    // throws部分の取得
    private AnalysisResultCode searchThrows(ArrayList<Phrase> signatures, Index index){
        
        Phrase signature = signatures.get(index.get());
        // throwsがあるがない場合
        if (signatures.get(index.get()).contains("throws") == false) {
            return null;
        }        
        // throwsがある場合
        AnalysisResultCode result = new AnalysisResultCode(signature.get(signature.size() - 1));
        index.increment();
        return result;
    }

    // 引数部分の取得
    private AnalysisResultCode searchArguments(ArrayList<Phrase> signatures, Index index){

        if (signatures.get(index.get()).contains("(") == false) {
            throw new IllegalArgumentException("開始括弧がありません");
        }
        index.increment();
        
        Phrase phrase = signatures.get(index.get());
        index.increment();
        // 引数がない場合
        if (phrase.contains(")")) {
            return null;
        }   
        
        // 引数がある場合
        AnalysisResultCode result = new AnalysisResultCode(phrase);
        if (signatures.get(index.get()).contains(")") == false) {
            throw new IllegalArgumentException("終了括弧がありません");
        }                
        index.increment();
        return result;
    }

    private String searchName(Phrase signature) {

        return signature.get(signature.size() - 1);
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

            if (phrase.contains("{")) {
                list.add(AnalysisResultInBraces.create(signatures, index));
            } 
            else if (phrase.contains("}")) {
                index.increment();
                break;
            } 
            else if (phrase.get(0).equals("if")){
                list.add(new AnalysisResultIf(signatures, index));
            }
            else {
                list.add(new AnalysisResultCode(phrase));
                index.increment();
            }            
        }

        return new AnalysisResultInBraces(list);
    }
}
