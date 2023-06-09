package javasvg;
import java.util.ArrayList;

public class AnalysisResultClass extends AnalysisResult {

    private AnalysisResultCode code;

    private ArrayList<AnalysisResult> list;

    public AnalysisResultClass(AnalysisResultCode code) {
        this.code = code;
        this.list = new ArrayList<AnalysisResult>();
    }

    public void add(AnalysisResultCode javaStaticAnalysisResultCode) {
        this.list.add(javaStaticAnalysisResultCode);
    }

    public String toString() {
        String result = "class code\n" + code.toString();
        return result + list.stream().map(AnalysisResult::toString).reduce("", (a, b) -> a + "\n" + b);
    }
}