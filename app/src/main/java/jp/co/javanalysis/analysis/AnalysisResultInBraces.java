package jp.co.javanalysis.analysis;
import java.util.ArrayList;
import java.util.stream.Stream;

import javasvg.Index;
import jp.co.javanalysis.phrase.Phrase;

/**
 * {}の中身を表すクラス
 */
public class AnalysisResultInBraces extends AnalysisResult {

    private ArrayList<AnalysisResult> list;

    public AnalysisResultInBraces(ArrayList<AnalysisResult> list) {
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

    public static AnalysisResultInBraces create(ArrayList<Phrase> signatures, Index index) {
        ArrayList<AnalysisResult> list = new ArrayList<AnalysisResult>();

        Phrase phrase = signatures.get(index.get());

        if (phrase.contains("{") == false) {
            throw new IllegalArgumentException("開始括弧がありません");
        }
        index.increment();

        while (signatures.size() > index.get()) {
            phrase = signatures.get(index.get());

            if (phrase.contains("{")) {
                list.add(AnalysisResultInBraces.create(signatures, index));
            } 
            else if (phrase.contains("}")) {
                index.increment();
                break;
            } else {
                list.add(new AnalysisResultCode(phrase));
                index.increment();
            }            
        }

        return new AnalysisResultInBraces(list);
    }
}