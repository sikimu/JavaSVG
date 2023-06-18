package javasvg;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * ()の中身を表すクラス
 */
public class AnalysisResultInParentheses extends AnalysisResult {

    private ArrayList<AnalysisResult> list;

    public AnalysisResultInParentheses(ArrayList<AnalysisResult> list) {
        this.list = list;
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

    public Integer size() {
        return list.size();
    }
}
