package javasvg;

import java.util.ArrayList;

public class AnalysisResultClass extends AnalysisResult {

    private AnalysisResultClassCode code;

    final public AnalysisResultInBraces inBraces;

    public AnalysisResultClass(ArrayList<Signature> signatures, Index index) {
        Signature signature = signatures.get(index.get());
        index.increment();

        this.code = new AnalysisResultClassCode(signature);
        this.inBraces = AnalysisResultInBraces.create(signatures, index);
    }

    public String toString() {
        String result = "class code\n" + code.toString();
        return result + inBraces.stream().map(AnalysisResult::toString).reduce("", (a, b) -> a + "\n" + b);
    }

    public String getName() {
        return code.getName();
    }
}