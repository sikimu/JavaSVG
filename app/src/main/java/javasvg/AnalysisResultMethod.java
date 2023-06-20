package javasvg;

import java.util.ArrayList;

public class AnalysisResultMethod extends AnalysisResult {

    final public String name;

    final public AnalysisResultCode arguments;

    final public AnalysisResultInBraces inBraces;

    public AnalysisResultMethod(ArrayList<Signature> signatures, Index index) {

        Signature signature = signatures.get(index.get());
        this.name = searchName(signature);
        index.increment();

        arguments = searchArguments(signatures, index);

        this.inBraces = create(signatures, index);
    }

    // 引数部分の取得
    private AnalysisResultCode searchArguments(ArrayList<Signature> signatures, Index index){

        if (signatures.get(index.get()).contains("(") == false) {
            throw new IllegalArgumentException("開始括弧がありません");
        }
        index.increment();
        
        Signature signature = signatures.get(index.get());
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

    private String searchName(Signature signature) {

        return signature.get(signature.size() - 1);
    }

    private AnalysisResultInBraces create(ArrayList<Signature> signatures, Index index){
        
        ArrayList<AnalysisResult> list = new ArrayList<AnalysisResult>();

        Signature signature = signatures.get(index.get());

        if (signature.contains("{") == false) {
            throw new IllegalArgumentException("開始括弧がありません");
        }
        index.increment();

        while (signatures.size() > index.get()) {
            signature = signatures.get(index.get());

            if (signature.contains("{")) {
                list.add(AnalysisResultInBraces.create(signatures, index));
            } 
            else if (signature.contains("(")){
                list.add(AnalysisResultInParentheses.create(signatures, index));
            } else if (signature.contains("}")) {
                index.increment();
                break;
            } else {
                list.add(new AnalysisResultCode(signature.toString()));
                index.increment();
            }            
        }

        return new AnalysisResultInBraces(list);
    }
}
