package javasvg;

import java.util.ArrayList;

import javasvg.signature.Signature;
import javasvg.signature.Word;

public class AnalysisResultClass extends AnalysisResult {

    final public String name;

    final public AnalysisResultInBraces inBraces;

    public AnalysisResultClass(ArrayList<Signature> signatures, Index index) {

        Signature signature = signatures.get(index.get());
        this.name = searchName(signature);
        index.increment();

        this.inBraces = create(signatures, index);
    }

    private String searchName(Signature signature) {

        //classの後にある単語がクラス名
        for (int i = 0; i < signature.size(); i++) {
            if (signature.get(i).equals("class")) {
                return signature.get(i + 1);
            }
        }
        throw new RuntimeException("class name not found");
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

            if (isMethod(signatures, index)) {
                list.add(new AnalysisResultMethod(signatures, index));
            } 
            else if (signature.contains("{")) {
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

    private boolean isMethod(ArrayList<Signature> signatures, Index index) {

        if (signatures.size() <= index.get() + 1) {
            return false;
        }

        Signature signature1 = signatures.get(index.get());
        Signature signature2 = signatures.get(index.get() + 1);

        if(signature1.contains(Word.Type.VOID) == false
        && signature1.contains(Word.Type.INT) == false
        && signature1.contains(Word.Type.STRING) == false
        && signature1.contains(Word.Type.DOUBLE) == false
        && signature1.contains(Word.Type.BOOLEAN) == false
        && signature1.contains(Word.Type.CHAR) == false
        && signature1.contains(Word.Type.FLOAT) == false
        && signature1.contains(Word.Type.LONG) == false
        && signature1.contains(Word.Type.BYTE) == false
        && signature1.contains(Word.Type.SHORT) == false
        && signature1.contains(Word.Type.OBJECT) == false
        && signature1.contains(Word.Type.ARRAY_LIST) == false){
            return false;
        }

        return signature2.contains("(");
    }
}