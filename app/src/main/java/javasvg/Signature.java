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

        if (source.substring(index).startsWith("/*")) {
          if(word.length() > 0){
            wordList.add(word);
            word = "";
          }
          word += "/*";
          index += 2;
          while (index < source.length()) {
            if (source.substring(index).startsWith("*/")) {
              break;
            }            
            c = source.charAt(index);
            word += c;
            index++;
          }
          word += "*/";
          wordList.add(word);
          word = "";
          index ++;
        }
        else if(c == '"'){
          word += c;
          index++;
          while (index < source.length()) {
            c = source.charAt(index);
            word += c;
            if (c == '"') {
              break;
            }
            index++;
          }
          wordList.add(word);
          word = "";
        }
        else if (c == ' ' || c == '\n' || c == '\t' || c == '\r') {
          if (word.length() > 0) {
            wordList.add(word);
            word = "";
          }
        } else if (c == '(' || c == ')' || c == '{' || c == '}' || c == ';') {
          
          if (word.length() > 0) {
            wordList.add(word);
            word = "";
          }
          else if(word.length() == 0 && wordList.size() == 0){
            wordList.add(String.valueOf(c));
            word = "";
            index++;
          }
          
          break;
        } else {
          word += c;
        }
        index++;
      }

      if(word.length() > 0){
        wordList.add(word);
      }

      return index;
    }

    public boolean contains(String word) {
      return wordList.contains(word);
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

    public String get(int i) {
      return wordList.get(i);
    }

    public int size() {
      return wordList.size();
    }
  }