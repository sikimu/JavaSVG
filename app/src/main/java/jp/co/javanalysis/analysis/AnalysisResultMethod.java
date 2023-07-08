package jp.co.javanalysis.analysis;

import java.util.ArrayList;

import javasvg.Index;
import jp.co.javanalysis.analysis.code.ThrowsCode;
import jp.co.javanalysis.phrase.Phrase;

public class AnalysisResultMethod extends AnalysisResult {

    final public String name;

    final public AnalysisResultCode arguments;

    final public ThrowsCode arThrows;

    final public AnalysisResultInBraces inBraces;

    public AnalysisResultMethod(ArrayList<Phrase> phraseList, Index index) {

        Phrase phrase = phraseList.get(index.get());
        this.name = searchName(phrase);
        this.arguments = searchArguments(phrase);
        this.arThrows = searchThrows(phrase);
        index.increment();

        this.inBraces = create(phraseList, index);
    }

    // throws部分の取得
    private ThrowsCode searchThrows(Phrase phrase){
        
        if(phrase.contains("throws") == false){
            return null;
        }

        ThrowsCode result = new ThrowsCode(phrase.subPhrase(phrase.indexOf("throws") + 1, phrase.size()));

        return result;
    }

    // 引数部分の取得
    private AnalysisResultCode searchArguments(Phrase phrase){

        return new AnalysisResultCode(phrase.subPhrase(phrase.indexOf("(") + 1, phrase.indexOf(")")));
    }

    private String searchName(Phrase signature) {

        return signature.get(signature.indexOf("(") - 1);
    }

    private AnalysisResultInBraces create(ArrayList<Phrase> signatures, Index index){
        
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
            } 
            else if (phrase.get(0).equals("if")){
                list.add(new AnalysisResultIf(signatures, index));
            }
            else if (phrase.contains("=")){
                list.add(new AnalysisResultAssignmen(phrase));
                index.increment();
            }
            else {
                list.add(new AnalysisResultCode(phrase));
                index.increment();
            }            
        }

        return new AnalysisResultInBraces(list);
    }
}
