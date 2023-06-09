package javasvg;
import java.util.ArrayList;

public class AnalysisResultSource extends AnalysisResult {

    private ArrayList<AnalysisResult> list;

    public AnalysisResultSource() {
        this.list = new ArrayList<AnalysisResult>();
    }

    public void add(AnalysisResult result) {
        this.list.add(result);
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < this.list.size(); i++) {
            result = result + this.list.get(i).toString() + "\n";
        }
        return list.stream().map(AnalysisResult::toString).reduce("", (a, b) -> a + "\n" + b);
    }
}