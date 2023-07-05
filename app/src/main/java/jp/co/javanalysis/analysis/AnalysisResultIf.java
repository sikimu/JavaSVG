package jp.co.javanalysis.analysis;

import java.util.ArrayList;

import javasvg.Index;
import jp.co.javanalysis.phrase.Phrase;

public class AnalysisResultIf extends AnalysisResult {

    final public AnalysisResultCode expression;

    final public AnalysisResult statement; 

    public AnalysisResultIf(ArrayList<Phrase> phraseList, Index index) {
        
        Phrase phrase = phraseList.get(index.get());
        //if()の解析
        int i = 2;
        int cnt = 0;
        while(true){
            if(phrase.get(i).equals(")")){
                if(cnt == 0){
                    break;
                }
                else{
                    cnt--;
                }
            }
            else if(phrase.get(i).equals("(")){
                cnt++;
            }
            i++;
        }
        expression = new AnalysisResultCode(phrase.subPhrase(2, i));
        //1行の場合
        if(phrase.size() > i+1){
            statement = new AnalysisResultCode(phrase.subPhrase(i + 1, phrase.size()));
        }
        //複数行の場合
        else{
            index.increment();
            statement = AnalysisResultInBraces.create(phraseList, index);
        }
    }
}