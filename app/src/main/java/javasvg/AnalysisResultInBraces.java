package javasvg;
import java.util.ArrayList;
import java.util.stream.Stream;

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

    public static AnalysisResultInBraces create(ArrayList<Signature> signatures, Index index) {
        ArrayList<AnalysisResult> list = new ArrayList<AnalysisResult>();

        Signature signature = signatures.get(index.get());

        if (signature.contains("{") == false) {
            throw new IllegalArgumentException("開始括弧がありません");
        }
        index.increment();

        while (signatures.size() > index.get()) {
            signature = signatures.get(index.get());

            if (signature.contains("{")) {
                list.add(AnalysisResultInBraces.create(signatures, index));
            } 
            else if (signature.contains("(")){
                AnalysisResultInParenthesesFactory factory = new AnalysisResultInParenthesesFactory(signatures, index);
                list.add(factory.createInParentheses());
            } else if (signature.contains("}")) {
                index.increment();
                break;
            } else {
                list.add(new AnalysisResultCode(signature.toString()));
                index.increment();
            }            
        }

        return new AnalysisResultInBraces(list);
    }
}