package javasvg;

public class AnalysisResultClass extends AnalysisResult {

    private AnalysisResultCode code;

    final public AnalysisResultInBraces inBraces;

    public AnalysisResultClass(AnalysisResultCode code, AnalysisResultInBraces inBraces) {
        this.code = code;
        this.inBraces = inBraces;
    }

    public String toString() {
        String result = "class code\n" + code.toString();
        return result + inBraces.stream().map(AnalysisResult::toString).reduce("", (a, b) -> a + "\n" + b);
    }

    public String getName() {
        return code.toString();
    }
}