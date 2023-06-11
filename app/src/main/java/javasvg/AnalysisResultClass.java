package javasvg;

public class AnalysisResultClass extends AnalysisResult {

    private AnalysisResultCode code;

    private AnalysisResultInBraces iBraces;

    public AnalysisResultClass(AnalysisResultCode code, AnalysisResultInBraces inBraces) {
        this.code = code;
        this.iBraces = inBraces;
    }

    public String toString() {
        String result = "class code\n" + code.toString();
        return result + iBraces.stream().map(AnalysisResult::toString).reduce("", (a, b) -> a + "\n" + b);
    }
}