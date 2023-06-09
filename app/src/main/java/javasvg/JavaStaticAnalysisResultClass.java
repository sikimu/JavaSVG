package javasvg;
import java.util.ArrayList;

public class JavaStaticAnalysisResultClass extends JavaStaticAnalysisResult {

    private JavaStaticAnalysisResultCode code;

    private ArrayList<JavaStaticAnalysisResult> list;

    public JavaStaticAnalysisResultClass(JavaStaticAnalysisResultCode code) {
        this.code = code;
        this.list = new ArrayList<JavaStaticAnalysisResult>();
    }

    public void add(JavaStaticAnalysisResultCode javaStaticAnalysisResultCode) {
        this.list.add(javaStaticAnalysisResultCode);
    }

    public String toString() {
        String result = "class code\n" + code.toString();
        return result + list.stream().map(JavaStaticAnalysisResult::toString).reduce("", (a, b) -> a + "\n" + b);
    }
}