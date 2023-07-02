package javasvg;

import java.util.ArrayList;

import javasvg.phrase.Phrase;

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

        Phrase signature = signatures.get(index.get());

        if (signature.contains("{") == false) {
            throw new IllegalArgumentException("開始括弧がありません");
        }
        index.increment();

        while (signatures.size() > index.get()) {
            signature = signatures.get(index.get());

            if (isMethod(signatures, index)) {
                list.add(new AnalysisResultMethod(signatures, index));
            } 
            else if (signature.contains("{")) {
                list.add(AnalysisResultInBraces.create(signatures, index));
            } 
            else if (signature.contains("}")) {
                index.increment();
                break;
            } else {
                list.add(new AnalysisResultCode(signature.toString()));
                index.increment();
            }            
        }

        return new AnalysisResultInBraces(list);
    }

    private boolean isMethod(ArrayList<Phrase> signatures, Index index) {

        if (signatures.size() <= index.get() + 1) {
            return false;
        }

        Phrase signature1 = signatures.get(index.get());
        Phrase signature2 = signatures.get(index.get() + 1);

        if(signature1.contains("void") == false
        && signature1.contains("int") == false
        && signature1.contains("String") == false
        && signature1.contains("double") == false
        && signature1.contains("boolean") == false
        && signature1.contains("char") == false
        && signature1.contains("float") == false
        && signature1.contains("long") == false
        && signature1.contains("byte") == false
        && signature1.contains("short") == false
        && signature1.contains("Object") == false
        && signature1.contains("ArrayList") == false){
            return false;
        }

        return signature2.contains("(");
    }
}