package javasvg;

public class JavaStaticAnalysisResultCode extends JavaStaticAnalysisResult {

    String code;

    public JavaStaticAnalysisResultCode(String code) {
        this.code = code;
    }

    public String toString() {
        return this.code;
    }
}