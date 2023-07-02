package javasvg;

import java.util.ArrayList;
import java.util.stream.Stream;

import javasvg.phrase.Phrase;

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

    public static AnalysisResultInParentheses create(ArrayList<Phrase> signatures, Index index) {
        ArrayList<AnalysisResult> list = new ArrayList<AnalysisResult>();

        Phrase phrase = signatures.get(index.get());

        if (phrase.contains("(") == false) {
            throw new IllegalArgumentException("開始括弧がありません");
        }
        index.increment();

        while (signatures.size() > index.get()) {
            phrase = signatures.get(index.get());

            if (phrase.contains("(")) {
                list.add(AnalysisResultInParentheses.create(signatures, index));
            } else if (phrase.contains(")")) {
                index.increment();
                break;
            } else {
                list.add(new AnalysisResultCode(phrase));
                index.increment();
            }            
        }

        return new AnalysisResultInParentheses(list);
    }    
}
