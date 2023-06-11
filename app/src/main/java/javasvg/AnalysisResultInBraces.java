package javasvg;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * {}の中身を表すクラス
 */
public class AnalysisResultInBraces extends AnalysisResult {

    private ArrayList<AnalysisResult> list;

    public AnalysisResultInBraces(ArrayList<AnalysisResult> list) {
        this.list = new ArrayList<AnalysisResult>();
    }

    public AnalysisResult get(int index) {
        return list.get(index);
    }

    public Stream<AnalysisResult> stream() {
        return list.stream();
    }

    public String toString() {
        return "{\n" + list.stream().map(AnalysisResult::toString).reduce("", (a, b) -> a + "\n" + b) + "\n}";
    }
}