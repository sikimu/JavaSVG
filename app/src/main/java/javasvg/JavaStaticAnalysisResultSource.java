package javasvg;
import java.util.ArrayList;

public class JavaStaticAnalysisResultSource extends JavaStaticAnalysisResult {

    private ArrayList<JavaStaticAnalysisResult> list;

    public JavaStaticAnalysisResultSource() {
        this.list = new ArrayList<JavaStaticAnalysisResult>();
    }

    public void add(JavaStaticAnalysisResult result) {
        this.list.add(result);
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < this.list.size(); i++) {
            result = result + this.list.get(i).toString() + "\n";
        }
        return list.stream().map(JavaStaticAnalysisResult::toString).reduce("", (a, b) -> a + "\n" + b);
    }
}