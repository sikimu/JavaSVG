package javasvg;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import jp.co.javanalysis.phrase.Phrase;
import jp.co.javanalysis.phrase.PhraseListFactory;

public class PhraseListFactoryTest {
    @Test
    void 文節と文節の間のスペース() {
        ArrayList<Phrase> signatures = PhraseListFactory.create("{ }");

        assertEquals("{", signatures.get(0).get(0));
        assertEquals("}", signatures.get(1).get(0));
    }

    @Test
    void 最後の改行が2連続() {
        ArrayList<Phrase> signatures = PhraseListFactory.create("}\n\n");

        assertEquals("}", signatures.get(0).get(0));
    }    

    @Test
    void 始めのスペース() {
        ArrayList<Phrase> signatures = PhraseListFactory.create(" \n aaaaa");

        assertEquals("aaaaa", signatures.get(0).get(0));
    }    
}
