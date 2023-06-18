package javasvg;

import java.util.ArrayList;

class AnalysisResultInParenthesesFactory {

    public final int fixIndex;

    private ArrayList<AnalysisResult> list;
    
    public AnalysisResultInParenthesesFactory(ArrayList<Signature> signatures, int index) {
        this.list = new ArrayList<AnalysisResult>();

        Signature signature = signatures.get(index);

        if (signature.contains("(") == false) {
            throw new IllegalArgumentException("開始括弧がありません");
        }
        index++;

        while (signatures.size() > index) {
            signature = signatures.get(index);

            if (signature.contains("(")) {
                AnalysisInBracesFactory factory = new AnalysisInBracesFactory(signatures, index);
                list.add(factory.createInBrances());
                index = factory.getFixIndex();
            } else if (signature.contains(")")) {
                index++;
                break;
            } else {
                list.add(new AnalysisResultCode(signature.toString()));
                index++;
            }            
        }

        fixIndex = index;
    }

    public AnalysisResultInParentheses createInParentheses() {
        return new AnalysisResultInParentheses(list);
    }

    /**
     * 解析完了時のインデックスを取得する
     * @return
     */
    public int getFixIndex() {
        return fixIndex;
    }
}