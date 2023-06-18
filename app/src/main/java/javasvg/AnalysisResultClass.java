package javasvg;

import java.util.ArrayList;

public class AnalysisResultClass extends AnalysisResult {

    final public String name;

    final public AnalysisResultInBraces inBraces;

    public AnalysisResultClass(ArrayList<Signature> signatures, Index index) {

        Signature signature = signatures.get(index.get());
        this.name = searchName(signature);
        index.increment();

        this.inBraces = AnalysisResultInBraces.create(signatures, index);
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
}