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
        // throwsがある場合のみ
        if (signatures.get(index.get()).contains("throws") == false) {
            return new AnalysisResultCode("");
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
        
        Phrase signature = signatures.get(index.get());
        index.increment();
        // 引数がない場合
        if (signature.contains(")")) {
            return new AnalysisResultCode("");
        }   
        
        // 引数がある場合
        AnalysisResultCode result = new AnalysisResultCode(signature.toString());
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

        Phrase signature = signatures.get(index.get());

        if (signature.contains("{") == false) {
            throw new IllegalArgumentException("開始括弧がありません");
        }
        index.increment();

        while (signatures.size() > index.get()) {
            signature = signatures.get(index.get());

            if (signature.contains("{")) {
                list.add(AnalysisResultInBraces.create(signatures, index));
            } 
            else if (signature.contains("}")) {
                index.increment();
                break;
            } 
            else if (signature.get(0).equals("if")){
                list.add(new AnalysisResultIf(signatures, index));
            }
            else {
                list.add(new AnalysisResultCode(signature.toString()));
                index.increment();
            }            
        }

        return new AnalysisResultInBraces(list);
    }
}
