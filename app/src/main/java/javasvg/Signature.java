package javasvg;

import java.util.ArrayList;

class Signature {
    private ArrayList<String> wordList;
    
    Signature() {
      wordList = new ArrayList<String>();
    }

    /**
     * 1文節を取得
     * @param source
     * @param index
     * @return
     */
    int extract(String source, int index) {

      wordList.clear();

      String word = "";
      while (index < source.length()) {
        char c = source.charAt(index);
        if (c == ' ' || c == '\n' || c == '\t' || c == '\r') {
          if (word.length() > 0) {
            wordList.add(word);
            word = "";
          }
        } else if (c == '(' || c == ')' || c == '{' || c == '}' || c == ';') {
          
          if (word.length() > 0) {
            wordList.add(word);
          }
          break;
        } else {
          word += c;
        }
        index++;
      }
      return index;
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < wordList.size(); i++) {
        sb.append(wordList.get(i));
        if (i < wordList.size() - 1) {
          sb.append(" ");
        }
      }
      return sb.toString();
    }
  }