package javasvg;

public class AnalysisResultCode extends AnalysisResult {

    String code;

    public AnalysisResultCode(String code) {
        this.code = code;
    }

    public String toString() {
        return this.code;
    }
}